package pl.kurs.programmingschool.service;

import pl.kurs.programmingschool.model.Lesson;
import pl.kurs.programmingschool.model.Student;
import pl.kurs.programmingschool.model.commands.CreateLessonCommand;
import pl.kurs.programmingschool.model.commands.UpdateLessonCommand;
import pl.kurs.programmingschool.model.dto.LessonDto;

import java.time.LocalDateTime;
import java.util.List;

public interface LessonService {

    List<Lesson> getAll();
    Lesson getById(Long id);
    Lesson add(Long studentId, Long teacherId, LocalDateTime localDateTime);
    Lesson edit(LocalDateTime localDateTime, long id);
    void delete(Long id);

}
