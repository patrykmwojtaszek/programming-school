package pl.kurs.programmingschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.programmingschool.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
