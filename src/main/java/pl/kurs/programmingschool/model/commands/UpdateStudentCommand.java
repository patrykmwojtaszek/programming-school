package pl.kurs.programmingschool.model.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kurs.programmingschool.model.Language;
import pl.kurs.programmingschool.validators.Name;
import pl.kurs.programmingschool.validators.ProgrammingLanguage;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateStudentCommand {

    @Name
    @NotEmpty
    private String firstName;

    @Name
    @NotEmpty
    private String lastName;

    @NotEmpty
    @ProgrammingLanguage
    private Language language;

    @NotNull
    private Long teacherId;

}
