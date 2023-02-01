package pl.kurs.programmingschool.service.impl;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kurs.programmingschool.exceptions.model.NoEntityException;
import pl.kurs.programmingschool.model.Language;
import pl.kurs.programmingschool.model.Student;
import pl.kurs.programmingschool.model.Teacher;
import pl.kurs.programmingschool.model.mapper.StudentMapper;
import pl.kurs.programmingschool.model.mapper.StudentMapperImpl;
import pl.kurs.programmingschool.model.mapper.TeacherMapper;
import pl.kurs.programmingschool.model.mapper.TeacherMapperImpl;
import pl.kurs.programmingschool.repository.StudentRepository;
import pl.kurs.programmingschool.repository.TeacherRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private TeacherRepository teacherRepository;
    @Spy
    private StudentMapper studentMapper = new StudentMapperImpl();
    @InjectMocks
    private StudentServiceImpl studentServiceImpl;

    @Test
    public void add_shouldAdd() {
        //given
        Student student = Student.builder()
                .firstName("Karol")
                .lastName("Nowak")
                .language(Language.JAVA)
                .build();

                Teacher teacher = Teacher.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .languages(List.of(Language.JAVA))
                .build();

        //when
        when(teacherRepository.findById(any(long.class))).thenReturn(java.util.Optional.of(teacher));
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        Student studentResult = studentServiceImpl.add(student, 1L);
        //then
        assertEquals(student, studentResult);
    }

    @Test
    public void add_shouldThrowNoEntityExceptionWhenEntityIsNull() {
        Exception e = Assertions.assertThrows(NoEntityException.class, () -> studentServiceImpl.add(null, 0));
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(e).isExactlyInstanceOf(NoEntityException.class);
    }

    @Test
    public void edit_shouldEdit() {
//        //given
        Teacher teacher = Teacher.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .languages(List.of(Language.JAVA))
                .build();

        Student student = Student.builder()
                .firstName("Karol")
                .lastName("Nowak")
                .language(Language.JAVA)
                .teacher(teacher)
                .build();

        //when
        when(studentRepository.findById(any(long.class))).thenReturn(java.util.Optional.ofNullable(student));
        when(teacherRepository.findById(any(long.class))).thenReturn(java.util.Optional.of(teacher));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student studentResult = studentServiceImpl.edit(student, 1L);
        //then
        assertEquals(student, studentResult);
    }

    @Test
    public void getById_shouldGetById() {
        //given
        Teacher teacher = Teacher.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .languages(List.of(Language.JAVA))
                .build();

        Student student = Student.builder()
                .firstName("Karol")
                .lastName("Nowak")
                .language(Language.JAVA)
                .teacher(teacher)
                .build();
        //when
        when(studentRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(student));
        Student studentResult = studentServiceImpl.getById(1L);
        //then
        assertEquals(student, studentResult);
    }


}