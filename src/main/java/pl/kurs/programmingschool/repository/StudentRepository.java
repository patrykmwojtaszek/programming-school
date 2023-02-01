package pl.kurs.programmingschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.programmingschool.model.Student;
import pl.kurs.programmingschool.model.Teacher;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
