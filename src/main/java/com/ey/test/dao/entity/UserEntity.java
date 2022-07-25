package com.ey.test.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_app")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {
    @Id
    @Column(unique = true)
    private String id;
    private String token;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private LocalDateTime lastLogin;
    private LocalDateTime created;
    private LocalDateTime modified;
    private boolean isActive;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<PhoneEntity> phones;
}
