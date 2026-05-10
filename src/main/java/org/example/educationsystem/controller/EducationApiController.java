package org.example.educationsystem.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
import org.example.educationsystem.exception.ConflictException;
import org.example.educationsystem.exception.NotFoundException;
import org.example.educationsystem.routes.EducationRoutes;
import org.example.educationsystem.service.EducationService;
import org.example.educationsystem.service.ScheduleService;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;



@RestController
@RequiredArgsConstructor
public class EducationApiController {

    private final EducationService educationService;
    private final ScheduleService scheduleService;



    //TEST_DATA
    @GetMapping(EducationRoutes.INIT)
    public Boolean init() {
        return educationService.init();
    }


    //TEACHERS
    @Operation(summary = "Создать учителя")
    @PostMapping(EducationRoutes.TEACHERS)
    public TeacherResponse teacherCreate(@Valid @RequestBody CreateTeacherRequest request) throws ConflictException {
        return educationService.createTeacher(request);
    }

    @Operation(summary = "Вывести всех учителей")
    @GetMapping(EducationRoutes.TEACHERS)
    public List<TeacherResponse> teachers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        return educationService.teacherList(page, size);
    }

    @Operation(summary = "Изменить учителя")
    @PatchMapping(EducationRoutes.TEACHERSBYID)
    public TeacherResponse teacherUpdate(@PathVariable Long id,@Valid @RequestBody EditTeacherRequest request) throws NotFoundException {
        return educationService.updateTeacher(id, request);
    }


    //SCHOOL_CLASSES
    @Operation(summary = "Создать класс")
    @PostMapping(EducationRoutes.SCHOOLCLASSES)
    public SchoolClassResponse schoolClassCreate(@Valid @RequestBody CreateSchoolClassRequest request) throws ConflictException {
        return educationService.createSchoolClass(request);
    }

    @Operation(summary = "Вывести все классы")
    @GetMapping(EducationRoutes.SCHOOLCLASSES)
    public List<SchoolClassResponse> schoolClasses(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        return educationService.schoolClassList(page, size);
    }

    @Operation(summary = "Изменить класс")
    @PatchMapping(EducationRoutes.SCHOOLCLASSESBYID)
    public SchoolClassResponse schoolClassUpdate(@PathVariable Long id,@Valid @RequestBody EditSchoolClassRequest request) throws NotFoundException, ConflictException {
        return educationService.updateSchoolClass(id, request);
    }


    //SCHEDULE
    @Operation(summary = "Создать урок")
    @PostMapping(EducationRoutes.SCHEDULECREATE)
    public ScheduleResponse scheduleCreate(@Valid @RequestBody CreateScheduleRequest request) throws NotFoundException {
        return ScheduleResponse.of(scheduleService.create(request));
    }

    @Operation(summary = "Вывести расписание на день для класса")
    @GetMapping(EducationRoutes.SCHEDULECLASS)
    public DayScheduleResponse scheduleClass(@PathVariable Long id, @RequestParam(defaultValue = "") LocalDate date , @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) throws NotFoundException {
        return scheduleService.schoolClassSchedule(id, date, page, size);
    }

    @Operation(summary = "Вывести расписание на день для учителя")
    @GetMapping(EducationRoutes.SCHEDULETEACHER)
    public DayScheduleResponse scheduleTeacher(@PathVariable Long id, @RequestParam(defaultValue = "") LocalDate date, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) throws NotFoundException {
        return scheduleService.teacherSchedule(id, date, page, size);
    }

    @Operation(summary = "Изменить урок")
    @PatchMapping(EducationRoutes.SCHEDULEBYID)
    public ScheduleResponse scheduleUpdate(@PathVariable Long id,@Valid @RequestBody EditScheduleRequest request) throws NotFoundException {
        return scheduleService.updateSchedule(id, request);
    }




}
