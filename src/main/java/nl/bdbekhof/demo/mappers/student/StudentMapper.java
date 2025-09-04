package nl.bdbekhof.demo.mappers.student;

import nl.bdbekhof.demo.dtos.student.StudentCreateDto;
import nl.bdbekhof.demo.dtos.student.StudentDto;
import nl.bdbekhof.demo.dtos.student.StudentPatchDto;
import nl.bdbekhof.demo.dtos.student.StudentUpdateDto;
import nl.bdbekhof.demo.models.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public static StudentDto toDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }

    public static Student toEntity(StudentCreateDto dto) {
        Student student = new Student();
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());

        return student;
    }

    public static void updateEntity(StudentUpdateDto dto, Student entity) {
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setEmail(dto.email());
    }

    public static void patchEntity(StudentPatchDto dto, Student entity) {
        if(dto.getFirstName() != null && !dto.getFirstName().equals(entity.getFirstName())) {
            entity.setFirstName(dto.getFirstName());
        }
        if(dto.getLastName() != null && !dto.getLastName().equals(entity.getLastName())) {
            entity.setLastName(dto.getLastName());
        }
        if(dto.getEmail() != null && !dto.getEmail().equals(entity.getEmail())) {
            entity.setEmail(dto.getEmail());
        }
    }
}