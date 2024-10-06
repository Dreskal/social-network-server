package com.zakhar.server.DTO.responce;

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
    private String description;
    private LocalDateTime createDate;
    private UserDTO user;
    private List<CommentsResponse> comments;
}
