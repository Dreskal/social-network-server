package com.zakhar.server.mapper;

import com.zakhar.server.DTO.UserDTO;
import com.zakhar.server.DTO.response.CommentsResponse;
import com.zakhar.server.DTO.response.PostsResponse;
import com.zakhar.server.entity.Comments;
import com.zakhar.server.entity.Posts;
import com.zakhar.server.entity.UserReactionPost;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;

import java.util.LinkedList;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class PostResponseMapper {

    private final ModelMapper modelMapper;

    public PostsResponse map(Posts posts) {

        List<UserReactionPost> userReactionPosts = posts.getReactionPosts();

        int reaction = 0;

        if(userReactionPosts !=null){
            reaction = (int) userReactionPosts
                    .stream()
                    .filter(UserReactionPost::getLike)
                    .count();
        }

        List<Comments> comments = posts.getComments();
        List<CommentsResponse> commentsResponse = new LinkedList<>();

        if(userReactionPosts !=null){
             commentsResponse = posts.getComments()
                    .stream()
                    .map(comment -> modelMapper.map(comment, CommentsResponse.class))
                    .toList();
        }


        return PostsResponse.builder()
                .id(posts.getId())
                .title(posts.getTitle())
                .description(posts.getDescription())
                .reaction(reaction)
                .createDate(posts.getCreateDate())
                .comments(commentsResponse)
                .user(modelMapper.map(posts.getUser(), UserDTO.class))
                .build();
    }

}
