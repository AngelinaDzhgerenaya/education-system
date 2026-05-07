package org.example.educationsystem.repository;

import org.example.educationsystem.entity.SchoolClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolClassRepository extends JpaRepository<SchoolClassEntity, Long> {
}
