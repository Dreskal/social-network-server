package com.zakhar.server.DTO.responce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class AuthenticationResponce {

    private String username;
    private String password;

}
