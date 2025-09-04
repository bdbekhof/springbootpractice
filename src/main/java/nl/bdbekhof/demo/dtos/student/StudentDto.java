package nl.bdbekhof.demo.dtos.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StudentDto(Long id, String firstName, String lastName, String email) {
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
