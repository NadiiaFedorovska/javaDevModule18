package com.example.JavaDevModule18.security.user;

import lombok.Data;

import javax.persistence.*;

@Table(name = "users")
@Entity()
@Data
public class User {
    @SequenceGenerator(name = "user_id",sequenceName = "user_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id")
    @Id
    private long userId;
    private String username;
    private String password;
    private String role;
    private short enabled;
}
