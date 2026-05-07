package org.example.educationsystem.repository;

import org.example.educationsystem.entity.SchoolClassEntity;
import org.example.educationsystem.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
}
