package nl.bdbekhof.demo.dtos;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentPatchDto {
    private String firstName;
    private String lastName;

    @Email(message = "Email is invalid.")
    private String email;

}
