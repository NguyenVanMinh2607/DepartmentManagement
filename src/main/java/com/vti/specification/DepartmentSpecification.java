package com.vti.specification;

import com.vti.entities.Department;
import com.vti.form.DepartmentFilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class DepartmentSpecification {
    public static Specification<Department> buildSpec(DepartmentFilterForm form) {
        if(form == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> {
            // Predicate là hàm chứa các điều kiện
            final List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.hasText(form.getSearch())) {
                String pattern = "%" + form.getSearch().trim() + "%";
                predicates.add(
                        // add để thêm điều kiện vào Predicate
                        criteriaBuilder.like(root.get("name"), pattern)
                );
            }
            if(form.getMinTotalMembers() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("totalMembers"),
                        form.getMinTotalMembers()
                ));
            }
            if(form.getMaxTotalMembers() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("totalMembers"),
                        form.getMaxTotalMembers()
                ));
            }

            if (form.getMinCreateAt() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("createdAt"),
                        form.getMinCreateAt()
                ));
            }
            if (form.getMaxCreateAt() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("createdAt"),
                        form.getMaxCreateAt()
                ));
            }

            if(form.getMinCreatedYear() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        // Call 1 Function
                        criteriaBuilder.function("YEAR", Integer.class, root.get("createdAt")),
                        form.getMinCreatedYear()
                ));
            }

            if(form.getType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("type"),form.getType()));
            }
            // toArray để convert from List to Array vì and chỉ chấp nhận Array
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
