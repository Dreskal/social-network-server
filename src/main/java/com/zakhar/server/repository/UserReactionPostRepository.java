package com.zakhar.server.repository;

import com.zakhar.server.entity.Posts;
import com.zakhar.server.entity.UserReactionPost;
import com.zakhar.server.entity.keys.UserReactionPostKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReactionPostRepository extends JpaRepository<UserReactionPost, UserReactionPostKey> {
}
