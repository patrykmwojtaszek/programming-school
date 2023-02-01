package pl.kurs.programmingschool.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import pl.kurs.programmingschool.model.Teacher;
import pl.kurs.programmingschool.model.commands.CreateTeacherCommand;
import pl.kurs.programmingschool.model.commands.UpdateTeacherCommand;
import pl.kurs.programmingschool.model.dto.TeacherDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TeacherMapper {

    Teacher mapFromCreateCommand(CreateTeacherCommand source);

    Teacher mapFromUpdateCommand(UpdateTeacherCommand source);

    TeacherDto mapFromTeacherToTeacherDto(Teacher source);
}
