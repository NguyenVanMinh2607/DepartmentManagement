package com.vti.controller;

import com.vti.dto.AccountDTO;
import com.vti.entities.Account;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountFilterFrom;
import com.vti.form.AccountUpdateForm;
import com.vti.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
//@CrossOrigin("http://127.0.0.1:5500/") chỉ cho 1 port 5500 truy cập để lấy dữ liệu
//@CrossOrigin("*")
public class AccountController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IAccountService service;

    @GetMapping
    public Page<AccountDTO> findAll(Pageable pageable, AccountFilterFrom form) {
        Page<Account> page = service.findAll(pageable, form);
        List<Account> accounts = page.getContent();
        List<AccountDTO> dtos = mapper.map(accounts, new TypeToken<List<AccountDTO>>(){}.getType());
        return new PageImpl<>(dtos, pageable, page.getTotalPages());
    }

    @GetMapping("/{id}")
    public AccountDTO findById(@PathVariable("id") int id) {
        Account account = service.findById(id);
        AccountDTO dto = mapper.map(account, AccountDTO.class);
        return dto;
    }

    @PostMapping
    public void create(@RequestBody @Valid AccountCreateForm form) {
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody AccountUpdateForm form) {
        form.setId(id);
        service.update(form);
    }

    @DeleteMapping
    public void deleteAllById(@RequestBody List<Integer> ids) {
        service.deleteAllById(ids);
    }
}
