package com.vti.form;

import com.vti.entities.Department;
import com.vti.validation.DepartmentNameNotExists;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class DepartmentCreateForm {

    @NotBlank(message = "Name must be not blank")
    @DepartmentNameNotExists
//    @Length(max = 50, message = "The name's length is max 50 characters")
    private String name;

    @PositiveOrZero(message = "TotalMember must be zero gretter than or equal")
    @NotNull(message = "TotalMember must be not null")
    private Integer totalMembers;

    // Pattern chhỉ dùng với String k dùng với enum
    @Pattern(regexp = "DEVELOPER|TESTER|SCRUM_MASTER|PROJECT_MANAGER",
            message = "Department type must be DEVELOPER, TESTER, SCRUM_MASTER, PROJECT_MANAGER")
    private String type;

    private List<Account> accounts;

    @Data
    public static class Account {
        private String username;
        private String password;
    }
}
