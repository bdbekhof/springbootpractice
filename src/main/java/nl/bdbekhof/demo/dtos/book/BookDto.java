package nl.bdbekhof.demo.dtos.book;

import io.swagger.v3.oas.annotations.media.Schema;

public record BookDto(@Schema(example = "1") Long id,
                      @Schema(example = "Sergio Leone") String author,
                      @Schema(example = "The good, the bad and the ugly") String title
) { }
