package org.example.educationsystem.repository;

import org.example.educationsystem.entity.ScheduleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findAllBySchoolClass_IdAndDateOrderByStartTime(Long schoolClassId, LocalDate date, Pageable pageable);

    List<ScheduleEntity> findAllByTeacher_IdAndDateOrderByStartTime(Long teacherId, LocalDate date, Pageable pageable);
}
