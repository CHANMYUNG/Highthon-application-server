package com.highthon.highthon3server.service;

import com.highthon.highthon3server.domain.application.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

}
