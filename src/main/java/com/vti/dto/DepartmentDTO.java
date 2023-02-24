package com.vti.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.entities.Department;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DepartmentDTO extends RepresentationModel<DepartmentDTO> {

    private Integer id;

    private String name;

    private Integer totalMembers;

    private Department.Type type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    private List<AccountDTO> accounts;

    @Data
    public static class AccountDTO extends RepresentationModel<AccountDTO>{
        private Integer id;
        private String username;
    }
}
