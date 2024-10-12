package com.zakhar.server.entity;


import com.zakhar.server.entity.keys.UserReactionPostKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserReactionPost {

    @EmbeddedId
    private UserReactionPostKey id;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "post_id")
    Posts post;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    Users user;

    @Column(name = "is_like")
    private Boolean like;
}
