package pl.kurs.programmingschool.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.programmingschool.model.Lesson;
import pl.kurs.programmingschool.model.Student;
import pl.kurs.programmingschool.model.commands.CreateLessonCommand;
import pl.kurs.programmingschool.model.commands.CreateStudentCommand;
import pl.kurs.programmingschool.model.commands.UpdateLessonCommand;
import pl.kurs.programmingschool.model.commands.UpdateStudentCommand;
import pl.kurs.programmingschool.model.dto.LessonDto;
import pl.kurs.programmingschool.model.dto.StatusDto;
import pl.kurs.programmingschool.model.dto.StudentDto;
import pl.kurs.programmingschool.model.mapper.LessonMapper;
import pl.kurs.programmingschool.service.LessonService;
import pl.kurs.programmingschool.service.StudentService;
import pl.kurs.programmingschool.service.TeacherService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;
    private final LessonMapper lessonMapper;

    @GetMapping
    public ResponseEntity<List<LessonDto>> getAll() {
        return ResponseEntity.ok(lessonService.getAll().stream()
                .map(lessonMapper::mapFromLessonToLessonDto)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<LessonDto> getLessonById(@PathVariable(name = "id") long id) {
        Lesson lesson = lessonService.getById(id);
        return ResponseEntity.ok(lessonMapper.mapFromLessonToLessonDto(lesson));
    }

    @PostMapping
    public ResponseEntity<LessonDto> addLesson(@RequestBody @Valid CreateLessonCommand createLessonCommand) {
        Lesson lessonForAdd = lessonService.add(createLessonCommand.getStudentId(), createLessonCommand.getTeacherId(), createLessonCommand.getLocalDateTime());
        LessonDto lessonDto = lessonMapper.mapFromLessonToLessonDto(lessonForAdd);
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonDto> editLesson(@RequestBody @Valid UpdateLessonCommand updateLessonCommand,
                                                @PathVariable long id) {
        Lesson lessonForEdit = lessonService.edit(updateLessonCommand.getLocalDateTime(), id);
        LessonDto lessonDto = lessonMapper.mapFromLessonToLessonDto(lessonForEdit);
        return ResponseEntity.status(HttpStatus.OK).body(lessonDto);
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<StatusDto> deleteLesson(@PathVariable(name = "id") long id) {
        lessonService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new StatusDto("Id: " + id + " deleted"));
    }

}
