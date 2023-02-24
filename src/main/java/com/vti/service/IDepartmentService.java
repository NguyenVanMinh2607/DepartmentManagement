package com.vti.service;

import com.vti.entities.Department;
import com.vti.form.DepartmentCreateForm;
import com.vti.form.DepartmentFilterForm;
import com.vti.form.DepartmentUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDepartmentService {
    Page<Department> findAll(Pageable pageable, DepartmentFilterForm form);
    // Page : trả về dữ liệu của 1 trang
    // Pageable : trả về kích thước của phần trang

    Department findById(int id);

//    Department findByName(String name);

    void create(DepartmentCreateForm form);

    void update(DepartmentUpdateForm form);

    void deleteAllById(List<Integer> ids);

    boolean existsById(int id);

//    void deleteByName(String name);
}
