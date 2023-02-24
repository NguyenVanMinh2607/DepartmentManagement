package com.vti.validation;

import com.vti.repository.IDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DepartmentIdExistsValidator implements ConstraintValidator<DepartmentIdExists, Integer> {

    @Autowired
    private IDepartmentRepository iDepartmentRepo;

    @SuppressWarnings("deprecation")
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }
        return iDepartmentRepo.existsById(value);
    }
}
