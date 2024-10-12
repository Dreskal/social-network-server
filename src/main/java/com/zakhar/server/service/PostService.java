package com.zakhar.server.service;


import com.zakhar.server.entity.Posts;
import com.zakhar.server.entity.UserReactionPost;
import com.zakhar.server.entity.Users;
import com.zakhar.server.entity.keys.UserReactionPostKey;
import com.zakhar.server.repository.PostRepository;
import com.zakhar.server.repository.UserReactionPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserReactionPostRepository userReactionPostRepository;
    private final UserService userService;

    public List<Posts> getAll() {
        return postRepository.findAll();
    }

    public Posts create(String title, String description, Principal principal) {
        Users currentUser = userService.getByPrincipal(principal);
        Posts post = Posts.builder()
                .title(title)
                .description(description)
                .createDate(LocalDateTime.now())
                .user(currentUser)
                .build();
        return postRepository.save(post);
    }

    public Posts reaction(Long id, Principal principal){

        Users currentUser = userService.getByPrincipal(principal);
        Posts post = get(id);

        UserReactionPostKey userReactionPostKey = new UserReactionPostKey(
                post.getId(),
                currentUser.getId()
        );

        UserReactionPost userReactionPost = userReactionPostRepository
                .findById(userReactionPostKey)
                .orElse(new UserReactionPost(userReactionPostKey, post, currentUser, false));


        userReactionPost.setLike(!userReactionPost.getLike());

        userReactionPostRepository.save(userReactionPost);

        return post;
    }

    public Posts get(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Post not found: " + id)
        );
    }
}
