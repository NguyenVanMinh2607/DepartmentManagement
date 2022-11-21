package com.vti.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 50, unique = true, nullable = false)
    private String name;

    @Column(name = "total_members", nullable = false)
    private Integer totalMembers;

    @Column(name = "type", length = 15, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Type type;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // cascade khi 1 phòng ban bị xoá thì toàn bộ account trong phòng ban đấy cx bị xoá theo
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Account> accounts;

    // inner Class
    public enum Type {
        DEVELOPER, TESTER, SCRUM_MASTER, PROJECT_MANAGER
    }
}
