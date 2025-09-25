package nl.bdbekhof.demo.dtos.teacher;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherPatchDto {
    @Schema(example = "Harold") private String firstName;
    @Schema(example = "Frennekan") private String lastName;
    @Schema(example = "Mathematics") private String subject;

    @Schema(example = "H.Frennekan@school.com")
    @Email(message = "Email is invalid.")
    private String email;
}
