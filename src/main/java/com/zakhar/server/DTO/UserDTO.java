package com.zakhar.server.DTO;

import com.zakhar.server.entity.enums.ERole;
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
public class UserDTO {

    private Long id;
    private String username;
    private LocalDateTime createDate;
    private List<ERole> role;

}
