package pl.kurs.programmingschool.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.kurs.programmingschool.ProgrammingSchoolApplication;
import pl.kurs.programmingschool.model.Language;
import pl.kurs.programmingschool.model.Teacher;
import pl.kurs.programmingschool.model.commands.CreateStudentCommand;
import pl.kurs.programmingschool.model.dto.StudentDto;
import pl.kurs.programmingschool.repository.StudentRepository;
import pl.kurs.programmingschool.repository.TeacherRepository;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ProgrammingSchoolApplication.class)
@AutoConfigureMockMvc
//@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Autowired
    private MockMvc postman;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TeacherRepository teacherRepository;

    @BeforeEach
    public void init() {
    }

    @Test
    void testAddStudent_AllDataAsRequired_ResultsInStudentSavedInDb() throws Exception {
        //given
        CreateStudentCommand createStudentCommand = CreateStudentCommand.builder()
                .firstName("Piotr")
                .lastName("Kowalski")
                .language(Language.JAVA)
                .teacherId(1L)
                .build();

        String json = objectMapper.writeValueAsString(createStudentCommand);

        Teacher teacher = Teacher.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .languages(List.of(Language.JAVA))
                .build();

        //when
        when(teacherRepository.findById(anyLong())).thenReturn(java.util.Optional.of(teacher));

        postman.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Piotr"))
                .andExpect(jsonPath("$.lastName").value("Kowalski"))
                .andExpect(jsonPath("$.language", equalTo(Language.JAVA.name())))
                .andExpect(jsonPath("$.teacherId").value(1L));

        //then
        String response = postman.perform(MockMvcRequestBuilders.get("/api/students/1"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        StudentDto saved = objectMapper.readValue(response, StudentDto.class);

        Assertions.assertEquals(createStudentCommand.getFirstName(), saved.getFirstName());
        Assertions.assertEquals(createStudentCommand.getLastName(), saved.getLastName());
        Assertions.assertEquals(createStudentCommand.getLanguage(), saved.getLanguage());
    }

}