package com.vti.repository;

import com.vti.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDepartmentRepository extends JpaRepository<Department, Integer>, JpaSpecificationExecutor<Department> {
    // Sử dụng HQL theo chỉ số k cần @Param
    // @Query("FROM Department WHERE name = ?1")
    // Department finByName(String name);

    // Sử dụng HQL theo tên phải sử dụng @Param
    // @Query("FROM Department WHERE name = :name")
    // Department finByName(@Param("name") String name);

    // Sử dụng SQL
    // @Query(value = "SELECT * FROM department WHERE name = :name", nativeQuery = true)

    // Sử dụng Custom query
    Department findByName(String name);

    List<Department> findByTotalMembersGreaterThanEqual(int minTotalMembers);

    List<Department> findByNameAndTotalMembersGreaterThanEqualAndTotalMembersLessThanEqual(
            String name,
            int minTotalMembers,
            int maxTotalMembers
    );

//    @Modifying // Sử dụng Delete, Update phải thêm modifying
    @Query("DELETE FROM Department WHERE name = ?1")
    void deleteByName(String name);

    boolean existsByName(String name);
}