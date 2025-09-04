package nl.bdbekhof.demo.services;

import nl.bdbekhof.demo.dtos.teacher.TeacherCreateDto;
import nl.bdbekhof.demo.dtos.teacher.TeacherDto;
import nl.bdbekhof.demo.dtos.teacher.TeacherPatchDto;
import nl.bdbekhof.demo.dtos.teacher.TeacherUpdateDto;
import nl.bdbekhof.demo.models.Teacher;

import java.util.List;

public interface TeacherService {
    List<TeacherDto> getAll();
    TeacherDto getOne(Long id);
    TeacherDto getByEmail(String email);
    TeacherDto create(TeacherCreateDto input);
    TeacherDto update(Long id, TeacherUpdateDto input);
    TeacherDto patch(Long id, TeacherPatchDto patch);
    void delete(Long id);
}
