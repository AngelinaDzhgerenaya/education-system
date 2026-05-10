# Education_system

REST API для управления школьным расписанием.

## Technologies

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Validation
- PostgreSQL
- H2 Database
- Maven
- Lombok
- Swagger / OpenAPI

Swagger UI

После запуска документация доступна по URL:
http://localhost:8080/swagger-ui/index.html


API Endpoints

Teachers

Создать учителя
POST /api/teachers
{
  "firstName": "Анна",
  "lastName": "Петрова",
  "middleName": "Ивановна"
}

Получить список учителей
GET /api/teachers?page=0&size=10

Изменить учителя
PATCH /api/teachers/{id}
{
  "firstName": "Мария"
}


School Classes

Создать класс
POST /api/classes
{
  "name": "9A"
}

Получить список классов
GET /api/classes?page=0&size=10

Изменить класс
PATCH /api/classes/{id}
{
  "name": "10B"
}


Schedule

Создать урок
POST /api/schedule
{
  "subject": "Математика",
  "date": "2026-05-10",
  "startTime": "08:30",
  "endTime": "09:15",
  "roomNumber": "101",
  "teacherId": 1,
  "schoolClassId": 1
}

Получить расписание класса на день
GET /api/classes/{id}/schedule?date=2026-05-10&page=0&size=10

Получить расписание учителя на день
GET /api/teachers/{id}/schedule?date=2026-05-10&page=0&size=10

Изменить урок
PATCH /api/schedule/{id}
{
  "subject": "Физика"
  "startTime": "10:00",
  "endTime": "10:45",
  "roomNumber": "202"
}













