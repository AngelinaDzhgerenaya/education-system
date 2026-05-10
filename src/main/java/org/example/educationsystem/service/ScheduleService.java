package org.example.educationsystem.service;

import lombok.AllArgsConstructor;
import org.example.educationsystem.dto.request.create.CreateScheduleRequest;
import org.example.educationsystem.dto.request.edit.EditScheduleRequest;
import org.example.educationsystem.dto.response.DayScheduleResponse;
import org.example.educationsystem.dto.response.ScheduleResponse;
import org.example.educationsystem.entity.ScheduleEntity;
import org.example.educationsystem.entity.SchoolClassEntity;
import org.example.educationsystem.entity.TeacherEntity;
import org.example.educationsystem.exception.NotFoundException;
import org.example.educationsystem.repository.ScheduleRepository;
import org.example.educationsystem.repository.SchoolClassRepository;
import org.example.educationsystem.repository.TeacherRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final TeacherRepository teacherRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleEntity create(CreateScheduleRequest request)throws NotFoundException{


        ScheduleEntity schedule = request.entity();
        DayOfWeek dayOfWeek = request.getDate().getDayOfWeek();
        TeacherEntity teacher = teacherRepository.findById(request.getTeacherId()
        ).orElseThrow(() ->
                new NotFoundException("Учитель не найден")
        );
        SchoolClassEntity schoolClass = schoolClassRepository.findById(request.getSchoolClassId()).orElseThrow(() ->
                        new NotFoundException("Класс не найден")
                );
        schedule.setTeacher(teacher);
        schedule.setSchoolClass(schoolClass);

        schedule = scheduleRepository.save(schedule);
        return schedule;
    }


    public DayScheduleResponse schoolClassSchedule(Long id, LocalDate date, int page, int size) throws NotFoundException {
        SchoolClassEntity schoolClass = schoolClassRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Класс не найден")
        );
        Pageable pageable = PageRequest.of(page, size);

        DayOfWeek dayOfWeek = date.getDayOfWeek();


        List<ScheduleEntity> schedules = scheduleRepository.findAllBySchoolClass_IdAndDateOrderByStartTime(id, date,pageable);
        List<ScheduleResponse> responseList = schedules.stream().map(ScheduleResponse::of).toList();


        return DayScheduleResponse.of(dayOfWeek.name(), responseList);

    }
    public DayScheduleResponse teacherSchedule(Long id, LocalDate date, int page, int size) throws NotFoundException {
        TeacherEntity teacher = teacherRepository.findById(id
        ).orElseThrow(() ->
                new NotFoundException("Учитель не найден")
        );

        Pageable pageable = PageRequest.of(page, size);

        DayOfWeek dayOfWeek = date.getDayOfWeek();

        List<ScheduleEntity> schedules = scheduleRepository.findAllByTeacher_IdAndDateOrderByStartTime(id, date, pageable);
        List<ScheduleResponse> responseList = schedules.stream().map(ScheduleResponse::of).toList();


        return DayScheduleResponse.of(dayOfWeek.name(), responseList);

    }

    public ScheduleResponse updateSchedule(Long id, EditScheduleRequest request) throws NotFoundException {

        ScheduleEntity schedule = scheduleRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Урок не найден")
        );

        if (request.getTeacherId() != null) {
            TeacherEntity teacher = teacherRepository.findById(request.getTeacherId()
            ).orElseThrow(() ->
                    new NotFoundException("Учитель не найден")
            );
            schedule.setTeacher(teacher);
        }

        if (request.getSchoolClassId() != null) {
            SchoolClassEntity schoolClass = schoolClassRepository.findById(request.getSchoolClassId()).orElseThrow(() ->
                    new NotFoundException("Класс не найден")
            );
            schedule.setSchoolClass(schoolClass);
        }

        if (request.getSubject() != null) {
            schedule.setSubject(request.getSubject());
        }

        if (request.getDate() != null) {
            schedule.setDate(request.getDate());
        }
        if (request.getRoomNumber() != null) {
            schedule.setRoomNumber(request.getRoomNumber());
        }

        if (request.getStartTime() != null) {
            schedule.setStartTime(request.getStartTime());
        }

        if (request.getEndTime() != null) {
            schedule.setEndTime(request.getEndTime());
        }

        return ScheduleResponse.of(scheduleRepository.save(schedule));
    }


}
