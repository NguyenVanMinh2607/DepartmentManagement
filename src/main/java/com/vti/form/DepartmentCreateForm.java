package com.vti.form;

import com.vti.entities.Account;
import com.vti.entities.Department;
import com.vti.validation.AccountNameNotExists;
import com.vti.validation.DepartmentNameNotExists;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
public class DepartmentCreateForm {

    @NotBlank(message = "{DepartmentForm.name.NotBlank}")
    @DepartmentNameNotExists
    @Length(max = 50, message = "{DepartmentForm.name.Length}")
    private String name;

    @PositiveOrZero(message = "TotalMember must be zero gretter than or equal")
    @NotNull(message = "TotalMember must be not null")
    private Integer totalMembers;

    // Pattern chhỉ dùng với String khong dùng với Enum
    @Pattern(regexp = "DEVELOPER|TESTER|SCRUM_MASTER|PROJECT_MANAGER",
            message = "Department type must be DEVELOPER, TESTER, SCRUM_MASTER, PROJECT_MANAGER")
    private String type;

//    @NotEmpty(message = "Accounts mustn't be empty")
//    private List<@Valid Account> accounts;
    private List<Account> accounts;

    @Data
    public static class Account {

        @NotBlank(message = "The username mustn't be null value")
        @Length(max = 50, message = "The name's length is max 50 character")
        @AccountNameNotExists
        private String username;

        private String firstName;

        private String lastName;

        @Length(min = 6, max = 15, message = "Password from 6 to 15 character")
        private String password;

        @Pattern(regexp = "ADMIN|MANAGER|EMPLOYEE",
                message = "Department type must be ADMIN, MANAGER, EMPLOYEE")
        private String role;
    }
}
