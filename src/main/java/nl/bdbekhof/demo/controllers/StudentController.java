package nl.bdbekhof.demo.controllers;

import jakarta.validation.Valid;
import nl.bdbekhof.demo.dtos.student.StudentCreateDto;
import nl.bdbekhof.demo.dtos.student.StudentDto;
import nl.bdbekhof.demo.dtos.student.StudentPatchDto;
import nl.bdbekhof.demo.dtos.student.StudentUpdateDto;
import nl.bdbekhof.demo.models.Student;
import nl.bdbekhof.demo.services.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    private Sort parseSort(String sortParam) {
        if(sortParam == null || sortParam.isBlank()) {
            return Sort.by(Sort.Order.asc("id"));
        }

        String[] parts = sortParam.split(",", 2);
        String property = parts[0].trim();
        if(property.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sort property is empty.");
        }

        Sort.Direction direction = Sort.Direction.ASC;
        if(parts.length == 2 && !parts[1].isBlank()) {
            String dir = parts[1].trim();
            if("desc".equalsIgnoreCase(dir)) direction = Sort.Direction.DESC;
            else if(!"asc".equalsIgnoreCase(dir)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sort direction must be 'asc'.");
            }
        }
        return Sort.by(new Sort.Order(direction, property));
    }

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public StudentDto getOne(@PathVariable Long id) {
        return studentService.getOne(id);
    }
    
    @GetMapping(value = "/search", params = "email")
    public StudentDto getByEmail(@RequestParam String email) {
        return studentService.getByEmail(email);
    }

    @GetMapping
    public Page<StudentDto> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName
    ) {
        Sort sortObj = parseSort(sort);
        Pageable pageable = PageRequest.of(page, size, sortObj);
        return studentService.search(firstName, lastName, pageable);
    }
    
    @PostMapping
    public StudentDto createStudent(@Valid @RequestBody StudentCreateDto input) {
        return studentService.create(input);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentUpdateDto input) {
        StudentDto updated = studentService.update(id, input);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> patchStudent(@PathVariable Long id, @RequestBody StudentPatchDto patch) {
        StudentDto updated = studentService.patch(id, patch);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable Long id) {
        studentService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
