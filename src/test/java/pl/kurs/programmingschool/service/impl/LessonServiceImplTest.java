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
import pl.kurs.programmingschool.model.Lesson;
import pl.kurs.programmingschool.model.Student;
import pl.kurs.programmingschool.model.Teacher;
import pl.kurs.programmingschool.model.mapper.LessonMapper;
import pl.kurs.programmingschool.model.mapper.LessonMapperImpl;
import pl.kurs.programmingschool.model.mapper.StudentMapper;
import pl.kurs.programmingschool.model.mapper.StudentMapperImpl;
import pl.kurs.programmingschool.repository.LessonRepository;
import pl.kurs.programmingschool.repository.StudentRepository;
import pl.kurs.programmingschool.repository.TeacherRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LessonServiceImplTest {

    @Mock
    private LessonRepository lessonRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private TeacherRepository teacherRepository;
    @Spy
    private LessonMapper lessonMapper = new LessonMapperImpl();
    @InjectMocks
    private LessonServiceImpl lessonServiceImpl;

    @Test
    public void add_shouldAdd() {
        //given

        Teacher teacher = Teacher.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .languages(List.of(Language.C))
                .build();

        Student student = Student.builder()
                .id(1L)
                .firstName("Karol")
                .lastName("Nowak")
                .language(Language.JAVA)
                .teacher(teacher)
                .build();

        Lesson lesson = Lesson.builder()
                .student(student)
                .teacher(teacher)
                .localDateTime(LocalDateTime.of(2022,01,20,16,00))
                .build();

        //when
        when(studentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(student));
        when(teacherRepository.findById(anyLong())).thenReturn(Optional.ofNullable(teacher));
        when(lessonRepository.save(any(Lesson.class))).thenReturn(lesson);
        Lesson lessonResult = lessonServiceImpl.add(1L, 1L, LocalDateTime.of(2022,01,20,16,00));
        //then
        assertEquals(lesson, lessonResult);
    }

    @Test
    public void add_shouldThrowNoEntityExceptionWhenEntityIsNull() {
        Exception e = Assertions.assertThrows(NoEntityException.class, () -> lessonServiceImpl.add(null, null, null));
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(e).isExactlyInstanceOf(NoEntityException.class);
    }

    @Test
    public void edit_shouldEdit() {
        //given
        Teacher teacher = Teacher.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .languages(List.of(Language.C))
                .build();

        Student student = Student.builder()
                .id(1L)
                .firstName("Karol")
                .lastName("Nowak")
                .language(Language.JAVA)
                .teacher(teacher)
                .build();

        Lesson lesson = Lesson.builder()
                .student(student)
                .teacher(teacher)
                .localDateTime(LocalDateTime.of(2022,01,20,16,00))
                .build();
        //when
        when(lessonRepository.findById(any(long.class))).thenReturn(java.util.Optional.ofNullable(lesson));
        when(lessonRepository.save(any(Lesson.class))).thenReturn(lesson);

        Lesson lessonResult = lessonServiceImpl.edit(LocalDateTime.of(2022,01,20,16,00), 1L);
        //then
        assertEquals(lesson, lessonResult);
    }

    @Test
    public void getById_shouldGetById() {
        //given
        Teacher teacher = Teacher.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .languages(List.of(Language.C))
                .build();

        Student student = Student.builder()
                .id(1L)
                .firstName("Karol")
                .lastName("Nowak")
                .language(Language.JAVA)
                .teacher(teacher)
                .build();

        Lesson lesson = Lesson.builder()
                .student(student)
                .teacher(teacher)
                .localDateTime(LocalDateTime.of(2022,01,20,16,00))
                .build();
        //when
        when(lessonRepository.findById(any(Long.class))).thenReturn(Optional.of(lesson));
        Lesson lessonResult = lessonServiceImpl.getById(1L);
        //then
        assertEquals(lesson, lessonResult);
    }

}