package org.example.educationsystem.service;

import lombok.AllArgsConstructor;
import org.example.educationsystem.dto.response.TeacherResponse;
import org.example.educationsystem.entity.TeacherEntity;
import org.example.educationsystem.repository.ScheduleRepository;
import org.example.educationsystem.repository.SchoolClassRepository;
import org.example.educationsystem.repository.TeacherRepository;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class EducationService {

    private final TeacherRepository teacherRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final ScheduleRepository scheduleRepository;


    public Boolean init(){

        TeacherEntity teacher = new TeacherEntity("Татьяна","Николаевна","Попова");

        teacherRepository.save(teacher);

        return true;
    }
}
