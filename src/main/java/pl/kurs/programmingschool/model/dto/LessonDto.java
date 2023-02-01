package pl.kurs.programmingschool.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import pl.kurs.programmingschool.model.Student;
import pl.kurs.programmingschool.model.Teacher;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class LessonDto {

    private Long id;
    private Long studentId;
    private String studentFirstName;
    private String studentLastName;
    private Long teacherId;
    private String teacherFirstName;
    private String teacherLastName;
    private LocalDateTime localDateTime;

}
