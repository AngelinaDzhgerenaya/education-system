package org.example.educationsystem.controller;


import lombok.RequiredArgsConstructor;
import org.example.educationsystem.dto.response.ScheduleResponse;
import org.example.educationsystem.dto.response.SchoolClassResponse;
import org.example.educationsystem.entity.SchoolClassEntity;
import org.example.educationsystem.entity.TeacherEntity;
import org.example.educationsystem.repository.ScheduleRepository;
import org.example.educationsystem.repository.SchoolClassRepository;
import org.example.educationsystem.repository.TeacherRepository;
import org.example.educationsystem.routes.EducationRoutes;
import org.example.educationsystem.service.EducationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EducationApiController {
    private final TeacherRepository teacherRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final ScheduleRepository scheduleRepository;

    private final EducationService educationService;


    @GetMapping(EducationRoutes.INIT)
    public Boolean init() {

        return educationService.init();
    }

    @PostMapping(EducationRoutes.TEACHERS)
    public TeacherEntity teacher(@RequestBody TeacherEntity teacherEntity) {
        return teacherRepository.save(teacherEntity);
    }

    @GetMapping(EducationRoutes.TEACHERS)
    public List<TeacherEntity> teachers() {

        return teacherRepository.findAll();
    }

    @PostMapping(EducationRoutes.SCHOOLCLASSES)
    public SchoolClassEntity teacher(@RequestBody SchoolClassEntity schoolClassEntity) {
        return schoolClassRepository.save(schoolClassEntity);
    }

    @GetMapping(EducationRoutes.SCHOOLCLASSES)
    public List<SchoolClassEntity> schoolClasses() {
        return schoolClassRepository.findAll();
    }
    /*
    @GetMapping(EducationRoutes.SCHEDULE)
    public List<ScheduleResponse> schedule(@PathVariable int id,@RequestParam(defaultValue = "") String date) {
        return "schedule";
    }
*/




}
