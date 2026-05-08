package org.example.educationsystem.service;

import lombok.AllArgsConstructor;
import org.example.educationsystem.dto.request.edit.EditSchoolClassRequest;
import org.example.educationsystem.dto.request.edit.EditTeacherRequest;
import org.example.educationsystem.dto.response.SchoolClassResponse;
import org.example.educationsystem.dto.response.TeacherResponse;
import org.example.educationsystem.entity.ScheduleEntity;
import org.example.educationsystem.entity.SchoolClassEntity;
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

    public TeacherResponse updateTeacher(Long id, EditTeacherRequest request){
        TeacherEntity teacher = teacherRepository.findById(id)
                .orElseThrow();

        if (request.getFirstName() != null) {
            teacher.setFirstName(request.getFirstName());
        }

        if (request.getMiddleName() != null) {
            teacher.setMiddleName(request.getMiddleName());
        }

        if (request.getLastName() != null) {
            teacher.setLastName(request.getLastName());
        }

        return TeacherResponse.of(teacherRepository.save(teacher));

    }

    public SchoolClassResponse updateSchoolClass(Long id, EditSchoolClassRequest request){
        SchoolClassEntity schoolClass = schoolClassRepository.findById(id).orElseThrow();
        if(request.getClassName() != null) {
            schoolClass.setClassName(request.getClassName());
        }

        return SchoolClassResponse.of(schoolClassRepository.save(schoolClass));
    }



}
