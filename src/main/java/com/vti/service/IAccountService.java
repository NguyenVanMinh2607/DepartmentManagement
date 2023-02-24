package com.vti.service;

import com.vti.entities.Account;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountFilterFrom;
import com.vti.form.AccountUpdateForm;
import com.vti.form.PasswordUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IAccountService extends UserDetailsService {
    Page<Account> findAll(Pageable pageable, AccountFilterFrom form);

    Account findById(int id);

    Account findByUsername(String username);

    void create(AccountCreateForm form);

    void update(AccountUpdateForm form);

    void updatePassword(PasswordUpdateForm passwordUpdateForm);

    void deleteAllById(List<Integer> ids);
}
