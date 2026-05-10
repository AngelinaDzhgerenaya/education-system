INSERT INTO teachers(first_name, last_name)
VALUES ('Иван', 'Петров');

INSERT INTO teachers(first_name, last_name, middle_name)
VALUES ('Анна', 'Смирнова', 'Олеговна');

INSERT INTO teachers(first_name, last_name)
VALUES ('Мария', 'Кузнецова');

INSERT INTO school_classes(class_name)
VALUES ('9A');

INSERT INTO school_classes(class_name)
VALUES ('10B');

INSERT INTO school_classes(class_name)
VALUES ('11C');

INSERT INTO schedules(
    subject,
    date,
    start_time,
    end_time,
    room_number,
    teacher_id,
    class_id
)
VALUES (
           'Математика',
           '2026-05-10',
           '08:30',
           '09:15',
           '101',
           1,
           1
       );

INSERT INTO schedules(
    subject,
    date,
    start_time,
    end_time,
    room_number,
    teacher_id,
    class_id
)
VALUES (
           'Физика',
           '2026-05-10',
           '09:30',
           '10:15',
           '202',
           2,
           2
       );

INSERT INTO schedules(
    subject,
    date,
    start_time,
    end_time,
    room_number,
    teacher_id,
    class_id
)
VALUES (
           'Информатика',
           '2026-05-11',
           '10:30',
           '11:15',
           '305',
           3,
           3
       );