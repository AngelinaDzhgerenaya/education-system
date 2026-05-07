package org.example.educationsystem.dto.response;

import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;
import org.example.educationsystem.entity.ScheduleEntity;
import org.example.educationsystem.entity.SchoolClassEntity;
import org.example.educationsystem.entity.TeacherEntity;
import org.example.educationsystem.enums.DayOfWeek;

import java.time.LocalTime;


@SuperBuilder
public class ScheduleResponse {

    private Long id;
    private String subject;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private String roomNumber;
    private TeacherEntity teacher;
    private SchoolClassEntity schoolClass;

    public static ScheduleResponse of(ScheduleEntity item) {
        return  ScheduleResponse.builder()
                .id(item.getId())
                .subject(item.getSubject())
                .dayOfWeek(item.getDayOfWeek())
                .startTime(item.getStartTime())
                .endTime(item.getEndTime())
                .roomNumber(item.getRoomNumber())
                .teacher(item.getTeacher())
                .schoolClass(item.getSchoolClass())
                .build();
    }
}
