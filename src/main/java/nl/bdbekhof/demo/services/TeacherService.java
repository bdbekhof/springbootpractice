package nl.bdbekhof.demo.services;

import nl.bdbekhof.demo.dtos.teacher.TeacherPatchDto;
import nl.bdbekhof.demo.models.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAll();
    Teacher getOne(Long id);
    Teacher getByEmail(String email);
    Teacher create(Teacher input);
    Teacher update(Long id, Teacher input);
    Teacher patch(Long id, TeacherPatchDto patch);
    void delete(Long id);
}
