package com.avanade.rpgbattle.service;

import com.avanade.rpgbattle.repository.ISampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class SampleService {
    @Autowired
    private ISampleRepository repository;
}
