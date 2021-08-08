package com.maneaandrei.sda.foodshop.repository;

import com.maneaandrei.sda.foodshop.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByAccount_Id(Long id);
}
