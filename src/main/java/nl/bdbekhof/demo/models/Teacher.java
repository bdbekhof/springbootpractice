package nl.bdbekhof.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Firstname is mandatory.")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Lastname is mandatory.")
    private String lastName;

    @Column(nullable = false, unique = true)
    @Email(message = "Email should be valid.")
    @NotNull
    private String email;

    @Column(nullable = false)
    private String subject;

    public Teacher() {}

    public Teacher(Long id, String firstName, String lastName, String email, String subject) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.subject = subject;
    }
}
