package org.example.educationsystem.service;

import lombok.AllArgsConstructor;
import org.example.educationsystem.dto.request.create.CreateSchoolClassRequest;
import org.example.educationsystem.dto.request.create.CreateTeacherRequest;
import org.example.educationsystem.dto.request.edit.EditSchoolClassRequest;
import org.example.educationsystem.dto.request.edit.EditTeacherRequest;
import org.example.educationsystem.dto.response.SchoolClassResponse;
import org.example.educationsystem.dto.response.TeacherResponse;
import org.example.educationsystem.entity.SchoolClassEntity;
import org.example.educationsystem.entity.TeacherEntity;
import org.example.educationsystem.exception.ConflictException;
import org.example.educationsystem.exception.NotFoundException;
import org.example.educationsystem.repository.ScheduleRepository;
import org.example.educationsystem.repository.SchoolClassRepository;
import org.example.educationsystem.repository.TeacherRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

    public SchoolClassResponse createSchoolClass(CreateSchoolClassRequest request) throws ConflictException {

        Optional<SchoolClassEntity> schoolClass = schoolClassRepository.findByClassName(request.getClassName());
        if (schoolClass.isPresent()) {
            throw new ConflictException("Такой класс уже существует");
        }

        return SchoolClassResponse.of(schoolClassRepository.save(request.entity()));
    }

    public TeacherResponse createTeacher(CreateTeacherRequest request) throws ConflictException {

        /*Optional<TeacherEntity> teacher;
        if (request.getMiddleName() != null) {
            teacher = teacherRepository.findByFirstNameAndLastNameAndMiddleName(request.getFirstName(), request.getLastName(), request.getMiddleName());
        }
        else {
            teacher = teacherRepository.findByFirstNameAndLastName(request.getFirstName(), request.getLastName());
        }
        if (teacher.isPresent()) {
            throw new ConflictException("Такой учитель уже существует");
        }*/

        return TeacherResponse.of(teacherRepository.save(request.entity()));
    }

    public List<TeacherResponse> teacherList(int page, int size){
        Pageable pageable = PageRequest.of(page, size);


        return teacherRepository.findAll(pageable)
                .stream()
                .map(TeacherResponse::of)
                .collect(Collectors.toList());
    }

    public List<SchoolClassResponse> schoolClassList(int page, int size){
        Pageable pageable = PageRequest.of(page, size);

        return schoolClassRepository.findAll()
                .stream()
                .map(SchoolClassResponse::of)
                .collect(Collectors.toList());
    }


    public TeacherResponse updateTeacher(Long id, EditTeacherRequest request) throws NotFoundException {

        TeacherEntity teacher = teacherRepository.findById(id
        ).orElseThrow(() ->
                new NotFoundException("Учитель не найден")
        );

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

    public SchoolClassResponse updateSchoolClass(Long id, EditSchoolClassRequest request) throws NotFoundException, ConflictException {
        SchoolClassEntity schoolClass = schoolClassRepository.findById(id
        ).orElseThrow(() ->
                new NotFoundException("Класс не найден")
        );


        if(request.getClassName() != null) {
            schoolClass.setClassName(request.getClassName());
            Optional<SchoolClassEntity> schoolClassCheck = schoolClassRepository.findByClassName(request.getClassName());
            if (schoolClassCheck.isPresent()) {
                throw new ConflictException("Такой класс уже существует");
            }
        }

        return SchoolClassResponse.of(schoolClassRepository.save(schoolClass));
    }



}
