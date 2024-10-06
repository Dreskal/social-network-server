package com.zakhar.server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zakhar.server.entity.enums.ERole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 32)
    private String username;
    @Column(nullable = false, length = 3000)
    private String password;
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss" )
    @Column(updatable = false )
    private LocalDateTime createDate;

    @ElementCollection(targetClass = ERole.class, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"))
    private List<ERole> role;

    @OneToOne
    @JoinColumn(name = "contacts_id", referencedColumnName = "id")
    private Contacts contacts;

    @OneToMany(mappedBy = "user")
    private List<Applications> applications;

    @OneToMany(mappedBy = "user")
    private List<Posts> posts;

    @OneToMany(mappedBy = "user")
    private List<Comments> comments;

    @OneToMany(mappedBy = "user")
    private List<UserReactionPost> reactionPosts;
}
