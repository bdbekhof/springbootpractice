package nl.bdbekhof.demo.controllers;

import jakarta.validation.Valid;
import nl.bdbekhof.demo.dtos.StudentPatchDto;
import nl.bdbekhof.demo.models.Student;
import nl.bdbekhof.demo.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public Student getOne(@PathVariable Long id) {
        return studentService.getOne(id);
    }
    
    @GetMapping(value = "/search", params = "email")
    public Student getByEmail(@RequestParam String email) {
        return studentService.getByEmail(email);
    }
    
    @PostMapping
    public Student createStudent(@Valid @RequestBody Student input) {
        return studentService.create(input);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody Student input) {
        Student updated = studentService.update(id, input);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Student> patchStudent(@PathVariable Long id, @RequestBody StudentPatchDto patch) {
        Student updated = studentService.patch(id, patch);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        studentService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
