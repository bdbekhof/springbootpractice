package nl.bdbekhof.demo.dtos.teacher;

import io.swagger.v3.oas.annotations.media.Schema;

public record TeacherCreateDto(@Schema(example = "Harold") String firstName,
                               @Schema(example = "Frennekan") String lastName,
                               @Schema(example = "Mathematics") String subject,
                               @Schema(example = "H.Frennekan@school.com") String email
) {
    public String getEmail() {
        return this.email;
    }
}
