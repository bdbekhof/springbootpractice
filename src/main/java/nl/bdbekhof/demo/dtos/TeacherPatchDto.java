package nl.bdbekhof.demo.dtos;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherPatchDto {
    private String firstName;
    private String lastName;
    private String subject;

    @Email(message = "Email is invalid.")
    private String email;
}
