package pl.kurs.programmingschool.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
//@Table(name = "teachers")
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Teacher extends Person {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id_teacher")
    private Long id;

    @ElementCollection
    private List<Language> languages = new ArrayList<>();

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Lesson> lessons = new ArrayList<>();

}
