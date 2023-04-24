package com.avanade.rpgbattle.repository;

import com.avanade.rpgbattle.model.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISampleRepository extends JpaRepository<Sample, Long > {
}
