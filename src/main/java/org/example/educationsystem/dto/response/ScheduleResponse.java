package org.example.educationsystem.dto.response;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.example.educationsystem.entity.ScheduleEntity;


import java.time.LocalTime;

@Getter
@SuperBuilder
public class ScheduleResponse {

    private Long id;
    private String subject;
    private String teacherName;
    private String schoolClassName;
    private LocalTime startTime;
    private LocalTime endTime;
    private String roomNumber;


    public static ScheduleResponse of(ScheduleEntity item) {
        return  ScheduleResponse.builder()
                .id(item.getId())
                .subject(item.getSubject())
                .teacherName(item.getTeacher().getFirstName() + " " + item.getTeacher().getMiddleName() + " " + item.getTeacher().getLastName())
                .schoolClassName(item.getSchoolClass().getClassName())
                .startTime(item.getStartTime())
                .endTime(item.getEndTime())
                .roomNumber(item.getRoomNumber())
                .build();
    }

    public static ScheduleResponse ofEmpty(ScheduleEntity item) {
        return  ScheduleResponse.builder()
                .id(item.getId())
                .subject(item.getSubject())
                .startTime(item.getStartTime())
                .endTime(item.getEndTime())
                .roomNumber(item.getRoomNumber())
                .build();
    }
}
