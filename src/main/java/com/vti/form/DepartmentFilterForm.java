package com.vti.form;

import com.vti.entities.Department;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Setter
@Getter
public class DepartmentFilterForm {

    private String search;
    private Integer minTotalMembers;
    private Integer maxTotalMembers;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime minCreateAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime maxCreateAt;

    private Integer minCreatedYear;

    private Department.Type type;
}
