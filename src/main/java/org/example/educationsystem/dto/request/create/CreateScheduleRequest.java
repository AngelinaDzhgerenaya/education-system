package org.example.educationsystem.dto.request.create;


import lombok.Data;
import org.example.educationsystem.entity.ScheduleEntity;


import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateScheduleRequest {


    private String subject;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String roomNumber;
    private Long teacherId;
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
