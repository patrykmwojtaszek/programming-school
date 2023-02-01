package pl.kurs.programmingschool.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import pl.kurs.programmingschool.model.Language;

@Data
@RequiredArgsConstructor
public class StudentDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Language language;
    private Long teacherId;

}
