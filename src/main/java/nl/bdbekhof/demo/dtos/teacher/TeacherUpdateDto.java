package nl.bdbekhof.demo.dtos.teacher;

import io.swagger.v3.oas.annotations.media.Schema;

public record TeacherUpdateDto(@Schema(example = "1") Long id,
                               @Schema(example = "Harold") String firstName,
                               @Schema(example = "Frennekan") String lastName,
                               @Schema(example = "H.Frennekan@school.com") String email,
                               @Schema(example = "Mathematics") String subject
) {

    public String getEmail() {
        return this.email;
    }
}
