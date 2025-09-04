package nl.bdbekhof.demo.controllers;

import jakarta.validation.Valid;
import nl.bdbekhof.demo.dtos.teacher.TeacherPatchDto;
import nl.bdbekhof.demo.models.Teacher;
import nl.bdbekhof.demo.services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/{id}")
    public Teacher getOne(@PathVariable Long id) {
        return teacherService.getOne(id);
    }

    @PostMapping
    public Teacher createTeacher(@Valid @RequestBody Teacher input) {
        return teacherService.create(input);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @Valid @RequestBody Teacher input) {
        Teacher updated = teacherService.update(id, input);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Teacher> patchTeacher(@PathVariable Long id, @RequestBody TeacherPatchDto patch) {
        Teacher updated = teacherService.patch(id, patch);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable Long id) {
        teacherService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
