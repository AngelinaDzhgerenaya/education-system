package org.example.educationsystem.dto.response;


import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.example.educationsystem.entity.TeacherEntity;

@Getter
@SuperBuilder
public class TeacherResponse {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;


    public static TeacherResponse of(TeacherEntity item) {
        return  TeacherResponse.builder()
                .id(item.getId())
                .firstName(item.getFirstName())
                .middleName(item.getMiddleName())
                .lastName(item.getLastName())
                .build();
    }
}
