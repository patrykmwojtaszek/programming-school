package pl.kurs.programmingschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.programmingschool.model.Lesson;
import pl.kurs.programmingschool.model.Student;

public interface LessonRepository extends JpaRepository<Lesson, Long>{
}
