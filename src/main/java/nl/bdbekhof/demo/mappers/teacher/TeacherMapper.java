package nl.bdbekhof.demo.mappers.teacher;

import nl.bdbekhof.demo.dtos.teacher.TeacherCreateDto;
import nl.bdbekhof.demo.dtos.teacher.TeacherDto;
import nl.bdbekhof.demo.dtos.teacher.TeacherPatchDto;
import nl.bdbekhof.demo.dtos.teacher.TeacherUpdateDto;
import nl.bdbekhof.demo.models.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {
    public static TeacherDto toDto(Teacher teacher) {
        return new TeacherDto(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getEmail(),
                teacher.getSubject()
        );
    }

    public static Teacher toEntity(TeacherCreateDto dto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(dto.firstName());
        teacher.setLastName(dto.lastName());
        teacher.setEmail(dto.email());
        teacher.setSubject(dto.subject());

        return teacher;
    }

    public static void updateEntity(TeacherUpdateDto dto, Teacher entity) {
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setEmail(dto.email());
        entity.setSubject(dto.subject());
    }

    public static void patchEntity(TeacherPatchDto dto, Teacher entity) {
        if(dto.getFirstName() != null && !dto.getFirstName().equals(entity.getFirstName())) {
            entity.setFirstName(dto.getFirstName());
        }
        if(dto.getLastName() != null && !dto.getLastName().equals(entity.getLastName())) {
            entity.setLastName(dto.getLastName());
        }
        if(dto.getEmail() != null && !dto.getEmail().equals(entity.getEmail())) {
            entity.setEmail(dto.getEmail());
        }
        if(dto.getSubject() != null && !dto.getSubject().equals(entity.getSubject())) {
            entity.setSubject(dto.getSubject());
        }
    }
}
