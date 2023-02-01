package pl.kurs.programmingschool.service.impl;

import java.util.Optional;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kurs.programmingschool.exceptions.model.NoEntityException;
import pl.kurs.programmingschool.exceptions.model.WrongIdException;
import pl.kurs.programmingschool.model.Language;
import pl.kurs.programmingschool.model.Teacher;
import pl.kurs.programmingschool.model.commands.CreateTeacherCommand;
import pl.kurs.programmingschool.model.commands.UpdateTeacherCommand;
import pl.kurs.programmingschool.model.dto.TeacherDto;
import pl.kurs.programmingschool.model.mapper.TeacherMapper;
import pl.kurs.programmingschool.model.mapper.TeacherMapperImpl;
import pl.kurs.programmingschool.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherServiceImplTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Spy
    private TeacherMapper teacherMapper = new TeacherMapperImpl();

    @InjectMocks
    private TeacherServiceImpl teacherServiceImpl;

    @Captor
    private ArgumentCaptor<Teacher> teacherArgumentCaptor;

    @Test
    public void add_shouldAdd() {
        //given
        Teacher teacher = Teacher.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .languages(List.of(Language.C))
                .build();
        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacher);

        //when
        Teacher teacherResult = teacherServiceImpl.add(teacher);

        //then
        assertEquals(teacher, teacherResult);

        verify(teacherRepository).save(teacherArgumentCaptor.capture());
        Teacher saved = teacherArgumentCaptor.getValue();
        assertEquals(teacher, saved);
    }

    @Test
    public void add_shouldThrowNoEntityExceptionWhenEntityIsNull() {
        Exception e = Assertions.assertThrows(NoEntityException.class, () -> teacherServiceImpl.add(null));
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(e).isExactlyInstanceOf(NoEntityException.class);
        //todo popraw
    }

    @Test
    public void edit_shouldEdit() {
//        //given
        Teacher teacherFromRepo = Teacher.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .languages(List.of(Language.JAVA))
                .build();

        Teacher newTeacher = Teacher.builder()
                .firstName("Maks")
                .lastName("Lewandowski")
                .languages(List.of(Language.CPP))
                .build();

        when(teacherRepository.findById(any(long.class))).thenReturn(Optional.of(teacherFromRepo));
        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacherFromRepo);

        assertNotEquals(newTeacher.getFirstName(), teacherFromRepo.getFirstName());
        assertNotEquals(newTeacher.getLastName(), teacherFromRepo.getLastName());
        assertNotEquals(newTeacher.getLanguages(), teacherFromRepo.getLanguages());

        //when
//        Teacher teacherForEdit = teacherRepository.findById(id).get();

        Teacher teacherResult = teacherServiceImpl.edit(newTeacher, 1L);

        //then
        verify(teacherRepository).findById(1L);
        verify(teacherRepository).save(teacherArgumentCaptor.capture());
        Teacher updated = teacherArgumentCaptor.getValue();
        assertEquals(newTeacher.getFirstName(), updated.getFirstName());
        assertEquals(newTeacher.getLastName(), updated.getLastName());
        assertEquals(newTeacher.getLanguages(), updated.getLanguages());

        assertEquals(newTeacher.getFirstName(), teacherResult.getFirstName());
        assertEquals(newTeacher.getLastName(), teacherResult.getLastName());
        assertEquals(newTeacher.getLanguages(), teacherResult.getLanguages());
    }

    @Test
    public void edit_shouldThrowIllegalArgumentExceptionWhenEntityIsNull() {
        String exceptionMessage = "No entity to edit!";

        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> teacherServiceImpl.edit(null, 1L));
        assertEquals(exceptionMessage, e.getMessage());

        verifyNoInteractions(teacherRepository);
    }

    @Test
    public void edit_shouldThrowNoEntityExceptionWhenEntityIsNull() {
        Long id = 1L;
        String exceptionMessage = "Entity with id " + id + " do not exists";

        when(teacherRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        NoEntityException e = Assertions.assertThrows(NoEntityException.class, () -> teacherServiceImpl.getById(id));
        assertEquals(exceptionMessage, e.getMessage());

        verify(teacherRepository).findById(id);
        verifyNoMoreInteractions(teacherRepository);
    }

    @Test
    public void getAll_shouldFindAll() {
        //given
        Teacher teacher1 = Teacher.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .languages(List.of(Language.JAVA))
                .build();

        Teacher teacher2 = Teacher.builder()
                .id(2L)
                .firstName("Jan")
                .lastName("Kowalski")
                .languages(List.of(Language.JAVA))
                .build();

        List<Teacher> teachers = List.of(teacher1, teacher2);
        when(teacherRepository.findAll()).thenReturn(teachers);

        //when
        List<Teacher> teachersResult = teacherServiceImpl.getAll();

        //then
        assertEquals(teachers, teachersResult);
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
        when(teacherRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(teacher));

        //when
        Teacher teacherResult = teacherServiceImpl.getById(1L);

        //then
        assertEquals(teacher, teacherResult);
    }

    @Test
    public void getById_shouldThrowWrongIdExceptionWhenEntityIsNull() {
        String exceptionMessage = "Id should be not null";

        WrongIdException e = Assertions.assertThrows(WrongIdException.class, () -> teacherServiceImpl.getById(null));
        assertEquals(exceptionMessage, e.getMessage());

        verifyNoInteractions(teacherRepository);
    }

    @Test
    public void getById_shouldThrowNoEntityExceptionWhenEntityIsNull() {
        Long id = 1L;
        String exceptionMessage = "Entity with id " + id + " do not exists";

        when(teacherRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        NoEntityException e = Assertions.assertThrows(NoEntityException.class, () -> teacherServiceImpl.getById(id));
        assertEquals(exceptionMessage, e.getMessage());

        verify(teacherRepository).findById(id);
    }

    // todo zrobic 2 testy dla delete
}