package pl.kurs.programmingschool.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "lesson")
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id_lesson")
    private Long id;

    @ManyToOne
//    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
//    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    private LocalDateTime localDateTime;

}
