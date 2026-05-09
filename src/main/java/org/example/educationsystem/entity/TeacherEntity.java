package org.example.educationsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Entity
@SuperBuilder
@Table(name = "teachers")
public class TeacherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    @Pattern(
            regexp = "^[А-Яа-я]+$",
            message = "Имя должно состоять только из русского алфавита"
    )
    private String firstName;

    @Column(name = "middle_name", length = 50)
    @Pattern(
            regexp = "^[А-Яа-я]+$",
            message = "Отчество должно состоять только из русского алфавита"
    )
    private String middleName;

    @Column(name = "last_name", nullable = false, length = 50)
    @Pattern(
            regexp = "^[А-Яа-я]+$",
            message = "Фамилия должна состоять только из русского алфавита"
    )
    private String lastName;

    @OneToMany(mappedBy = "teacher" ,fetch = FetchType.LAZY)
    private List<ScheduleEntity> schedule;

    public TeacherEntity() {
    }

    public TeacherEntity(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;

    }

    public TeacherEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String secondName) {
        this.lastName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public List<ScheduleEntity> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ScheduleEntity> schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }
}
