package pl.kurs.programmingschool.service;

import pl.kurs.programmingschool.model.Student;
import pl.kurs.programmingschool.model.Teacher;
import pl.kurs.programmingschool.model.commands.CreateStudentCommand;
import pl.kurs.programmingschool.model.commands.UpdateStudentCommand;
import pl.kurs.programmingschool.model.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List<Student> getAll();
    Student getById(Long id);
    Student add(Student student, long teacherId);
    Student edit(Student student, long id);
    void delete(Long id);

}
