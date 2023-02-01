package pl.kurs.programmingschool.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.kurs.programmingschool.exceptions.model.NoEntityException;
import pl.kurs.programmingschool.exceptions.model.WrongIdException;
import pl.kurs.programmingschool.model.Lesson;
import pl.kurs.programmingschool.model.commands.CreateLessonCommand;
import pl.kurs.programmingschool.model.commands.UpdateLessonCommand;
import pl.kurs.programmingschool.model.dto.LessonDto;
import pl.kurs.programmingschool.model.mapper.LessonMapper;
import pl.kurs.programmingschool.repository.LessonRepository;
import pl.kurs.programmingschool.repository.StudentRepository;
import pl.kurs.programmingschool.repository.TeacherRepository;
import pl.kurs.programmingschool.service.LessonService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Transactional
@Component
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final LessonRepository lessonRepository;

    @Override
    public List<Lesson> getAll() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson getById(Long id) {
        if (Objects.isNull(id)) {
            throw new WrongIdException("Id should be not null");
        }
        return lessonRepository.findById(id)
                .orElseThrow(() -> new NoEntityException("Entity with id " + id + " do not exists"));
    }

    @Override
    public Lesson add(Long studentId, Long teacherId, LocalDateTime localDateTime) {

        if (Objects.isNull(studentId)) {
            throw new NoEntityException("No entity to add!");
        }
        if (Objects.isNull(teacherId)) {
            throw new NoEntityException("No entity to add!");
        }
        if (Objects.isNull(localDateTime)) {
            throw new NoEntityException("No entity to add!");
        }

        Lesson lessonForAdd = Lesson.builder()
                .student(studentRepository.findById(studentId).get())
                .teacher(teacherRepository.findById(teacherId).get())
                .localDateTime(localDateTime)
                .build();

        return lessonRepository.save(lessonForAdd);
    }

    @Override
    public Lesson edit(LocalDateTime localDateTime, long id) {
        if (Objects.isNull(localDateTime)) {
            throw new NoEntityException("No entity to edit!");
        }

        Lesson lesson = lessonRepository.findById(id).get();
        lesson.setLocalDateTime(localDateTime);

        return lessonRepository.save(lesson);
    }

    @Override
    public void delete(Long id) {

    }
}
