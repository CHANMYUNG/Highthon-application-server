package com.highthon.highthon3server.controller;

import com.highthon.highthon3server.domain.application.Application;
import com.highthon.highthon3server.domain.application.ApplicationCondition;
import com.highthon.highthon3server.domain.application.ApplicationIncludesWaitingNumber;
import com.highthon.highthon3server.dto.application.ApplicationSaveDto;
import com.highthon.highthon3server.dto.application.GetApplicationConditionDto;
import com.highthon.highthon3server.service.application.ApplicationService;
import io.swagger.annotations.ApiOperation;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@NoArgsConstructor
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @ApiOperation("하이톤 참가 신청")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "area", value = "참가 분야 (생활/게임)", required = true, dataType = "string", allowableValues = "LIFE, GAME", paramType = "body", example = "LIFE"),
//            @ApiImplicitParam(name = "belong", value = "소속(학교) -등학교 형식", required = true, dataType = "string", paramType = "body", example = "대덕소프트웨어마이스터고등학교"),
//            @ApiImplicitParam(name = "email", value = "이메일", required = true, dataType = "string", paramType = "body", example = "nooheat1228@gmail.com"),
//            @ApiImplicitParam(name = "sex", value = "성별", required = true, dataType = "string", paramType = "body", allowableValues = "MAN, WOMAN", example = "MAN"),
//            @ApiImplicitParam(name = "phone", value = "전화번호 000-0000-0000 형식", required = true, dataType = "string", paramType = "body", example = "000-0000-0000"),
//            @ApiImplicitParam(name = "position", value = "포지션 (개발자/디자이너)", required = true, dataType = "string", paramType = "body", allowableValues = "DEVELOP, DESIGN", example = "DEVELOP"),
//            @ApiImplicitParam(name = "name", value = "이름", required = true, dataType = "string", paramType = "body", example = "윤찬명"),
//            @ApiImplicitParam(name = "password", value = "비밀번호", required = true, dataType = "string", paramType = "body", example = "thisispassword")
//    })
    @PostMapping("/apply")
    @ResponseStatus(HttpStatus.CREATED)
    public void apply(/*@ApiIgnore*/ @Valid @RequestBody ApplicationSaveDto dto) {
//        return new ResponseEntity<SaveResponse>(applicationService.saveApplication(dto), HttpStatus.CREATED);
        applicationService.saveApplication(dto);
    }

    @ApiOperation("하이톤 참가 신청 상태 조회")
    @PostMapping("/apply/status")
    public ResponseEntity<ApplicationCondition> getApplicationCondition(@Valid @RequestBody GetApplicationConditionDto dto) {
        return new ResponseEntity<ApplicationCondition>(applicationService.getApplicationCondition(dto), HttpStatus.OK);
    }

    @ApiOperation("접수된 하이톤 참가 신청 목록 조회")
    @GetMapping("/applications/accepted")
    public List<Application> getAcceptedApplications(Pageable pageable) {
        return applicationService.getAcceptedApplications(pageable);
    }

    @ApiOperation("대기중 하이톤 참가 신청 목록 조회")
    @GetMapping("/applications/waiting")
    public List<ApplicationIncludesWaitingNumber> getWaitingApplications(Pageable pageable) {
        return applicationService.getWaitingApplications(pageable);
    }

    @ApiOperation("하이톤 참가 신청 삭제")
    @DeleteMapping("/applications/{applicationId}")
    public void deleteApplication(@PathVariable("applicationId") Long applicationId) {
        applicationService.deleteApplicationAndUpdateLatestWaitingApplicationToAccepted(applicationId);
    }
}
