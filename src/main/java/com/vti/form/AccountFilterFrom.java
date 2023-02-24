package com.vti.form;

import com.vti.entities.Account;
import lombok.Data;

@Data
public class AccountFilterFrom {

    private String search;

    private Integer minId;

    private Integer maxId;

    private Account.Role role;
}
