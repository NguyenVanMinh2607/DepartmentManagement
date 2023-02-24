package com.vti.specification;

import com.vti.entities.Account;
import com.vti.form.AccountFilterFrom;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class AccountSpecification {
    public static Specification<Account> buildSpec(AccountFilterFrom form) {
        if(form == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.hasText(form.getSearch())) {
                String pattern = "%" + form.getSearch().trim() + "%";
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(root.get("username"), pattern),
                        // Neseted Atribute
                        criteriaBuilder.like(root.get("department").get("name"), pattern)
                ));
            }
            if(form.getMaxId() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("id"),
                        form.getMaxId()
                ));
            }
            if(form.getMinId() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("id"),
                        form.getMinId()
                ));
            }

            if(form.getRole() != null) {
                predicates.add(criteriaBuilder.equal(root.get("role"),form.getRole()));
            }

            // toArray để convert from List to Array vì and chỉ chấp nhận Array
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
