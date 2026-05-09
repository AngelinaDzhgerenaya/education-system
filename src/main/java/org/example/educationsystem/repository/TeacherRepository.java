package org.example.educationsystem.repository;

import org.example.educationsystem.entity.SchoolClassEntity;
import org.example.educationsystem.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

    Optional<TeacherEntity> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<TeacherEntity> findByFirstNameAndLastNameAndMiddleName(String firstName, String lastName, String middleName);
}
