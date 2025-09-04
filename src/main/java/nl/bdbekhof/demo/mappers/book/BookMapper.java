package nl.bdbekhof.demo.mappers.book;

import nl.bdbekhof.demo.dtos.book.BookCreateDto;
import nl.bdbekhof.demo.dtos.book.BookDto;
import nl.bdbekhof.demo.dtos.book.BookPatchDto;
import nl.bdbekhof.demo.dtos.book.BookUpdateDto;
import nl.bdbekhof.demo.models.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public static BookDto toDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getAuthor(),
                book.getTitle()
        );
    }

    public static Book toEntity(BookCreateDto dto) {
        Book book = new Book();
        book.setTitle(dto.title());
        book.setAuthor(dto.author());

        return book;
    }

    public static void updateEntity(BookUpdateDto dto, Book entity) {
        entity.setTitle(dto.title());
        entity.setAuthor(dto.author());
    }

    public static void patchEntity(BookPatchDto dto, Book entity) {
        if(dto.getAuthor() != null && !dto.getAuthor().equals(entity.getAuthor())) {
            entity.setAuthor(dto.getAuthor());
        }
        if(dto.getTitle() != null && !dto.getTitle().equals(entity.getTitle())) {
            entity.setTitle(dto.getTitle());
        }
    }
}
