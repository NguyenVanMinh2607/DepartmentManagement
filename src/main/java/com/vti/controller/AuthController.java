package com.vti.controller;

import com.vti.dto.ProfileDTO;
import com.vti.entities.Account;
import com.vti.form.AccountCreateForm;
import com.vti.form.PasswordUpdateForm;
import com.vti.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private IAccountService iAccountService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/register")
    public void register(@RequestBody AccountCreateForm createForm) {
        iAccountService.create(createForm);
    }

    @GetMapping("/login")
    // Lấy ra thông tin người dùng đã đăng nhập thành công
    // Principal để lưu username người dùng sau khi đăng nhập
    public ProfileDTO getProfile(Principal principal) {
        Account account = iAccountService.findByUsername(principal.getName());
        ProfileDTO profileDTO = mapper.map(account, ProfileDTO.class);
        return profileDTO;
    }

    @PutMapping("/change")
    public void changePassword(@RequestBody PasswordUpdateForm authUpdateForm) {
        iAccountService.updatePassword(authUpdateForm);
    }
}
