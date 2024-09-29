package com.zakhar.server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zakhar.server.entity.enums.EState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Entity
public class Applications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String description;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss" )
    @Column(updatable = false )
    private LocalDateTime createDate;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss" )
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    private EState state;

    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private Users user;


}
