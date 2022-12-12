package hu.mik.prog5.assignment.bookrental.service;

import hu.mik.prog5.assignment.bookrental.dao.BookDao;
import hu.mik.prog5.assignment.bookrental.entity.Book;
import hu.mik.prog5.assignment.bookrental.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookDao bookDAO;

    public Book create(Book book) {
        return this.bookDAO.create(book);
    }

    public Book findById(Long id) {
        return this.bookDAO.findById(id);
    }

    public List<Book> findAll() {
        return this.bookDAO.findAll();
    }

    public Book update(Book book) {
        return this.bookDAO.update(book);
    }

    public boolean delete(Long id) {
        return this.bookDAO.delete(id);
    }

    public List<Book> findByGenre(Genre genre) {
        return this.bookDAO.findByGenre(genre);
    }

    public List<Book> findByUsername(String username) {
        return this.bookDAO.findByUsername(username);
    }

    public Book rentBook(Book book) {
        return this.bookDAO.rentBook(book);
    }

    public List<Book> findAllRentedBooks() {
        return this.bookDAO.findAllRentedBooks();
    }

    public Book retrieveBook(Long id) {
        return this.bookDAO.retrieveBook(id);
    }

}
