package com.ekros.libraryspring.model.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "tokens")
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class Token {

    @Column(name = "uuid")
    private String uuid;

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "time")
    private Timestamp time;

    public static Token create(String uuid, User user) {
        return new Token(uuid, user.getId(), user, new Timestamp(System.currentTimeMillis()));
    }
}