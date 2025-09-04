package nl.bdbekhof.demo.repositories;

import nl.bdbekhof.demo.dtos.student.StudentDto;
import nl.bdbekhof.demo.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
    Page<StudentDto> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);
    Page<StudentDto> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);
    Page<StudentDto> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName, Pageable pageable);
}
