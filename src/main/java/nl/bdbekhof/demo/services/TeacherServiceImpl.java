package nl.bdbekhof.demo.services;

import nl.bdbekhof.demo.dtos.teacher.TeacherCreateDto;
import nl.bdbekhof.demo.dtos.teacher.TeacherDto;
import nl.bdbekhof.demo.dtos.teacher.TeacherPatchDto;
import nl.bdbekhof.demo.dtos.teacher.TeacherUpdateDto;
import nl.bdbekhof.demo.mappers.teacher.TeacherMapper;
import nl.bdbekhof.demo.models.Teacher;
import nl.bdbekhof.demo.repositories.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// Random comments are added to make sure I know what things do.

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    // Make teacherRepository usable for methods.
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    // Retrieve all records of teachers
    @Override
    public List<TeacherDto> getAll() {
        return teacherRepository.findAll()
                .stream()
                .map(TeacherMapper::toDto)
                .toList();
    }

    // Retrieve a specific teacher by id, if the teacher (id) does not exist, throw a bad request error with
    // explanation why it happened.
    @Override
    public TeacherDto getOne(Long id) {
        Teacher teacher = teacherRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Teacher doesn't exist."));

        return TeacherMapper.toDto(teacher);
    }

    // Retrieve a specific teacher by id, if the teacher (email) does not exist, throw a bad request error
    // with explanation why it happened.
    @Override
    public TeacherDto getByEmail(String email) {
        Teacher teacher = teacherRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email doesn't exist."));

        return TeacherMapper.toDto(teacher);
    }

    // Create a new teacher, check if the email already exists. If it does, create a conflict error
    @Override
    public TeacherDto create(TeacherCreateDto input) {
        if(teacherRepository.existsByEmail(input.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        Teacher entity = TeacherMapper.toEntity(input);
        Teacher saved = teacherRepository.save(entity);

        return TeacherMapper.toDto(saved);
    }

    // Update every field of teacher, also checks if the id matches and if the given email already exists.
    @Override
    public TeacherDto update(Long id, TeacherUpdateDto input) {
        Teacher existingTeacher = teacherRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found."));

        if(input.id() != null && !input.id().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID mismatch.");
        }

        if(input.getEmail() != null && teacherRepository.existsByEmailAndIdNot(input.getEmail(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists.");
        }

        Teacher saved = teacherRepository.save(existingTeacher);

        return TeacherMapper.toDto(saved);
    }

    // Only update (patch) fields that have received new information. Also check if the email already exists.
    @Override
    public TeacherDto patch(Long id, TeacherPatchDto patch) {
        Teacher existingTeacher = teacherRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));

        if(patch.getEmail() != null && teacherRepository.existsByEmailAndIdNot(patch.getEmail(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists.");
        }

        TeacherMapper.patchEntity(patch, existingTeacher);
        Teacher patched = teacherRepository.save(existingTeacher);

        return TeacherMapper.toDto(patched);
    }

    // Delete a teacher.
    @Override
    public void delete(Long id) {
        if(!teacherRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher doesn't exist.");
        }
        teacherRepository.deleteById(id);
    }
}
