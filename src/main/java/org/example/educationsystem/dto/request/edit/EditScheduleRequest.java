package org.example.educationsystem.dto.request.edit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.educationsystem.entity.ScheduleEntity;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EditScheduleRequest {

    @Size(min = 1, max = 50, message = "Неверная длина названия урока")
    @Pattern(
            regexp = "^[А-Яа-я]+$",
            message = "Урок должно состоять только из русского алфавита"
    )
    private String subject;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    @Size(min = 1, max = 10, message = "Неверная длина номера комнаты")
    @Pattern(
            regexp = "^[A-Za-zА-Яа-я0-9]+$",
            message = "Номер комнаты должен содержать только буквы и цифры"
    )
    private String roomNumber;
    private Long teacherId;
    private Long schoolClassId;

}
