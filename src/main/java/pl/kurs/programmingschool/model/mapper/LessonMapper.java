package pl.kurs.programmingschool.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import pl.kurs.programmingschool.model.Lesson;
import pl.kurs.programmingschool.model.Student;
import pl.kurs.programmingschool.model.Teacher;
import pl.kurs.programmingschool.model.commands.CreateLessonCommand;
import pl.kurs.programmingschool.model.commands.CreateStudentCommand;
import pl.kurs.programmingschool.model.commands.UpdateLessonCommand;
import pl.kurs.programmingschool.model.dto.LessonDto;
import pl.kurs.programmingschool.model.dto.TeacherDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface LessonMapper {

    Lesson mapFromCreateCommand(CreateLessonCommand source);
    Lesson mapFromUpdateCommand(UpdateLessonCommand source);
    LessonDto mapFromLessonToLessonDto(Lesson source);

}
