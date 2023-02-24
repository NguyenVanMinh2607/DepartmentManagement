package com.vti.validation;

import com.vti.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountNameNotExistsValidator implements ConstraintValidator<AccountNameNotExists, String> {

    @Autowired
    IAccountRepository iAccountRepo;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !iAccountRepo.existsByUsername(value);
    }
}
