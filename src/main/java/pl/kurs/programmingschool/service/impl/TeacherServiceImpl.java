package pl.kurs.programmingschool.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.el.stream.Optional;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.kurs.programmingschool.exceptions.model.NoEntityException;
import pl.kurs.programmingschool.exceptions.model.WrongIdException;
import pl.kurs.programmingschool.model.Language;
import pl.kurs.programmingschool.model.Student;
import pl.kurs.programmingschool.model.Teacher;
import pl.kurs.programmingschool.model.commands.CreateTeacherCommand;
import pl.kurs.programmingschool.model.commands.UpdateTeacherCommand;
import pl.kurs.programmingschool.model.dto.TeacherDto;
import pl.kurs.programmingschool.model.mapper.TeacherMapper;
import pl.kurs.programmingschool.repository.StudentRepository;
import pl.kurs.programmingschool.repository.TeacherRepository;
import pl.kurs.programmingschool.service.TeacherService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional
@Component
@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;


    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getById(Long id) {
        if (Objects.isNull(id)) {
            throw new WrongIdException("Id should be not null");
        }
        return teacherRepository.findById(id)
                .orElseThrow(() -> new NoEntityException("Entity with id " + id + " do not exists"));
    }

    @Override
    public Teacher add(Teacher teacher) {
        if (Objects.isNull(teacher)) {
            throw new NoEntityException("No entity to add!");
        }

        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher edit(Teacher teacher, long id) {
        if (Objects.isNull(teacher)) {
            throw new IllegalArgumentException("No entity to edit!");
        }

        Teacher teacherForEdit = teacherRepository.findById(id)
                .orElseThrow(() -> new NoEntityException("Entity with id " + id + " do not exists"));
        teacherForEdit.setFirstName(teacher.getFirstName());
        teacherForEdit.setLastName(teacher.getLastName());
        teacherForEdit.setLanguages(teacher.getLanguages());

        return teacherRepository.save(teacherForEdit);
    }

    @Override
    public void delete(Long id) {
        try{
            teacherRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new NoEntityException("Entity with id " + id + " do not exists");
        }
    }
}
