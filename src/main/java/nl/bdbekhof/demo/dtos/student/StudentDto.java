package nl.bdbekhof.demo.dtos.student;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StudentDto(@Schema(example = "52") Long id,
                         @Schema(example = "Barry") String firstName,
                         @Schema(example = "Bekhof") String lastName,
                         @Schema(example = "barry@example.com") String email
) {
    public String getEmail() {
        return this.email;
    }

    public void setId(Object o) {
    }

    public void setFirstName(@NotBlank(message = "First name is mandatory") String firstName) {
    }

    public void setLastName(@NotBlank(message = "Last name is mandatory") String lastName) {
    }

    public void setEmail(@NotBlank @Email(message = "Email should be valid") String email) {

    }
}
