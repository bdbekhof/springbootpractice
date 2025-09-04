package nl.bdbekhof.demo.seeders;

import nl.bdbekhof.demo.models.Student;
import nl.bdbekhof.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class StudentSeeder implements CommandLineRunner {
    private final StudentService studentService;

    // Seeding 10, turn to 0 if you don't want to seed.
    @Value("${app.seed.students:10}")
    private int amount;

    public StudentSeeder(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) {
        // Doesn't seed if there are database entries.
        if(amount <= 0) return;
        if(!studentService.getAll().isEmpty()) return;

        for(int i = 1; i <= amount; i++) {
            Student s = new Student();
            s.setFirstName("Demo " + i);
            s.setLastName("Student");
            s.setEmail("demo"+ i + "@example.com");
//            studentService.create(s);
        }
    }
}
