package com.maneaandrei.sda.foodshop.service;

import com.maneaandrei.sda.foodshop.model.Account;
import com.maneaandrei.sda.foodshop.model.Role;
import com.maneaandrei.sda.foodshop.repository.AccountRepository;
import com.maneaandrei.sda.foodshop.service.dto.AccountDTO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

@Service
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, BCryptPasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return new User(account.getEmail(), account.getPassword(), new ArrayList<>());
    }

    public void createAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setEmail(accountDTO.getEmail());
        account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        account.setCreationDate(Timestamp.from(Instant.now()));
        account.setRole(Role.CUSTOMER);

        accountRepository.save(account);
    }

    public Boolean accountExist(String email) {
        Account accountExisting = accountRepository.findByEmail(email);
        return accountExisting != null;
    }
}
