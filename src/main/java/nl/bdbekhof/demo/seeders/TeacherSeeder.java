package nl.bdbekhof.demo.seeders;

import nl.bdbekhof.demo.services.TeacherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

public class TeacherSeeder implements CommandLineRunner {
    private final TeacherService teacherService;

    @Value("${app.seed.teachers:20}")
    private int amount;

    public TeacherSeeder(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Override
    public void run(String... args) {
        if(amount <= 0) return;
        if(!teacherService.getAll().isEmpty()) return;

        // TODO: Create seeder runner
    }
}
