package pl.kurs.programmingschool.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.kurs.programmingschool.ProgrammingSchoolApplication;
import pl.kurs.programmingschool.model.Language;
import pl.kurs.programmingschool.model.Teacher;
import pl.kurs.programmingschool.model.commands.CreateTeacherCommand;
import pl.kurs.programmingschool.model.dto.TeacherDto;
import pl.kurs.programmingschool.repository.TeacherRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ProgrammingSchoolApplication.class)
@AutoConfigureMockMvc
class TeacherControllerTest {

    @Autowired
    private MockMvc postman;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
    }

    @Test
    void testAddTeacher_AllDataAsRequired_ResultsInTeacherSavedInDb() throws Exception {
        //given
//        Teacher teacher = Teacher.builder()
//                .firstName("Jan")
//                .lastName("Nowak")
//                .languages(List.of(Language.C))
//                .build();

//        CreateTeacherCommand createTeacherCommand = new CreateTeacherCommand("Jan", "Nowak", List.of(Language.C));
        CreateTeacherCommand createTeacherCommand = CreateTeacherCommand.builder()
                .firstName("Jan")
                .lastName("Nowak")
                .languages(List.of(Language.C))
                .build();


        String json = objectMapper.writeValueAsString(createTeacherCommand);

        //when
        postman.perform(post("/api/teachers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Jan"))
                .andExpect(jsonPath("$.lastName").value("Nowak"))
                .andExpect(jsonPath("$.languages", equalTo(List.of(Language.C.name()))));

        //then
//
//        Teacher saved = teacherRepository.findById(1L)
//                .orElseThrow();
//        saved.getLanguages();

        String response2 = postman.perform(MockMvcRequestBuilders.get("/api/teachers/1"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        TeacherDto saved = objectMapper.readValue(response2, TeacherDto.class);

        Assertions.assertEquals(createTeacherCommand.getFirstName(), saved.getFirstName());
        Assertions.assertEquals(createTeacherCommand.getLastName(), saved.getLastName());
        Assertions.assertEquals(createTeacherCommand.getLanguages(), saved.getLanguages());
    }

}