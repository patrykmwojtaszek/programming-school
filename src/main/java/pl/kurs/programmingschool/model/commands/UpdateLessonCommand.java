package pl.kurs.programmingschool.model.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kurs.programmingschool.model.Student;
import pl.kurs.programmingschool.model.Teacher;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateLessonCommand {

//    @NotNull
//    private Long studentId;
//
//    @NotNull
//    private Long teacherId;
//
    @NotNull
    private LocalDateTime localDateTime;

}
