package nl.bdbekhof.demo.services;

import nl.bdbekhof.demo.dtos.StudentPatchDto;
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
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student getOne(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found."));
    }

    @Override
    public Student getByEmail(String email) {
        return studentRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found."));
    }

    @Override
    public Student create(Student input) {
        if(studentRepository.existsByEmail(input.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists.");
        }
        input.setId(null);
        return studentRepository.save(input);
    }

    @Override
    public Student update(Long id, Student input) {
        Student existingStudent = getOne(id);

        if(input.getId() != null && !input.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID mismatch");
        }

        if(input.getEmail() != null && studentRepository.existsByEmailAndIdNot(input.getEmail(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists.");
        }

        existingStudent.setFirstName(input.getFirstName());
        existingStudent.setLastName(input.getLastName());
        existingStudent.setEmail(input.getEmail());

        return studentRepository.save(existingStudent);
    }

    @Override
    public Student patch(Long id, StudentPatchDto patch) {
        Student existingStudent = getOne(id);

        if(patch.getEmail() != null && studentRepository.existsByEmailAndIdNot(patch.getEmail(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists.");
        }

        if(patch.getFirstName() != null && !patch.getFirstName().isBlank()) existingStudent.setFirstName(patch.getFirstName());
        if(patch.getLastName() != null && !patch.getLastName().isBlank()) existingStudent.setLastName(patch.getLastName());
        if(patch.getEmail() != null && !patch.getEmail().isBlank()) existingStudent.setEmail(patch.getEmail());

        return studentRepository.save(existingStudent);
    }

    @Override
    public void delete(Long id) {
        if(!studentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public Page<Student> getAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
}
