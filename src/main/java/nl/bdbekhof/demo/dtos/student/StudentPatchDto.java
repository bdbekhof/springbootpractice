package nl.bdbekhof.demo.dtos.student;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentPatchDto {
    @Schema(example = "Barry") private String firstName;
    @Schema(example = "Bekhof") private String lastName;

    @Email(message = "Email is invalid.")
    @Schema(example = "barry@example.com")
    private String email;

}
