package nl.bdbekhof.demo.services;

import nl.bdbekhof.demo.dtos.student.StudentCreateDto;
import nl.bdbekhof.demo.dtos.student.StudentDto;
import nl.bdbekhof.demo.dtos.student.StudentPatchDto;
import nl.bdbekhof.demo.dtos.student.StudentUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAll();
    StudentDto getOne(Long id);
    StudentDto getByEmail(String email);
    StudentDto create(StudentCreateDto input);
    StudentDto update(Long id, StudentUpdateDto input);
    StudentDto patch(Long id, StudentPatchDto patch);
    void delete(Long id);
    Page<StudentDto> getAll(Pageable pageable);
    Page<StudentDto> search(String firstName, String lastName, Pageable pageable);
}
