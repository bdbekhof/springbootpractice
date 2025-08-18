package nl.bdbekhof.demo.repositories;

import nl.bdbekhof.demo.models.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
    Page<Teacher> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);
    Page<Teacher> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);
    Page<Teacher> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName, Pageable pageable);
}
