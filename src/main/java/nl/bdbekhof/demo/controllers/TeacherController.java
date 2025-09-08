package nl.bdbekhof.demo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import nl.bdbekhof.demo.dtos.teacher.TeacherCreateDto;
import nl.bdbekhof.demo.dtos.teacher.TeacherDto;
import nl.bdbekhof.demo.dtos.teacher.TeacherPatchDto;
import nl.bdbekhof.demo.dtos.teacher.TeacherUpdateDto;
import nl.bdbekhof.demo.models.Teacher;
import nl.bdbekhof.demo.services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Teachers", description = "Management of teachers")
@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Operation(summary = "Find teachers by id", description = "Supports searching for teachers by id.")
    @GetMapping("/{id}")
    public TeacherDto getOne(@PathVariable Long id) {
        return teacherService.getOne(id);
    }

    @Operation(summary = "Create a teacher", description = "Has the ability to create a new teacher.")
    @PostMapping
    public TeacherDto createTeacher(@Valid @RequestBody TeacherCreateDto input) {
        return teacherService.create(input);
    }

    @Operation(summary = "Update a teacher", description = "Updates every field of a teacher.")
    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable Long id, @Valid @RequestBody TeacherUpdateDto input) {
        TeacherDto updated = teacherService.update(id, input);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @Operation(summary = "Patch a teacher", description = "Only updates altered fields of a teacher.")
    @PatchMapping("/{id}")
    public ResponseEntity<TeacherDto> patchTeacher(@PathVariable Long id, @RequestBody TeacherPatchDto patch) {
        TeacherDto updated = teacherService.patch(id, patch);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @Operation(summary = "Delete a teacher", description = "Deletes the selected teacher.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable Long id) {
        teacherService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
