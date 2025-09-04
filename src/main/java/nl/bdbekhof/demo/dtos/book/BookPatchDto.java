package nl.bdbekhof.demo.dtos.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookPatchDto {
    private String title;
    private String author;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = this.author;
    }
}
