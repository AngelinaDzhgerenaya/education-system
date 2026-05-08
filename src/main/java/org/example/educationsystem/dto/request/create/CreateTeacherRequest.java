package org.example.educationsystem.dto.request.create;

import lombok.Data;
import org.example.educationsystem.entity.TeacherEntity;


@Data
public class CreateTeacherRequest {

    private String firstName;
    private String middleName;
    private String lastName;

    public TeacherEntity entity(){
        return TeacherEntity.builder()
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .build();
    }
}
