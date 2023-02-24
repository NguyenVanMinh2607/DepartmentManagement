package com.vti.service;

import com.vti.entities.Account;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountFilterFrom;
import com.vti.form.AccountUpdateForm;
import com.vti.form.PasswordUpdateForm;
import com.vti.repository.IAccountRepository;
import com.vti.specification.AccountSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IAccountRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Page<Account> findAll(Pageable pageable, AccountFilterFrom form) {
        Specification<Account> spec = AccountSpecification.buildSpec(form);
        return repository.findAll(spec, pageable);
    }

    // Lấy ra thông tin để xác thực người dùng, được gọi khi người dùng tiến hành đăng nhập
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repository.findByUsername(username);
        if(account == null) {
            throw new UsernameNotFoundException(username);
        }else {
            return new User(
                    account.getUsername(),
                    account.getPassword(),
                    AuthorityUtils.createAuthorityList(account.getRole().toString()));
//                    Collections.emptyList());
        }
    }

    @Override
    public Account findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Account findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public void create(AccountCreateForm form) {
        Account account = mapper.map(form, Account.class);
        account.setPassword(encoder.encode(form.getPassword()));
//        account.setPassword(BCrypt.hashpw(form.getPassword(), BCrypt.gensalt(12)));
        repository.save(account);
    }

    @Override
    public void update(AccountUpdateForm form) {
        Account account = mapper.map(form, Account.class);
        account.setPassword(encoder.encode(form.getPassword()));
        repository.save(account);

        // Refresh authentication for CREATE, UPDATE, DELETE
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Authentication newAuth = new UsernamePasswordAuthenticationToken(
                auth.getPrincipal(),
                auth.getCredentials(),
                AuthorityUtils.createAuthorityList(account.getRole().toString())
        );
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

    @Override
    public void updatePassword(PasswordUpdateForm passwordUpdateForm) {
        Account account = repository.findByUsername(passwordUpdateForm.getUsername());
        account.setPassword(encoder.encode(passwordUpdateForm.getPassword()));
        repository.save(account);
    }

    public void deleteAllById(List<Integer> ids) {
        // deleteAllByIdInBath : xoá all trong 1 câu truy vấn
        repository.deleteAllById(ids);
    }

}
