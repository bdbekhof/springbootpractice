package nl.bdbekhof.demo.dtos.student;

import io.swagger.v3.oas.annotations.media.Schema;

public record StudentUpdateDto(@Schema(example = "52") Long id,
                               @Schema(example = "Barry") String firstName,
                               @Schema(example = "Bekhof") String lastName,
                               @Schema(example = "barry@example.com") String email
) { }
