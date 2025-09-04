package nl.bdbekhof.demo.services;

import jakarta.validation.Valid;
import nl.bdbekhof.demo.dtos.student.StudentCreateDto;
import nl.bdbekhof.demo.dtos.student.StudentDto;
import nl.bdbekhof.demo.dtos.student.StudentPatchDto;
import nl.bdbekhof.demo.dtos.student.StudentUpdateDto;
import nl.bdbekhof.demo.mappers.student.StudentMapper;
import nl.bdbekhof.demo.models.Student;
import nl.bdbekhof.demo.repositories.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDto> getAll() {
        return studentRepository.findAll().stream().map(StudentMapper::toDto).toList();
    }

    @Override
    public StudentDto getOne(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found."));
        return StudentMapper.toDto(student);
    }

    @Override
    public StudentDto getByEmail(String email) {
        Student student = studentRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found."));
        return StudentMapper.toDto(student);
    }

    @Override
    public StudentDto create(StudentCreateDto input) {
        if(studentRepository.existsByEmail(input.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists.");
        }

        Student entity = StudentMapper.toEntity(input);
        Student saved = studentRepository.save(entity);

        return StudentMapper.toDto(saved);
    }

    @Override
    public StudentDto update(Long id, StudentUpdateDto input) {
        Student existingStudent = studentRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found."));

        if(input.id() != null && !input.id().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID mismatch.");
        }

        StudentMapper.updateEntity(input, existingStudent);

        Student saved = studentRepository.save(existingStudent);
        return StudentMapper.toDto(saved);
    }

    @Override
    public StudentDto patch(Long id, StudentPatchDto patch) {
        Student existingStudent = studentRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found."));

        if(patch.getEmail() != null && studentRepository.existsByEmailAndIdNot(patch.getEmail(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists.");
        }

        StudentMapper.patchEntity(patch, existingStudent);
        Student patched = studentRepository.save(existingStudent);

        return StudentMapper.toDto(patched);
    }

    @Override
    public void delete(Long id) {
        if(!studentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public Page<StudentDto> getAll(Pageable pageable) {
        return studentRepository.findAll(pageable).map(StudentMapper::toDto);
    }

    @Override
    public Page<StudentDto> search(String firstName, String lastName, Pageable pageable) {
        boolean hasFirst = firstName != null && !firstName.isBlank();
        boolean hasLast = lastName != null && !lastName.isBlank();

        Page<StudentDto> result;
        if(!hasFirst && !hasLast) {
            result = studentRepository.findAll(pageable).map(StudentMapper::toDto);
        } else if(!hasLast) {
            result = studentRepository.findByFirstNameContainingIgnoreCase(firstName, pageable);
        } else if(!hasFirst) {
            result = studentRepository.findByLastNameContainingIgnoreCase(lastName, pageable);
        } else {
            result = studentRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName, pageable);
        }

        return result;
    }
}
