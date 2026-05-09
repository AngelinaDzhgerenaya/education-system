package org.example.educationsystem.dto.request.create;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.educationsystem.entity.ScheduleEntity;


import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateScheduleRequest {

    @NotBlank(message = "Название урока обязательно")
    @Size(min = 1, max = 50, message = "Неверная длина названия урока")
    @Pattern(
            regexp = "^[А-Яа-я]+$",
            message = "Урок должно состоять только из русского алфавита"
    )
    private String subject;
    @NotNull(message = "Дата урока обязательна")
    private LocalDate date;
    @NotNull(message = "Время начала урока обязательно")
    private LocalTime startTime;
    @NotNull(message = "Время окончания урока обязательно")
    private LocalTime endTime;
    @NotBlank(message = "Номер комнаты не может быть пустым")
    @Size(min = 1, max = 10, message = "Неверная длина номера комнаты")
    @Pattern(
            regexp = "^[A-Za-zА-Яа-я0-9]+$",
            message = "Номер комнаты должен содержать только буквы и цифры"
    )
    private String roomNumber;
    @NotNull(message = "У урока должен быть учитель")
    private Long teacherId;
    @NotNull(message = "У урока должен быть класс")
    private Long schoolClassId;


    public ScheduleEntity entity(){
        return ScheduleEntity.builder()
                .subject(subject)
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .roomNumber(roomNumber)
                .build();
    }

}
