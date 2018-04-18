package com.highthon.highthon3server.service.application;

import com.highthon.highthon3server.domain.application.*;
import com.highthon.highthon3server.dto.application.ApplicationSaveDto;
import com.highthon.highthon3server.dto.application.ApplicationConditionDto;
import com.highthon.highthon3server.dto.application.SaveResponse;
import com.highthon.highthon3server.exception.ApplicationNotFoundException;
import com.highthon.highthon3server.exception.AuthenticationException;
import com.highthon.highthon3server.exception.DuplicatedValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final Integer GAME_DESIGN_LIMIT = 10;
    private static final Integer GAME_DEVELOP_LIMIT = 30;
    private static final Integer LIFE_DESIGN_LIMIT = 15;
    private static final Integer LIFE_DEVELOP_LIMIT = 45;

    @Transactional
    public SaveResponse saveApplication(ApplicationSaveDto dto) {
        SaveResponse response = null;
        Integer waitingNumber = null;
        if (applicationRepository.countByPhone(dto.getPhone()) > 0)
            throw new DuplicatedValueException("phone");
        if (applicationRepository.countByEmail(dto.getEmail()) > 0)
            throw new DuplicatedValueException("email");

        int limit = getLimit(dto.getArea(), dto.getPosition());
        int count = applicationRepository.countByAreaAndPosition(dto.getArea(), dto.getPosition());

        Application application = dto.toEntity();
        application.setPassword(passwordEncoder.encode(application.getPassword()));
        application.setBelong(application.getBelong().replace(" ", ""));

        if (count < limit) {
            application.setIsAccepted(true);
        } else {
            application.setIsAccepted(false);
            waitingNumber = applicationRepository.getWaitingNumber(application.getCreatedDate());
        }

        applicationRepository.save(application);
        response = new SaveResponse(true, waitingNumber);

        return response;
    }

    private int getLimit(Area area, Position position) {
        if (area == Area.GAME) {
            if (position == Position.DESIGN) return GAME_DESIGN_LIMIT;
            else return GAME_DEVELOP_LIMIT;
        } else {
            if (position == Position.DESIGN) return LIFE_DESIGN_LIMIT;
            else return LIFE_DEVELOP_LIMIT;
        }
    }

    @Transactional
    public ApplicationCondition getApplicationCondition(ApplicationConditionDto dto) {
        Application application = applicationRepository.findByEmail(dto.getEmail()).orElse(null);
        if (application == null) throw new ApplicationNotFoundException();
        if (!passwordEncoder.matches(dto.getPassword(), application.getPassword()))
            throw new AuthenticationException("password does not match");

        ApplicationCondition condition = applicationRepository.getApplicationConditionById(application.getApplicationId());
        if (condition == null) throw new ApplicationNotFoundException();
        else return condition;
    }
}
