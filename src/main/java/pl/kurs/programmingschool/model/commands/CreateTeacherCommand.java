package pl.kurs.programmingschool.model.commands;

import lombok.*;
import pl.kurs.programmingschool.model.Language;
import pl.kurs.programmingschool.validators.Name;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTeacherCommand {

    @Name
    @NotNull
//    @Pattern(regexp = "[A-Z][a-z]+")
    private String firstName;

    @Name
    @NotNull
    private String lastName;

    @NotEmpty
    private List<Language> languages;


}
