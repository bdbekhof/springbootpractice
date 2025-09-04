package nl.bdbekhof.demo.mappers.book;

import nl.bdbekhof.demo.dtos.book.BookCreateDto;
import nl.bdbekhof.demo.dtos.book.BookDto;
import nl.bdbekhof.demo.dtos.book.BookUpdateDto;
import nl.bdbekhof.demo.models.Book;

public interface BookMapper {
    BookDto toDto(Book entity);
    Book toEntity(BookCreateDto dto);
    void updateEntity(BookUpdateDto dto, Book entity);
}
