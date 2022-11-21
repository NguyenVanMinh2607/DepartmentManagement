package com.vti.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AccountCreateForm {

//    @NotBlank(message = "Username must be nnot blank")
    private String username;

    private String password;

    private Integer departmentId;
}
