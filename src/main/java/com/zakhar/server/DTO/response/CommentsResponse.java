package com.zakhar.server.DTO.response;

import com.zakhar.server.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CommentsResponse {
    private Long id;
    private String description;
    private Integer reaction;
    private LocalDateTime createDate;
    private UserDTO user;
}
