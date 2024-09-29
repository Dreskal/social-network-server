package com.zakhar.server.controller;


import com.zakhar.server.DTO.ApplicationDTO;
import com.zakhar.server.DTO.request.ApplicationRequest;
import com.zakhar.server.entity.Applications;
import com.zakhar.server.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ApplicationDTO> create (@RequestBody ApplicationRequest request,
                                                  Principal principal){
        Applications applications = applicationService.create(request, principal);
        return new ResponseEntity<>(map(applications),
                HttpStatus.CREATED
        );
    }

    public ApplicationDTO map(Applications applications){
        return modelMapper.map(applications, ApplicationDTO.class);
    }
}
