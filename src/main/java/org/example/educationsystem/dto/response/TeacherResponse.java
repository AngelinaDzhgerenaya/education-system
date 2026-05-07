package org.example.educationsystem.dto.response;


import lombok.experimental.SuperBuilder;
import org.example.educationsystem.entity.TeacherEntity;


@SuperBuilder
public class TeacherResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;

    public static TeacherResponse of(TeacherEntity item) {
        return  TeacherResponse.builder()
                .id(item.getId())
                .firstName(item.getFirstName())
                .lastName(item.getLastName())
                .middleName(item.getMiddleName())
                .build();
    }
}
