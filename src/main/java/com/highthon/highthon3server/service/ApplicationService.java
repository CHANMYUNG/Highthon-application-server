package com.highthon.highthon3server.service;

import com.highthon.highthon3server.domain.application.Application;
import com.highthon.highthon3server.domain.application.ApplicationRepository;
import com.highthon.highthon3server.domain.application.Area;
import com.highthon.highthon3server.domain.application.Position;
import com.highthon.highthon3server.dto.application.ApplicationSaveDto;
import com.highthon.highthon3server.dto.application.SaveResponse;
import com.highthon.highthon3server.exception.DuplicateValueException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    private static final Integer GAME_DESIGN_LIMIT = 10;
    private static final Integer GAME_DEVELOP_LIMIT = 30;
    private static final Integer LIFE_DESIGN_LIMIT = 15;
    private static final Integer LIFE_DEVELOP_LIMIT = 45;

    @Transactional
    public SaveResponse saveApplication(ApplicationSaveDto dto) {
        SaveResponse response = null;

        if (applicationRepository.countByPhone(dto.getPhone()) > 0)
            throw new DuplicateValueException("phone");
        if (applicationRepository.countByEmail(dto.getEmail()) > 0)
            throw new DuplicateValueException("email");

        int limit = getLimit(dto.getArea(), dto.getPosition());
        int count = applicationRepository.countByAreaAndPosition(dto.getArea(), dto.getPosition());

        Application application = dto.toEntity();

        if (count < limit) {
            application.setIsAccepted(true);
            applicationRepository.save(application);
            response = new SaveResponse(true, null);
        } else {
            application.setIsAccepted(false);
            applicationRepository.save(application);
            Integer waitingNumber = applicationRepository.getWaitingNumber(application.getCreatedDate());
            response = new SaveResponse(false, waitingNumber);
        }

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
}