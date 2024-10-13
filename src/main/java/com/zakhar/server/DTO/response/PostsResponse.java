package com.zakhar.server.DTO.response;

import com.zakhar.server.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PostsResponse {
    private Long id;
    private String title;
    private Integer reaction;
    private String description;
    private LocalDateTime createDate;
    private UserDTO user;
    private List<CommentsResponse> comments;
}
