package pl.kurs.programmingschool.model.commands;

import lombok.*;
import pl.kurs.programmingschool.model.Language;
import pl.kurs.programmingschool.validators.Name;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTeacherCommand {

    @Name
    @NotNull
    private String firstName;

    @Name
    @NotNull
    private String lastName;

    @NotEmpty
    private List<Language> languages;

//    @NotNull
//    private List<Long> studentIds;

}
