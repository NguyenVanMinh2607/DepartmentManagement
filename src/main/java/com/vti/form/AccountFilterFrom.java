package com.vti.form;

import lombok.Data;

@Data
public class AccountFilterFrom {

    private String search;

    private Integer minId;

    private Integer maxId;
}
