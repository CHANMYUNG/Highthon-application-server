package com.highthon.highthon3server.controller;

import com.highthon.highthon3server.dto.application.ApplicationSaveDto;
import com.highthon.highthon3server.dto.application.SaveResponse;
import com.highthon.highthon3server.service.ApplicationService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/apply")
    public ResponseEntity<SaveResponse> apply(ApplicationSaveDto dto) {
        return new ResponseEntity<SaveResponse>(applicationService.saveApplication(dto), HttpStatus.CREATED);
    }
}
