package com.ekros.libraryspring.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Table(name = "users")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name", length = 40, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 40, nullable = false)
    private String lastName;
    @Column(length = 50, nullable = false, unique = true)
    private String email;
    @Column(length = 120, nullable = false)
    private String password;
    @Column(nullable = false)
    private Date birthday;
    @Column(length = 15, nullable = false)
    private String phone;
    @Column(nullable = false)
    private Role role;
    @Column(nullable = false)
    private boolean block;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean enable;
    @OneToMany(mappedBy = "user")
    private Set<Order> orders;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Token token;

    public String getFullName(){
        return firstName + " " + lastName;
    }

}