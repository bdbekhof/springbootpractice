package nl.bdbekhof.demo.services;

import nl.bdbekhof.demo.dtos.teacher.TeacherPatchDto;
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
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    // Retrieve a specific teacher by id, if the teacher (id) does not exist, throw a bad request error with
    // explanation why it happened.
    @Override
    public Teacher getOne(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Teacher doesn't exist."));
    }

    // Retrieve a specific teacher by id, if the teacher (email) does not exist, throw a bad request error
    // with explanation why it happened.
    @Override
    public Teacher getByEmail(String email) {
        return teacherRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email doesn't exist."));
    }

    // Create a new teacher, check if the email already exists. If it does, create a conflict error
    @Override
    public Teacher create(Teacher input) {
        if(teacherRepository.existsByEmail(input.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        input.setId(null);
        return teacherRepository.save(input);
    }

    // Update every field of teacher, also checks if the id matches and if the given email already exists.
    @Override
    public Teacher update(Long id, Teacher input) {
        Teacher existingTeacher = getOne(id);

        if(input.getId() != null && !input.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID mismatch.");
        }

        if(input.getEmail() != null && teacherRepository.existsByEmailAndIdNot(input.getEmail(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists.");
        }

        existingTeacher.setFirstName(input.getFirstName());
        existingTeacher.setLastName(input.getLastName());
        existingTeacher.setEmail(input.getEmail());
        existingTeacher.setSubject(input.getSubject());

        return teacherRepository.save(existingTeacher);
    }

    // Only update (patch) fields that have received new information. Also check if the email already exists.
    @Override
    public Teacher patch(Long id, TeacherPatchDto patch) {
        Teacher existingTeacher = getOne(id);

        if(patch.getEmail() != null && teacherRepository.existsByEmailAndIdNot(patch.getEmail(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists.");
        }

        if(patch.getFirstName() != null && !patch.getFirstName().isBlank()) existingTeacher.setFirstName(patch.getFirstName());
        if(patch.getLastName() != null && !patch.getLastName().isBlank()) existingTeacher.setLastName(patch.getLastName());
        if(patch.getEmail() != null && !patch.getEmail().isBlank()) existingTeacher.setEmail(patch.getEmail());
        if(patch.getSubject() != null && !patch.getSubject().isBlank()) existingTeacher.setSubject(patch.getSubject());

        return teacherRepository.save(existingTeacher);
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
