package com.maneaandrei.sda.foodshop.service.dto;

import com.maneaandrei.sda.foodshop.model.Bill;
import com.maneaandrei.sda.foodshop.model.Customer;
import com.maneaandrei.sda.foodshop.model.OrderLine;
import com.maneaandrei.sda.foodshop.model.OrderStatus;

import java.sql.Timestamp;
import java.util.List;

public class OrderDTO {
    private Long id;
    private String customerLogin;
    private String address;
    private Timestamp creationDate;
    private Customer customer;
    private Bill bill;
    private OrderStatus orderStatus;
    private List<OrderLine> orderLines;

    public Long getId() {
        return id;
    }

    public String getCustomerLogin() {
        return customerLogin;
    }

    public void setCustomerLogin(String customerLogin) {
        this.customerLogin = customerLogin;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}
