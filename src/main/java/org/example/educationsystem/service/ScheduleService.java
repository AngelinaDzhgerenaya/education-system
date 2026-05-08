package org.example.educationsystem.service;

import lombok.AllArgsConstructor;
import org.example.educationsystem.dto.request.create.CreateScheduleRequest;
import org.example.educationsystem.dto.request.edit.EditScheduleRequest;
import org.example.educationsystem.dto.request.edit.EditSchoolClassRequest;
import org.example.educationsystem.dto.request.edit.EditTeacherRequest;
import org.example.educationsystem.dto.response.DayScheduleResponse;
import org.example.educationsystem.dto.response.ScheduleResponse;
import org.example.educationsystem.dto.response.SchoolClassResponse;
import org.example.educationsystem.dto.response.TeacherResponse;
import org.example.educationsystem.entity.ScheduleEntity;
import org.example.educationsystem.entity.SchoolClassEntity;
import org.example.educationsystem.entity.TeacherEntity;
import org.example.educationsystem.repository.ScheduleRepository;
import org.example.educationsystem.repository.SchoolClassRepository;
import org.example.educationsystem.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final TeacherRepository teacherRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleEntity create(CreateScheduleRequest request){
        ScheduleEntity schedule = request.entity();
        DayOfWeek dayOfWeek = request.getDate().getDayOfWeek();
        Optional<TeacherEntity> teacher = teacherRepository.findById(request.getTeacherId());
        Optional<SchoolClassEntity> schoolClass = schoolClassRepository.findById(request.getSchoolClassId());
        schedule.setDayOfWeek(dayOfWeek);
        schedule.setTeacher(teacher.get());
        schedule.setSchoolClass(schoolClass.get());

        schedule = scheduleRepository.save(schedule);
        return schedule;
    }


    public DayScheduleResponse schoolClassSchedule(Long id, LocalDate date){

        DayOfWeek dayOfWeek = date.getDayOfWeek();

        List<ScheduleEntity> schedules = scheduleRepository.findAllBySchoolClass_IdAndDateOrderByStartTime(id, date);
        List<ScheduleResponse> responseList = schedules.stream().map(ScheduleResponse::of).toList();


        return DayScheduleResponse.of(dayOfWeek.name(), responseList);

    }
    public DayScheduleResponse teacherSchedule(Long id, LocalDate date){

        DayOfWeek dayOfWeek = date.getDayOfWeek();

        List<ScheduleEntity> schedules = scheduleRepository.findAllByTeacher_IdAndDateOrderByStartTime(id, date);
        List<ScheduleResponse> responseList = schedules.stream().map(ScheduleResponse::of).toList();


        return DayScheduleResponse.of(dayOfWeek.name(), responseList);

    }

    public ScheduleResponse updateSchedule(Long id, EditScheduleRequest request){
        ScheduleEntity schedule = scheduleRepository.findById(id)
                .orElseThrow();

        if (request.getTeacherId() != null) {
            TeacherEntity teacher = teacherRepository
                    .findById(request.getTeacherId())
                    .orElseThrow();
            schedule.setTeacher(teacher);
        }

        if (request.getSchoolClassId() != null) {
            SchoolClassEntity schoolClass = schoolClassRepository
                    .findById(request.getSchoolClassId())
                    .orElseThrow();
            schedule.setSchoolClass(schoolClass);
        }

        if (request.getSubject() != null) {
            schedule.setSubject(request.getSubject());
        }

        if (request.getDate() != null) {
            schedule.setDate(request.getDate());
            DayOfWeek dayOfWeek = request.getDate().getDayOfWeek();
            schedule.setDayOfWeek(dayOfWeek);
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
