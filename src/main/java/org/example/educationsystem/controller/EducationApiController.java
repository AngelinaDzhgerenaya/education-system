package org.example.educationsystem.controller;


import lombok.RequiredArgsConstructor;
import org.example.educationsystem.dto.request.create.CreateScheduleRequest;
import org.example.educationsystem.dto.request.create.CreateSchoolClassRequest;
import org.example.educationsystem.dto.request.create.CreateTeacherRequest;
import org.example.educationsystem.dto.request.edit.EditScheduleRequest;
import org.example.educationsystem.dto.request.edit.EditSchoolClassRequest;
import org.example.educationsystem.dto.request.edit.EditTeacherRequest;
import org.example.educationsystem.dto.response.DayScheduleResponse;
import org.example.educationsystem.dto.response.ScheduleResponse;
import org.example.educationsystem.dto.response.SchoolClassResponse;
import org.example.educationsystem.dto.response.TeacherResponse;
import org.example.educationsystem.repository.ScheduleRepository;
import org.example.educationsystem.repository.SchoolClassRepository;
import org.example.educationsystem.repository.TeacherRepository;
import org.example.educationsystem.routes.EducationRoutes;
import org.example.educationsystem.service.EducationService;
import org.example.educationsystem.service.ScheduleService;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class EducationApiController {
    private final TeacherRepository teacherRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final ScheduleRepository scheduleRepository;

    private final EducationService educationService;
    private final ScheduleService scheduleService;



    //TEST_DATA
    @GetMapping(EducationRoutes.INIT)
    public Boolean init() {
        return educationService.init();
    }


    //TEACHERS
    @PostMapping(EducationRoutes.TEACHERS)
    public TeacherResponse teacherCreate(@RequestBody CreateTeacherRequest request) {
        return TeacherResponse.of(teacherRepository.save(request.entity()));
    }

    @GetMapping(EducationRoutes.TEACHERS)
    public List<TeacherResponse> teachers() {

        return teacherRepository.findAll()
                .stream()
                .map(TeacherResponse::of)
                .collect(Collectors.toList());
    }

    @PatchMapping(EducationRoutes.TEACHERSBYID)
    public TeacherResponse teacherUpdate(@PathVariable Long id,@RequestBody EditTeacherRequest request) {
        return educationService.updateTeacher(id, request);
    }


    //SCHOOL_CLASSES
    @PostMapping(EducationRoutes.SCHOOLCLASSES)
    public SchoolClassResponse schoolClassCreate(@RequestBody CreateSchoolClassRequest request) {
        return SchoolClassResponse.of(schoolClassRepository.save(request.entity()));
    }

    @GetMapping(EducationRoutes.SCHOOLCLASSES)
    public List<SchoolClassResponse> schoolClasses() {

        return schoolClassRepository.findAll()
                .stream()
                .map(SchoolClassResponse::of)
                .collect(Collectors.toList());
    }

    @PatchMapping(EducationRoutes.SCHOOLCLASSESBYID)
    public SchoolClassResponse schoolClassUpdate(@PathVariable Long id,@RequestBody EditSchoolClassRequest request) {
        return educationService.updateSchoolClass(id, request);
    }


    //SCHEDULE
    @PostMapping(EducationRoutes.SCHEDULECRETE)
    public ScheduleResponse scheduleCreate(@RequestBody CreateScheduleRequest request)
    {
        return ScheduleResponse.of(scheduleService.create(request));
    }


    @GetMapping(EducationRoutes.SCHEDULECLASS)
    public DayScheduleResponse scheduleClass(@PathVariable Long id, @RequestParam(defaultValue = "") LocalDate date) {
        return scheduleService.schoolClassSchedule(id, date);
    }

    @GetMapping(EducationRoutes.SCHEDULETECHER)
    public DayScheduleResponse scheduleTeacher(@PathVariable Long id, @RequestParam(defaultValue = "") LocalDate date) {
        return scheduleService.teacherSchedule(id, date);
    }

    @PatchMapping(EducationRoutes.SCHEDULEBYID)
    public ScheduleResponse scheduleUpdate(@PathVariable Long id,@RequestBody EditScheduleRequest request) {
        return scheduleService.updateSchedule(id, request);
    }




}
