package com.highthon.highthon3server.controller;

import com.highthon.highthon3server.domain.application.Application;
import com.highthon.highthon3server.domain.application.ApplicationCondition;
import com.highthon.highthon3server.domain.application.ApplicationIncludesWaitingNumber;
import com.highthon.highthon3server.dto.application.ApplicationSaveDto;
import com.highthon.highthon3server.dto.application.GetApplicationConditionDto;
import com.highthon.highthon3server.dto.application.SaveResponse;
import com.highthon.highthon3server.service.application.ApplicationService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@NoArgsConstructor
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/apply")
    public ResponseEntity<SaveResponse> apply(@Valid @RequestBody ApplicationSaveDto dto) {
        return new ResponseEntity<SaveResponse>(applicationService.saveApplication(dto), HttpStatus.CREATED);
    }

    @PostMapping("/apply/status")
    public ResponseEntity<ApplicationCondition> getApplicationCondition(@Valid @RequestBody GetApplicationConditionDto dto) {
        return new ResponseEntity<ApplicationCondition>(applicationService.getApplicationCondition(dto), HttpStatus.OK);
    }

    @GetMapping("/applications/accepted")
    public List<Application> getAcceptedApplications(Pageable pageable) {
        return applicationService.getAcceptedApplications(pageable);
    }

    @GetMapping("/applications/waiting")
    public List<ApplicationIncludesWaitingNumber> getWaitingApplications(Pageable pageable) {
        return applicationService.getWaitingApplications(pageable);
    }

    @DeleteMapping("/applications/{applicationId}")
    public void deleteApplication(@PathVariable("applicationId") Long applicationId) {
        applicationService.deleteApplicationAndUpdateLatestWaitingApplicationToAccepted(applicationId);
    }
}
