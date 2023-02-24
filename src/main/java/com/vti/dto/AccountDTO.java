package com.vti.dto;

import lombok.Data;

@Data
public class AccountDTO {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String fullName;
    private String role;
    private String departmentName;
}
