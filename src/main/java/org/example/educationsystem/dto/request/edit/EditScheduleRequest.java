package org.example.educationsystem.dto.request.edit;

import lombok.Data;
import org.example.educationsystem.entity.ScheduleEntity;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EditScheduleRequest {

    private String subject;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String roomNumber;
    private Long teacherId;
    private Long schoolClassId;

}
