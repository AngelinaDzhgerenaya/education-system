package org.example.educationsystem.dto.request.edit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.educationsystem.entity.TeacherEntity;


@Data
public class EditTeacherRequest {

    @Size(min = 2, max = 50, message = "Неверная длина имени")
    @Pattern(
            regexp = "^[А-Яа-я]+$",
            message = "Имя должно состоять только из русского алфавита"
    )
    private String firstName;
    @Size( max = 50, message = "Неверная длина отчества")
    @Pattern(
            regexp = "^[А-Яа-я]+$",
            message = "Отчество должно состоять только из русского алфавита"
    )
    private String middleName;
    @Size(min = 2, max = 50, message = "Неверная длина фамилии")
    @Pattern(
            regexp = "^[А-Яа-я]+$",
            message = "Фамилия должна состоять только из русского алфавита"
    )
    private String lastName;

}
