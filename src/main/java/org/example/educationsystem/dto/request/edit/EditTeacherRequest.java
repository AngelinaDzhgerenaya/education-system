package org.example.educationsystem.dto.request.edit;

import lombok.Data;
import org.example.educationsystem.entity.TeacherEntity;


@Data
public class EditTeacherRequest {

    private String firstName;
    private String middleName;
    private String lastName;

}
