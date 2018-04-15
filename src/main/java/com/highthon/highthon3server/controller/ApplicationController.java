package com.highthon.highthon3server.controller;

import com.highthon.highthon3server.domain.application.ApplicationCondition;
import com.highthon.highthon3server.dto.application.ApplicationSaveDto;
import com.highthon.highthon3server.dto.application.ApplicationConditionDto;
import com.highthon.highthon3server.dto.application.SaveResponse;
import com.highthon.highthon3server.service.application.ApplicationService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@NoArgsConstructor
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/apply")
    public ResponseEntity<SaveResponse> apply(@RequestBody ApplicationSaveDto dto) {
        return new ResponseEntity<SaveResponse>(applicationService.saveApplication(dto), HttpStatus.CREATED);
    }

    @GetMapping("/apply/status")
    public ResponseEntity<ApplicationCondition> getApplicationCondition(@ModelAttribute ApplicationConditionDto dto) {
        return new ResponseEntity<ApplicationCondition>(applicationService.getApplicationCondition(dto), HttpStatus.OK);
    }
}
