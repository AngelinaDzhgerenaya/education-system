package org.example.educationsystem.dto.response;

import lombok.experimental.SuperBuilder;
import org.example.educationsystem.entity.SchoolClassEntity;



@SuperBuilder
public class SchoolClassResponse {

    private Long id;
    private String className;


    public static SchoolClassResponse of(SchoolClassEntity item) {
        return  SchoolClassResponse.builder()
                .id(item.getId())
                .className(item.getClassName())
                .build();
    }
}
