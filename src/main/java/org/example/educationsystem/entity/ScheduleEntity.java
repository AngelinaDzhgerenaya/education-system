package org.example.educationsystem.entity;


import jakarta.persistence.*;
import org.example.educationsystem.enums.DayOfWeek;

import java.time.LocalTime;

@Entity
@Table(name = "schedules")
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject", nullable = false, length = 50)
    private String subject;


    @Column(name = "day_of_week", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(name = "start_time", nullable = false, length = 50)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false, length = 50)
    private LocalTime endTime;

    @Column(name = "room_number", nullable = false, length = 50)
    private String roomNumber;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClassEntity schoolClass;


    public ScheduleEntity() {
    }

    public ScheduleEntity(String subject, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, String roomNumber) {
        this.subject = subject;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomNumber = roomNumber;
    }

    public ScheduleEntity(Long id, String subject, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, String roomNumber, TeacherEntity teacher, SchoolClassEntity schoolClass) {
        this.id = id;
        this.subject = subject;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomNumber = roomNumber;
        this.teacher = teacher;
        this.schoolClass = schoolClass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public SchoolClassEntity getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClassEntity schoolClass) {
        this.schoolClass = schoolClass;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", dayOfWeek=" + dayOfWeek +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", roomNumber='" + roomNumber + '\'' +
                ", teacher=" + teacher +
                ", schoolClass=" + schoolClass +
                '}';
    }
}
