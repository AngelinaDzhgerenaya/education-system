package org.example.educationsystem.dto.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.example.educationsystem.entity.ScheduleEntity;

import java.util.List;


@Getter
@SuperBuilder
public class DayScheduleResponse {

    private String dayOfWeek;
    private List<ScheduleResponse> schedule;



    public static DayScheduleResponse of(String dayOfWeek, List<ScheduleResponse> schedule) {
        return  DayScheduleResponse.builder()
                .dayOfWeek(dayOfWeek)
                .schedule(schedule)
                .build();
    }

}
