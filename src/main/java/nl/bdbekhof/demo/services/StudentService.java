package nl.bdbekhof.demo.services;

import nl.bdbekhof.demo.dtos.StudentPatchDto;
import nl.bdbekhof.demo.models.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAll();
    Student getOne(Long id);
    Student getByEmail(String email);
    Student create(Student input);
    Student update(Long id, Student input);
    Student patch(Long id, StudentPatchDto patch);
    void delete(Long id);
}
