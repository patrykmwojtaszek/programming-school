package pl.kurs.programmingschool.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import pl.kurs.programmingschool.model.Language;

import java.util.List;

@Data
@RequiredArgsConstructor
public class TeacherDto {

    private Long id;
    private String firstName;
    private String lastName;
    private List<Language> languages;

}
