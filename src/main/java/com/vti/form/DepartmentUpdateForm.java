package com.vti.form;

import com.vti.entities.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentUpdateForm {
    private Integer id;
    private String name;
    private Integer totalMembers;
    private Department.Type type;
}
