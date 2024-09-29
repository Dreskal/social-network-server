package com.zakhar.server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "contacts")
    private Users user;

    @Column(length = 16)
    private String name;

    private String email;
    private boolean licence;
    private String country;
    private String bio;
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss" )
    private LocalDateTime birthDate;

    public Contacts(String name, String email, boolean licence) {
        this.name = name;
        this.email = email;
        this.licence = licence;
    }
}
