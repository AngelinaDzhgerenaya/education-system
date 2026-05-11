package org.example.educationsystem;



import org.example.educationsystem.dto.request.create.CreateScheduleRequest;
import org.example.educationsystem.dto.request.create.CreateSchoolClassRequest;
import org.example.educationsystem.dto.request.create.CreateTeacherRequest;
import org.example.educationsystem.entity.ScheduleEntity;
import org.example.educationsystem.entity.SchoolClassEntity;
import org.example.educationsystem.entity.TeacherEntity;
import org.springframework.http.MediaType;
import org.example.educationsystem.repository.ScheduleRepository;
import org.example.educationsystem.repository.SchoolClassRepository;
import org.example.educationsystem.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.time.LocalDate;
import java.time.LocalTime;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.AssertionsKt.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class Tests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;


    @BeforeEach
    void config() {
        scheduleRepository.deleteAll();
        teacherRepository.deleteAll();
        schoolClassRepository.deleteAll();
    }


    @Test
    void createTeacherTest() throws Exception {

        CreateTeacherRequest request = new CreateTeacherRequest();
        request.setFirstName("Анна");
        request.setMiddleName("Ивановна");
        request.setLastName("Петрова");

        mockMvc.perform(post("/api/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());

        TeacherEntity result =
                teacherRepository.findByFirstNameAndLastName("Анна","Петрова").orElse(null);

        assertNotNull(result);
        assertEquals("Анна", result.getFirstName());
    }




    @Test
    void createClassTest() throws Exception {

        CreateSchoolClassRequest request = new CreateSchoolClassRequest();
        request.setClassName("10А");

        mockMvc.perform(post("/api/classes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());

        SchoolClassEntity result =
                schoolClassRepository.findByClassName("10А").orElse(null);

        assertNotNull(result);
        assertEquals("10А", result.getClassName());
    }


    @Test
    void classConflictTest() throws Exception {

        SchoolClassEntity schoolClass =
                new SchoolClassEntity();

        schoolClass.setClassName("10А");

        schoolClassRepository.save(schoolClass);

        CreateSchoolClassRequest request =
                new CreateSchoolClassRequest();

        request.setClassName("10А");

        mockMvc.perform(post("/api/classes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    void createScheduleTest() throws Exception {

        TeacherEntity teacher = new TeacherEntity();

        teacher.setFirstName("Анна");
        teacher.setLastName("Петрова");

        teacher = teacherRepository.save(teacher);

        SchoolClassEntity schoolClass =
                new SchoolClassEntity();

        schoolClass.setClassName("10А");

        schoolClass = schoolClassRepository.save(schoolClass);

        CreateScheduleRequest request =
                new CreateScheduleRequest();

        request.setSubject("Математика");
        request.setDate(LocalDate.of(2026, 5, 9));
        request.setStartTime(LocalTime.of(9, 0));
        request.setEndTime(LocalTime.of(9, 40));
        request.setRoomNumber("101");
        request.setTeacherId(teacher.getId());
        request.setSchoolClassId(schoolClass.getId());

        mockMvc.perform(post("/api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("Математика")));
    }

    @Test
    void classesListTest() throws Exception {

        SchoolClassEntity class1 = new SchoolClassEntity();
        class1.setClassName("10А");
        schoolClassRepository.save(class1);

        SchoolClassEntity class2 = new SchoolClassEntity();
        class2.setClassName("11Б");
        schoolClassRepository.save(class2);

        mockMvc.perform(get("/api/classes")
                        .param("page", "0")
                        .param("size", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(org.hamcrest.Matchers.greaterThan(0)));
    }
    @Test
    void teachersListTest() throws Exception {

        TeacherEntity teacher1 = new TeacherEntity();
        teacher1.setFirstName("Анна");
        teacher1.setLastName("Петрова");
        teacherRepository.save(teacher1);

        TeacherEntity teacher2 = new TeacherEntity();
        teacher2.setFirstName("Иван");
        teacher2.setLastName("Сидоров");
        teacherRepository.save(teacher2);

        mockMvc.perform(get("/api/teachers")
                        .param("page", "0")
                        .param("size", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(org.hamcrest.Matchers.greaterThan(0)));
    }
    @Test
    void classScheduleTest() throws Exception {

        TeacherEntity teacher = new TeacherEntity();
        teacher.setFirstName("Анна");
        teacher.setLastName("Петрова");
        teacher = teacherRepository.save(teacher);

        SchoolClassEntity schoolClass = new SchoolClassEntity();
        schoolClass.setClassName("10А");
        schoolClass = schoolClassRepository.save(schoolClass);


        ScheduleEntity schedule = new ScheduleEntity();
        schedule.setSubject("Математика");
        schedule.setDate(LocalDate.of(2026, 5, 9));
        schedule.setStartTime(LocalTime.of(9, 0));
        schedule.setEndTime(LocalTime.of(9, 40));
        schedule.setRoomNumber("101");
        schedule.setTeacher(teacher);
        schedule.setSchoolClass(schoolClass);

        scheduleRepository.save(schedule);

        mockMvc.perform(get("/api/classes/" + schoolClass.getId() + "/schedule")
                        .param("date", "2026-05-09"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.schedule").isArray());
    }


}
