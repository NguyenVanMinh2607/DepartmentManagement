package com.vti.form;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class PasswordUpdateForm {

    private String username;

    private String password;
}
