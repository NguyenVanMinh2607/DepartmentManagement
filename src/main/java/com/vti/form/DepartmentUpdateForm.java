package com.vti.form;

import com.vti.entities.Department;
import com.vti.validation.DepartmentIdExists;
import com.vti.validation.DepartmentNameNotExists;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

@Data
public class DepartmentUpdateForm {

    @DepartmentIdExists
    private Integer id;

    @NotBlank(message = "Name must be not blank")
    @DepartmentNameNotExists
    private String name;

    @PositiveOrZero(message = "TotalMember must be zero gretter than or equal")
    @NotNull(message = "TotalMember must be not null")
    private Integer totalMembers;

    @Pattern(regexp = "DEVELOPER|TESTER|SCRUM_MASTER|PROJECT_MANAGER",
            message = "Department type must be DEVELOPER, TESTER, SCRUM_MASTER, PROJECT_MANAGER")
    private Department.Type type;
}
