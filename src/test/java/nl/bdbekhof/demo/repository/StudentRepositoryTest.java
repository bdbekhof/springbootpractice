package nl.bdbekhof.demo.repository;

import nl.bdbekhof.demo.models.Student;
import nl.bdbekhof.demo.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class StudentRepositoryTest {
    @Autowired
    StudentRepository repo;

    @Test
    void findByEmail_returns_entity() {
        Student s = new Student();
        s.setFirstName("A");
        s.setLastName("B");
        s.setEmail("a@b.com");
        repo.save(s);

        Optional<Student> found = repo.findByEmail("a@b.com");
        assertThat(found).isPresent();
    }
}
