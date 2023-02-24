package com.vti.dto;

import lombok.Data;

@Data
public class ProfileDTO {
    private Integer id;
    private String username;
    private String fullName;
    private String role;
    private String departmentName;
}
