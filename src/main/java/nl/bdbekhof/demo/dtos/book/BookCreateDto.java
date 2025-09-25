package nl.bdbekhof.demo.dtos.book;

import io.swagger.v3.oas.annotations.media.Schema;

public record BookCreateDto(@Schema(example = "The good, the bad and the ugly") String title, @Schema(example = "Sergio Leone") String author) {
}
