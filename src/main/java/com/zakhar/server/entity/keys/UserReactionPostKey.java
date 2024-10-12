package com.zakhar.server.entity.keys;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserReactionPostKey implements Serializable {
    @Column(name = "post_id")
    Long postId;
    @Column(name = "user_id")
    Long userId;
}
