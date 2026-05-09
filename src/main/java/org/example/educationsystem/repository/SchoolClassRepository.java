package org.example.educationsystem.repository;


import org.example.educationsystem.entity.SchoolClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolClassRepository extends JpaRepository<SchoolClassEntity, Long> {

    Optional<SchoolClassEntity> findByClassName(String className);
}
