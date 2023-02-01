package pl.kurs.programmingschool.model.commands;

import lombok.*;
import pl.kurs.programmingschool.model.Language;
import pl.kurs.programmingschool.validators.ProgrammingLanguage;
import pl.kurs.programmingschool.validators.Name;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateStudentCommand {

    @Name
    @NotEmpty
    private String firstName;

    @Name
    @NotEmpty
    private String lastName;

//    @NotEmpty
//    @ProgrammingLanguage
    @NotNull
    private Language language;

    @NotNull
    @Positive
    private Long teacherId;

}
