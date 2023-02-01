package pl.kurs.programmingschool.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.kurs.programmingschool.exceptions.model.NoEntityException;
import pl.kurs.programmingschool.exceptions.model.WrongIdException;
import pl.kurs.programmingschool.model.Language;
import pl.kurs.programmingschool.model.Student;
import pl.kurs.programmingschool.model.Teacher;
import pl.kurs.programmingschool.model.commands.CreateStudentCommand;
import pl.kurs.programmingschool.model.commands.UpdateStudentCommand;
import pl.kurs.programmingschool.model.dto.StudentDto;
import pl.kurs.programmingschool.model.mapper.StudentMapper;
import pl.kurs.programmingschool.repository.StudentRepository;
import pl.kurs.programmingschool.repository.TeacherRepository;
import pl.kurs.programmingschool.service.StudentService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

@Transactional
@Component
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student getById(Long id) {
        if (Objects.isNull(id)) {
            throw new WrongIdException("Id should be not null");
        }
        return studentRepository.findById(id)
                .orElseThrow(() -> new NoEntityException("Entity with id " + id + " do not exists"));
    }

    @Override
    public Student add(Student student, long teacherId) {
        if (Objects.isNull(student)) {
            throw new NoEntityException("No entity to add!");
        }

//        Long teacherId = student.getTeacher().getId();
            Teacher teacher = teacherRepository.findById(teacherId)
                    .orElseThrow(() -> new EntityNotFoundException(MessageFormat
                            .format("Teacher with id={} was not found", teacherId)));
            Language language = Language.valueOf(student.getLanguage().toString());
            if (!teacher.getLanguages().contains(language)) {
                throw new IllegalArgumentException(MessageFormat
                        .format("Teacher with id {} is not teaching language {}", teacherId, language));
            }

        return studentRepository.save(student);
    }

    @Override
    public Student edit(Student student, long id) {
        if (Objects.isNull(student)) {
            throw new NoEntityException("No entity to add!");
        }

        Long teacherId = student.getTeacher().getId();
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat
                        .format("Teacher with id={} was not found", teacherId)));
        Language language = Language.valueOf(student.getLanguage().toString());
        if (!teacher.getLanguages().contains(language)) {
            throw new IllegalArgumentException(MessageFormat
                    .format("Teacher with id {} is not teaching language {}", teacherId, language));
        }

        Student studentForEdit = studentRepository.findById(id).get();
        studentForEdit.setLanguage(student.getLanguage());
        studentForEdit.setTeacher(student.getTeacher());
        studentForEdit.setLessons(student.getLessons());

        return studentRepository.save(studentForEdit);
    }

    @Override
    public void delete(Long id) {
        try{
            studentRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new NoEntityException("Entity with id " + id + " do not exists");
        }
    }
}
