package pl.kurs.programmingschool.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import pl.kurs.programmingschool.model.Student;
import pl.kurs.programmingschool.model.Teacher;
import pl.kurs.programmingschool.model.commands.CreateStudentCommand;
import pl.kurs.programmingschool.model.commands.CreateTeacherCommand;
import pl.kurs.programmingschool.model.commands.UpdateStudentCommand;
import pl.kurs.programmingschool.model.dto.StudentDto;
import pl.kurs.programmingschool.model.dto.TeacherDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface StudentMapper {

    Student mapFromCreateCommand(CreateStudentCommand source);
    Student mapFromUpdateCommand(UpdateStudentCommand source);
    StudentDto mapFromStudentToStudentDto(Student source);

}
