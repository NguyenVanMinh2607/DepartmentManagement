package com.vti.validation;

import com.vti.repository.IDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DepartmentNameNotExistsValidator implements ConstraintValidator<DepartmentNameNotExists, String> {

    @Autowired
    private IDepartmentRepository iDepartmentRepo;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return !iDepartmentRepo.existsByName(name);
    }
}
