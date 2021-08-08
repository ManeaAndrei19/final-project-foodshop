package com.maneaandrei.sda.foodshop.repository;

import com.maneaandrei.sda.foodshop.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

    /**
     * TODO: To be completed
     */
    Account findByEmail(String email);

}
