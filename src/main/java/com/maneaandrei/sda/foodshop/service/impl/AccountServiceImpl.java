package com.maneaandrei.sda.foodshop.service.impl;

import com.maneaandrei.sda.foodshop.model.Role;
import com.maneaandrei.sda.foodshop.repository.AccountRepository;
import com.maneaandrei.sda.foodshop.repository.CustomerRepository;
import com.maneaandrei.sda.foodshop.service.AccountService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class AccountServiceImpl extends AccountService {
    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository, BCryptPasswordEncoder passwordEncoder) {
        super(accountRepository, customerRepository, passwordEncoder);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Role role)
    {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }
}
