package com.zakhar.server.service;


import com.zakhar.server.entity.Comments;
import com.zakhar.server.entity.Posts;
import com.zakhar.server.entity.Users;
import com.zakhar.server.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;

    public List<Comments> getAll(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comments create(String description, Long postId, Principal principal) {

        Users currentUser = userService.getByPrincipal(principal);
        Posts currentPost = postService.get(postId);

        Comments comments = Comments.builder()
                .description(description)
                .reaction(0)
                .createDate(LocalDateTime.now())
                .user(currentUser)
                .post(currentPost)
                .build();

        return commentRepository.save(comments);
    }
}
