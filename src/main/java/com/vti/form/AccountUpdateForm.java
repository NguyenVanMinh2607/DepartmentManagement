package com.vti.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountUpdateForm {
    private Integer id;

    private String password;

    private Integer departmentId;
}
