package pl.kurs.programmingschool.service;

import pl.kurs.programmingschool.model.Teacher;
import pl.kurs.programmingschool.model.commands.CreateTeacherCommand;
import pl.kurs.programmingschool.model.commands.UpdateTeacherCommand;
import pl.kurs.programmingschool.model.dto.TeacherDto;

import java.util.List;

public interface TeacherService {

    List<Teacher> getAll();
    Teacher getById(Long id);
    Teacher add(Teacher teacher);
    Teacher edit(Teacher teacher, long id);
    void delete(Long id);

}
