package com.vti.form;

import com.vti.entities.Account;
import com.vti.validation.AccountNameNotExists;
import com.vti.validation.DepartmentIdExists;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class AccountCreateForm {

    @NotBlank(message = "Username must be not blank")
    @AccountNameNotExists
    private String username;

    private String firstName;

    private String lastName;

    @Length(min = 6, max = 15, message = "Password between from 6 to 15 character")
    private String password;

    @Pattern(regexp = "ADMIN|MANAGER|EMPLOYEE",
            message = "Department type must be ADMIN, MANAGER, EMPLOYEE")
    private String role;

//    @DepartmentIdExists
//    private Integer departmentId;
}
