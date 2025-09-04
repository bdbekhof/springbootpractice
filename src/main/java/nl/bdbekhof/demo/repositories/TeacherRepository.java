package nl.bdbekhof.demo.repositories;

import nl.bdbekhof.demo.dtos.teacher.TeacherDto;
import nl.bdbekhof.demo.models.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
    Page<TeacherDto> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);
    Page<TeacherDto> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);
    Page<TeacherDto> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName, Pageable pageable);
}
