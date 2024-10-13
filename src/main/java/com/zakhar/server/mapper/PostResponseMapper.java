package com.zakhar.server.mapper;

import com.zakhar.server.DTO.UserDTO;
import com.zakhar.server.DTO.response.CommentsResponse;
import com.zakhar.server.DTO.response.PostsResponse;
import com.zakhar.server.entity.Posts;
import com.zakhar.server.entity.UserReactionPost;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class PostResponseMapper {

    private final ModelMapper modelMapper;

    public PostsResponse map(Posts posts) {

        int reaction = (int) posts.getReactionPosts()
                .stream()
                .filter(UserReactionPost::getLike)
                .count();

        List<CommentsResponse> commentsResponse = posts.getComments()
                .stream()
                .map(comment -> modelMapper.map(comment, CommentsResponse.class))
                .toList();

        return PostsResponse.builder()
                .id(posts.getId())
                .title(posts.getTitle())
                .reaction(reaction)
                .createDate(posts.getCreateDate())
                .comments(commentsResponse)
                .user(modelMapper.map(posts.getUser(), UserDTO.class))
                .build();
    }

}
