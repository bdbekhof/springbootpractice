package nl.bdbekhof.demo.mappers.teacher;

import nl.bdbekhof.demo.dtos.teacher.TeacherCreateDto;
import nl.bdbekhof.demo.dtos.teacher.TeacherDto;
import nl.bdbekhof.demo.dtos.teacher.TeacherUpdateDto;
import nl.bdbekhof.demo.models.Teacher;

public interface TeacherMapper {
    TeacherDto toDto(Teacher entity);
    Teacher toEntity(TeacherCreateDto dto);
    void updateEntity(TeacherUpdateDto dto, Teacher entity);
}
