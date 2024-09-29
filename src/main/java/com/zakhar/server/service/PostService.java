package com.zakhar.server.service;


import com.zakhar.server.entity.Posts;
import com.zakhar.server.entity.Users;
import com.zakhar.server.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public List<Posts> getAll() {
        return postRepository.findAll();
    }

    public Posts create(String title, String description, Principal principal) {
        Users currentUser = userService.getByPrincipal(principal);
        Posts post = Posts.builder()
                .title(title)
                .description(description)
                .reaction(0)
                .createDate(LocalDateTime.now())
                .user(currentUser)
                .build();
        return postRepository.save(post);
    }

    public Posts get(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Post not found: " + id)
        );
    }
}
