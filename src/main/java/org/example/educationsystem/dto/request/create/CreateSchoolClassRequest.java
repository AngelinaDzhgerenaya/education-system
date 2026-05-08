package org.example.educationsystem.dto.request.create;


import lombok.Data;
import org.example.educationsystem.entity.SchoolClassEntity;


@Data
public class CreateSchoolClassRequest {

    private String className;

    public SchoolClassEntity entity(){
        return SchoolClassEntity.builder()
                .className(className)
                .build();
    }
}
