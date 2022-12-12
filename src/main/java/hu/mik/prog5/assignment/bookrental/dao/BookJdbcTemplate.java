package hu.mik.prog5.assignment.bookrental.dao;

import hu.mik.prog5.assignment.bookrental.dao.rowmapper.BookRowMapper;
import hu.mik.prog5.assignment.bookrental.entity.Book;
import hu.mik.prog5.assignment.bookrental.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class BookJdbcTemplate implements BookDao {

    private final JdbcTemplate jdbcTemplate;
    private final BookRowMapper rowMapper;

    @Override
    public Book create(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(connection -> {
            PreparedStatementCreatorFactory psFactory = new PreparedStatementCreatorFactory(
                    "INSERT INTO book (isbn, title, author, publisher, page_number, genre, is_rented) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.SMALLINT, Types.VARCHAR, Types.BOOLEAN
            );
            psFactory.setReturnGeneratedKeys(true);

            PreparedStatementCreator ps = psFactory.newPreparedStatementCreator(
                    List.of(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPageNumber(), book.getGenre().toString(), false)
            );

            return ps.createPreparedStatement(connection);

        }, keyHolder);

        return this.findById(((Integer) keyHolder.getKeys().get("id")).longValue());
    }

    @Override
    public Book findById(Long id) {
        return this.jdbcTemplate.queryForObject("SELECT b.id, b.isbn, b.title, b.author, b.publisher, b.page_number, b.genre, b.rented_by, b.rented_until, b.is_rented FROM book b WHERE b.id = ?",
                this.rowMapper, id);
    }

    @Override
    public List<Book> findAll() {
        return this.jdbcTemplate.query("SELECT b.id, b.isbn, b.title, b.author, b.publisher, b.page_number, b.genre, b.rented_by, b.rented_until, b.is_rented FROM book b", this.rowMapper);
    }

    @Override
    public Book update(Book book) {
        this.jdbcTemplate.update("UPDATE book b SET isbn = ?, title = ?, author = ?, publisher = ?, page_number = ?, genre = ? WHERE b.id = ?",
                book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPageNumber(), book.getGenre().toString(), book.getId());

        return this.findById(book.getId());
    }

    @Override
    public boolean delete(Long id) {
        return this.jdbcTemplate.update("DELETE FROM book b WHERE b.id = ? ", id) == 1;
    }

    @Override
    public List<Book> findByGenre(Genre genre) {
        return this.jdbcTemplate.query("SELECT b.id, b.isbn, b.title, b.author, b.publisher, b.page_number, b.genre, b.rented_by, b.rented_until, b.is_rented FROM book b WHERE b.genre = ?",
                this.rowMapper, genre);
    }

    @Override
    public List<Book> findByUsername(String username) {
        return this.jdbcTemplate.query("SELECT b.id, b.isbn, b.title, b.author, b.publisher, b.page_number, b.genre, b.rented_by, b.rented_until, b.is_rented FROM book b WHERE b.rented_by = ?",
                this.rowMapper, username);
    }

    @Override
    public Book rentBook(Book book) {
        this.jdbcTemplate.update("UPDATE book b SET rented_by = ?, rented_until = ?, is_rented = ? WHERE b.id = ?",
                book.getRentedBy(), LocalDate.parse(book.getRentedUntil()), book.getIsRented(), book.getId());

        return this.findById(book.getId());
    }

    @Override
    public List<Book> findAllRentedBooks() {
        return this.jdbcTemplate.query("SELECT b.id, b.isbn, b.title, b.author, b.publisher, b.page_number, b.genre, b.rented_by, b.rented_until, b.is_rented FROM book b WHERE b.is_rented = true", this.rowMapper);
    }

    @Override
    public Book retrieveBook(Long id) {
        this.jdbcTemplate.update("UPDATE book b SET rented_by = ?, rented_until = ?, is_rented = ? WHERE b.id = ?",
                null, null, false, id);

        return this.findById(id);
    }
}
