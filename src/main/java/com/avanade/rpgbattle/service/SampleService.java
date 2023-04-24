package com.avanade.rpgbattle.service;

import com.avanade.rpgbattle.repository.ISampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleService {
    @Autowired
    private ISampleRepository repository;
}
