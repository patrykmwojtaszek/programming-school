package pl.kurs.programmingschool.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.programmingschool.model.commands.CreateTeacherCommand;
import pl.kurs.programmingschool.model.commands.UpdateTeacherCommand;
import pl.kurs.programmingschool.model.dto.StatusDto;
import pl.kurs.programmingschool.model.dto.TeacherDto;
import pl.kurs.programmingschool.model.Teacher;
import pl.kurs.programmingschool.model.mapper.TeacherMapper;
import pl.kurs.programmingschool.service.TeacherService;
import pl.kurs.programmingschool.service.impl.TeacherServiceImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherServiceImpl;
    private final TeacherMapper teacherMapper;

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAll() {
        return ResponseEntity.ok(teacherServiceImpl.getAll().stream()
                .map(teacherMapper::mapFromTeacherToTeacherDto)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable long id) {
        TeacherDto teacherDto = teacherMapper.mapFromTeacherToTeacherDto(teacherServiceImpl.getById(id));
        return ResponseEntity.ok(teacherDto);
    }

    @PostMapping
    public ResponseEntity<TeacherDto> addTeacher(@RequestBody @Valid CreateTeacherCommand createTeacherCommand) {
        Teacher teacherForAdd = teacherMapper.mapFromCreateCommand(createTeacherCommand);
        teacherForAdd = teacherServiceImpl.add(teacherForAdd);
        TeacherDto teacherDto = teacherMapper.mapFromTeacherToTeacherDto(teacherForAdd);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherDto);
    }

    @PutMapping
    public ResponseEntity<TeacherDto> editTeacher(@RequestBody @Valid UpdateTeacherCommand updateTeacherCommand,
                                                  @PathVariable long id) {
        Teacher teacherForEdit = teacherMapper.mapFromUpdateCommand(updateTeacherCommand);
        teacherForEdit = teacherServiceImpl.edit(teacherForEdit, id);
        TeacherDto teacherDto = teacherMapper.mapFromTeacherToTeacherDto(teacherForEdit);
        return ResponseEntity.status(HttpStatus.OK).body(teacherDto);
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<StatusDto> deleteTeacher(@PathVariable long id) {
        teacherServiceImpl.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new StatusDto("Id: " + id + " deleted"));
    }
}
