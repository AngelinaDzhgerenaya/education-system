package org.example.educationsystem.dto.request.edit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.educationsystem.entity.SchoolClassEntity;


@Data
public class EditSchoolClassRequest {

    @Size(min = 2, max = 10, message = "Неверная длина названия класса")
    @Pattern(
            regexp = "^[A-Za-zА-Яа-я0-9]+$",
            message = "Название класса должно содержать только буквы и цифры"
    )
    private String className;

}
