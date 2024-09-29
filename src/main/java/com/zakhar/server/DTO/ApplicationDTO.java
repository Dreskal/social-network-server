package com.zakhar.server.DTO;


import com.zakhar.server.entity.enums.EState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ApplicationDTO {
    private Long id;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime dueDate;
    private EState state;
    private UserDTO user;

}
