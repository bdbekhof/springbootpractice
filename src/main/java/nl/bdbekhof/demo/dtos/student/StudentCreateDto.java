package nl.bdbekhof.demo.dtos.student;

import io.swagger.v3.oas.annotations.media.Schema;

public record StudentCreateDto(
        @Schema(example = "Barry") String firstName,
        @Schema(example = "Bekhof") String lastName,
        @Schema(example = "barry@example.com") String email
) {
    public void setId(Object o) {
    }

    public String getEmail() {
        return this.email;
    }
}
