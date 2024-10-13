package com.zakhar.server.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class AuthenticationResponse {

    private String username;
    private String password;

}
