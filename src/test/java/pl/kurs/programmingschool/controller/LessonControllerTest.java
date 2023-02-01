package pl.kurs.programmingschool.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.kurs.programmingschool.ProgrammingSchoolApplication;
import pl.kurs.programmingschool.model.Language;
import pl.kurs.programmingschool.model.Lesson;
import pl.kurs.programmingschool.model.Student;
import pl.kurs.programmingschool.model.Teacher;
import pl.kurs.programmingschool.model.commands.CreateLessonCommand;
import pl.kurs.programmingschool.model.commands.CreateStudentCommand;
import pl.kurs.programmingschool.model.commands.CreateTeacherCommand;
import pl.kurs.programmingschool.model.dto.LessonDto;
import pl.kurs.programmingschool.model.dto.StudentDto;
import pl.kurs.programmingschool.repository.LessonRepository;
import pl.kurs.programmingschool.repository.StudentRepository;
import pl.kurs.programmingschool.repository.TeacherRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ProgrammingSchoolApplication.class)
@AutoConfigureMockMvc
class LessonControllerTest {

    @Autowired
    private MockMvc postman;

    @Autowired
    private LessonRepository lessonRepository;

    @MockBean
    private TeacherRepository teacherRepository;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
    }

//    @Test
//    void testAddLesson_AllDataAsRequired_ResultsInLessonSavedInDb() throws Exception {
//        //given
//        CreateStudentCommand createStudentCommand = CreateStudentCommand.builder()
//                .firstName("Karol")
//                .lastName("Nowak")
//                .language(Language.JAVA)
//                .teacherId(1L)
//                .build();
//
//        String jsonStudent = objectMapper.writeValueAsString(createStudentCommand);
//
//        Teacher teacher = Teacher.builder()
//                .id(1L)
//                .firstName("Jan")
//                .lastName("Kowalski")
//                .languages(List.of(Language.JAVA))
//                .build();
//
//        CreateTeacherCommand createTeacherCommand = CreateTeacherCommand.builder()
//                .firstName("Jan")
//                .lastName("Kowalski")
//                .languages(List.of(Language.JAVA))
//                .build();
//
//        String jsonTeacher = objectMapper.writeValueAsString(createTeacherCommand);
//
//        CreateLessonCommand createLessonCommand = CreateLessonCommand.builder()
//                .studentId(1L)
//                .teacherId(1L)
//                .localDateTime(LocalDateTime.of(2022,01,20,16,00))
//                .build();
//
//        String json = objectMapper.writeValueAsString(createLessonCommand);
//
////        Teacher teacher = Teacher.builder()
////                .firstName("Jan")
////                .lastName("Kowalski")
////                .languages(List.of(Language.C))
////                .build();
////
////        Student student = Student.builder()
////                .id(1L)
////                .firstName("Karol")
////                .lastName("Nowak")
////                .language(Language.JAVA)
////                .teacher(teacher)
////                .build();
////
////        Lesson lesson = Lesson.builder()
////                .student(student)
////                .teacher(teacher)
////                .localDateTime(LocalDateTime.of(2022,01,20,16,00))
////                .build();
//
//        //when
////        when(studentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(student));
////        when(teacherRepository.findById(anyLong())).thenReturn(Optional.ofNullable(teacher));
////        when(lessonRepository.save(any(Lesson.class))).thenReturn(lesson);
//
//        postman.perform(post("/api/teachers")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonTeacher))
//                .andDo(print())
//                .andExpect(status().isCreated());
//
//        when(teacherRepository.findById(anyLong())).thenReturn(java.util.Optional.of(teacher));
//        postman.perform(post("/api/students")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonStudent))
//                .andDo(print())
//                .andExpect(status().isCreated());
//
//        postman.perform(post("/api/lessons")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.studentId").value(1))
//                .andExpect(jsonPath("$.studentFirstName").value("Karol"))
//                .andExpect(jsonPath("$.studentLastName").value("Nowak"))
//                .andExpect(jsonPath("$.teacherId").value(1))
//                .andExpect(jsonPath("$.teacherFirstName").value("Jan"))
//                .andExpect(jsonPath("$.teacherLastName").value("Kowalski"))
//                .andExpect(jsonPath("$.localDateTime").value(LocalDateTime.of(2022,01,20,16,00)));
//
//        //then
//        String response = postman.perform(MockMvcRequestBuilders.get("/api/lessons/1"))
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//
//        LessonDto saved = objectMapper.readValue(response, LessonDto.class);
//
//        Assertions.assertEquals(createLessonCommand.getStudentId(), saved.getStudentId());
//        Assertions.assertEquals(createLessonCommand.getTeacherId(), saved.getTeacherId());
//        Assertions.assertEquals(createLessonCommand.getLocalDateTime(), saved.getLocalDateTime());
//    }

}