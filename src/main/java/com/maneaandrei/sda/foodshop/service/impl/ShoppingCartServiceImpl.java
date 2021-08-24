package com.maneaandrei.sda.foodshop.service.impl;


import com.maneaandrei.sda.foodshop.model.*;
import com.maneaandrei.sda.foodshop.repository.*;
import com.maneaandrei.sda.foodshop.service.ShoppingCartService;
import com.maneaandrei.sda.foodshop.service.mail.MailService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final BillRepository billRepository;
    private final MailService mailService;

    private Map<Food, Integer> cart = new LinkedHashMap<>();

    public ShoppingCartServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository, OrderRepository orderRepository, OrderLineRepository orderLineRepository, BillRepository billRepository, MailService mailService) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
        this.billRepository = billRepository;
        this.mailService = mailService;
    }

    @Override
    public void addProduct(Food food) {
        if (cart.containsKey(food)) {
            cart.replace(food, cart.get(food) + 1);
        } else {
            cart.put(food, 1);
        }
    }

    @Override
    public void removeProduct(Food food) {
        if (cart.containsKey(food)) {
            if (cart.get(food) > 1)
                cart.replace(food, cart.get(food) - 1);
            else if (cart.get(food) == 1) {
                cart.remove(food);
            }
        }
    }

    @Override
    public void clearProducts() {
        cart.clear();
    }

    @Override
    public Map<Food, Integer> productsInCart() {
        return Collections.unmodifiableMap(cart);
    }

    @Override
    public BigDecimal totalPrice() {
        return cart.entrySet().stream()
                .map(k -> k.getKey().getPrice().multiply(BigDecimal.valueOf(k.getValue()))).sorted()
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public void cartCheckout() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountRepository.findByEmail(principal.getUsername());
        Customer customer = customerRepository.findByAccount_Id(account.getId());

        Bill bill = new Bill();
        double price = 0;

        //create order
        Order order = new Order();
        order.setCreationDate(Timestamp.from(Instant.now()));
        order.setOrderStatus(OrderStatus.NEW);
        //info from customer
        order.setCustomer(customer);
        order.setAddress(customer.getAddress());


        orderRepository.save(order);

        //create orderLines
        List<OrderLine> orderLines = new ArrayList<>();
        for (Map.Entry<Food, Integer> foodIntegerEntry : cart.entrySet()) {


            //create orderLine
            OrderLine orderLine = new OrderLine();
            orderLine.setFood(foodIntegerEntry.getKey());
            orderLine.setQuantity(foodIntegerEntry.getValue());
            orderLine.setPrice((foodIntegerEntry.getValue() * foodIntegerEntry.getKey().getPrice().doubleValue()));


            // add orderLine to orderLines
            orderLine.setOrder(order);

            // save orderLine
            orderLineRepository.save(orderLine);

            price += orderLine.getPrice();
        }

        // Truncate price to 2 decimals
        BigDecimal bd = new BigDecimal(String.valueOf(price));
        BigDecimal rounded = bd.setScale(2, RoundingMode.FLOOR);
        price = rounded.doubleValue();

        bill.setCurrency("RON");
        bill.setIssueDate(Timestamp.from(Instant.now()));
        bill.setTotalPrice(price);
        bill.setDueDate(Timestamp.from(Instant.now().plusSeconds(3600))); // 1 hour delivery time
        billRepository.save(bill);
        order.setBill(bill);

        //save order in repository
        orderRepository.save(order);

        cart.clear();

        // to add MAIL PROP to account EMAIL
        try {
            mailService.sendMail("andrei@food.com", principal.getUsername(), "Order was successfully created ",
                    "Order details: " +
                            "\nOrder id: " + order.getId() +
                            "\nOrder creation date: " + order.getCreationDate() +
                            "\nOrder address: " + order.getAddress() +
                            "\nTotal Price: " + order.getBill().getTotalPrice() + order.getBill().getCurrency());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
