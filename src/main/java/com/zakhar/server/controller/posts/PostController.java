package com.zakhar.server.controller.posts;


import com.zakhar.server.DTO.request.CommentRequest;
import com.zakhar.server.DTO.request.PostsCreateRequest;
import com.zakhar.server.DTO.response.CommentsResponse;
import com.zakhar.server.DTO.response.PostsResponse;
import com.zakhar.server.entity.Comments;
import com.zakhar.server.entity.Posts;
import com.zakhar.server.mapper.PostResponseMapper;
import com.zakhar.server.service.CommentService;
import com.zakhar.server.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/v1/posts")
@RestController
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final ModelMapper modelMapper;
    private final PostResponseMapper postResponseMapper;

    @PostMapping
    public ResponseEntity<PostsResponse> create(@RequestBody PostsCreateRequest request,
                                                Principal principal) {
        Posts post = postService.create(
                request.getTitle(),
                request.getDescription(),
                principal
        );
        return new ResponseEntity<>(postResponseMapper.map(post),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentsResponse> create(@RequestBody CommentRequest request,
                                                   Principal principal) {
        Comments comments = commentService.create(
                request.getDescription(),
                request.getPostId(),
                principal
        );
        return new ResponseEntity<>(map(comments),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<PostsResponse>> getAllPosts() {
        List<PostsResponse> posts =
                postService.getAll()
                        .stream()
                        .map(postResponseMapper::map)
                        .toList();
        return ResponseEntity.ok(posts);
    }

    @PatchMapping("/{id}/reaction")
    public ResponseEntity<PostsResponse> reaction(@PathVariable Long id,
                                                  Principal principal){
        Posts posts = postService.reaction(id, principal);
        return ResponseEntity.ok(postResponseMapper.map(posts));
    }

    public CommentsResponse map(Comments comments){
        return modelMapper.map(comments, CommentsResponse.class);
    }
}
