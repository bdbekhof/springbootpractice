package nl.bdbekhof.demo.dtos.book;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookPatchDto {
    @Schema(example = "The good, the bad and the ugly") private String title;
    @Schema(example = "Sergio Leone") private String author;

    public void setAuthor(String author) {
        this.author = this.author;
    }
}
