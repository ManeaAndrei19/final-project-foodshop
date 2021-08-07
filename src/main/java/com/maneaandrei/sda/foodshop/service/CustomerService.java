package com.maneaandrei.sda.foodshop.service;

import com.maneaandrei.sda.foodshop.model.Customer;
import com.maneaandrei.sda.foodshop.repository.AccountRepository;
import com.maneaandrei.sda.foodshop.repository.CustomerRepository;
import com.maneaandrei.sda.foodshop.service.dto.CustomerDTO;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public CustomerService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    public void createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setEmail(customerDTO.getEmail());
        customer.setAddress(customerDTO.getAddress());
        customer.setPhone(customerDTO.getPhone());
        customer.setAccount(customerDTO.getAccount());

        customerRepository.save(customer);
    }
}
