package com.vti.service;

import com.vti.dto.AccountDTO;
import com.vti.entities.Account;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountFilterFrom;
import com.vti.form.AccountUpdateForm;
import com.vti.repository.IAccountRepository;
import com.vti.specification.AccountSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IAccountRepository repository;

    @Override
    public Page<Account> findAll(Pageable pageable, AccountFilterFrom form) {
        Specification<Account> spec = AccountSpecification.buildSpec(form);
        return repository.findAll(spec, pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repository.findByUsername(username);
        if(account == null) {
            throw new UsernameNotFoundException(username);
        }else {
            return new User(
                    account.getUsername(),
                    account.getPassword(),
                    Collections.emptyList());
        }
    }

    @Override
    public Account findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void create(AccountCreateForm form) {
        Account account = mapper.map(form, Account.class);
        account.setPassword(BCrypt.hashpw(form.getPassword(), BCrypt.gensalt(12)));
        repository.save(account);
    }

    @Override
    public void update(AccountUpdateForm form) {
        Account account = mapper.map(form, Account.class);
        account.setPassword(BCrypt.hashpw(form.getPassword(), BCrypt.gensalt(12)));
        repository.save(account);
    }

    public void deleteAllById(List<Integer> ids) {
        // deleteAllByIdInBath : xoá all trong 1 câu truy vấn
        repository.deleteAllById(ids);
    }

}
