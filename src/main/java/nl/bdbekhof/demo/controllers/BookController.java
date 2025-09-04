package nl.bdbekhof.demo.controllers;

import jakarta.validation.Valid;
import nl.bdbekhof.demo.dtos.book.BookDto;
import nl.bdbekhof.demo.models.Book;
import nl.bdbekhof.demo.models.Student;
import nl.bdbekhof.demo.repositories.BookRepository;
import nl.bdbekhof.demo.repositories.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/students/{studentId}/books")
public class BookController {
    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;

    public BookController(BookRepository bookRepository, StudentRepository studentRepository) {
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<BookDto> getAll(@PathVariable Long studentId) {
        return bookRepository.findByStudentId(studentId);
    }

    @GetMapping("/{id}")
    public Book getOne(@PathVariable Long id, @PathVariable Long studentId) {
        return bookRepository.findByIdAndStudentId(id, studentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }

    @PostMapping
    public Book createBook(@PathVariable Long studentId, @RequestBody Book book) {
        Student owner = studentRepository.findById(studentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found."));
        book.setId(null);
        book.setStudent(owner);
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long studentId, @PathVariable Long id, @Valid @RequestBody Book input) {
        Book existingBook = bookRepository.findByIdAndStudentId(id, studentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        if(input.getId() != null && !input.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID mismatch");
        }

        existingBook.setTitle(input.getTitle());
        existingBook.setAuthor(input.getAuthor());

        Book updated = bookRepository.save(existingBook);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> patchBook(@PathVariable Long studentId, @PathVariable Long id, @RequestBody Book patch) {
        Book existingBook = bookRepository.findByIdAndStudentId(id, studentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        if(patch.getTitle() != null && !patch.getTitle().isBlank()) existingBook.setTitle(patch.getTitle());
        if(patch.getAuthor() != null && !patch.getAuthor().isBlank()) existingBook.setAuthor(patch.getAuthor());

        Book updated = bookRepository.save(existingBook);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long studentId, @PathVariable Long id) {
        Book existingBook = bookRepository.findByIdAndStudentId(id, studentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
