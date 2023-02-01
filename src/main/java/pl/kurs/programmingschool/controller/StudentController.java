package pl.kurs.programmingschool.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.programmingschool.model.commands.CreateStudentCommand;
import pl.kurs.programmingschool.model.commands.UpdateStudentCommand;
import pl.kurs.programmingschool.model.dto.StatusDto;
import pl.kurs.programmingschool.model.dto.StudentDto;
import pl.kurs.programmingschool.model.Student;
import pl.kurs.programmingschool.model.mapper.StudentMapper;
import pl.kurs.programmingschool.service.StudentService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAll() {
        return ResponseEntity.ok(studentService.getAll().stream()
                .map(studentMapper::mapFromStudentToStudentDto)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<StudentDto> getStudentById(@PathVariable(name = "id") long id) {
        Student student = studentService.getById(id);
        return ResponseEntity.ok(studentMapper.mapFromStudentToStudentDto(student));
    }

    @PostMapping
    public ResponseEntity<StudentDto> addStudent(@RequestBody @Valid CreateStudentCommand createStudentCommand) {
        Student student = studentMapper.mapFromCreateCommand(createStudentCommand);
        student = studentService.add(student, createStudentCommand.getTeacherId());
        StudentDto studentDto = studentMapper.mapFromStudentToStudentDto(student);
        studentDto.setTeacherId(createStudentCommand.getTeacherId());
        return ResponseEntity.status(HttpStatus.CREATED).body(studentDto);
    }

    @PutMapping
    public ResponseEntity<StudentDto> editStudent(@RequestBody @Valid UpdateStudentCommand updateStudentCommand,
                                                  @PathVariable long id) {
        Student student = studentMapper.mapFromUpdateCommand(updateStudentCommand);
        student = studentService.edit(student, id);
        StudentDto studentDto = studentMapper.mapFromStudentToStudentDto(student);
        return ResponseEntity.status(HttpStatus.OK).body(studentDto);
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<StatusDto> deleteStudent(@PathVariable(name = "id") long id) {
        studentService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new StatusDto("Id: " + id + " deleted"));
    }

}
