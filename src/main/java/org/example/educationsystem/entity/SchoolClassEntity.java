package org.example.educationsystem.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@SuperBuilder
@Table(name = "school_classes")
public class SchoolClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_name", nullable = false, length = 10)
    @Pattern(
            regexp = "^[A-Za-zА-Яа-я0-9]+$",
            message = "Название класса должно содержать только буквы и цифры"
    )
    private String className;

    @OneToMany(mappedBy = "schoolClass", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private List<ScheduleEntity> schedule;

    public SchoolClassEntity() {
    }

    public SchoolClassEntity(String className) {
        this.className = className;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ScheduleEntity> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ScheduleEntity> schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + id +
                ", className='" + className + '\'' +
                '}';
    }
}
