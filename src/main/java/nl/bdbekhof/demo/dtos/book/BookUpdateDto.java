package nl.bdbekhof.demo.dtos.book;

import io.swagger.v3.oas.annotations.media.Schema;

public record BookUpdateDto(@Schema(example = "1") Long id,
                            @Schema(example = "The good, the bad and the ugly") String title,
                            @Schema(example = "Sergio Leone") String author
) { }
