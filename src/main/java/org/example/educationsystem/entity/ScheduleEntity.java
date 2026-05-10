package org.example.educationsystem.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.experimental.SuperBuilder;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@SuperBuilder
@Table(name = "schedules")
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject", nullable = false, length = 50)
    @Pattern(
            regexp = "^[А-Яа-я]+$",
            message = "Урок должно состоять только из русского алфавита"
    )
    private String subject;

    @Column(name = "date", nullable = false, length = 50)
    private LocalDate date;


    @Column(name = "start_time", nullable = false, length = 10)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false, length = 10)
    private LocalTime endTime;

    @Column(name = "room_number", nullable = false, length = 10)
    @Pattern(
            regexp = "^[A-Za-zА-Яа-я0-9]+$",
            message = "Номер комнаты должен содержать только буквы и цифры"
    )
    private String roomNumber;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClassEntity schoolClass;


    public ScheduleEntity() {
    }


    public ScheduleEntity(Long id, String subject, LocalDate date, LocalTime startTime, LocalTime endTime, String roomNumber, TeacherEntity teacher, SchoolClassEntity schoolClass) {
        this.id = id;
        this.subject = subject;
        this.date = date;
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


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
        return "ScheduleEntity{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", roomNumber='" + roomNumber + '\'' +
                ", teacher=" + teacher +
                ", schoolClass=" + schoolClass +
                '}';
    }
}
