package com.vti.form;

import com.vti.entities.Account;
import com.vti.validation.DepartmentIdExists;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class AccountUpdateForm {
    private Integer id;

    private String firstName;

    private String lastName;

    @Length(min = 6, max = 15, message = "Password between from 6 to 15 character")
    private String password;

    @Pattern(regexp = "ADMIN|MANAGER|EMPLOYEE",
            message = "Department type must be ADMIN, MANAGER, EMPLOYEE")
    private String role;

    @DepartmentIdExists
    private Integer departmentId;
}
