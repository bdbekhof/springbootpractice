package nl.bdbekhof.demo.repositories;

import nl.bdbekhof.demo.dtos.book.BookDto;
import nl.bdbekhof.demo.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<BookDto> findByStudentId(Long studentId);
    Optional<Book> findByIdAndStudentId(Long id, Long studentId);
    Optional<Book> findByStudentIdAndAuthor(Long studentId, String author);
}
