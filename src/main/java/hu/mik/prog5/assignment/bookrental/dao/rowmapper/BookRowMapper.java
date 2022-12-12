package hu.mik.prog5.assignment.bookrental.dao.rowmapper;

import hu.mik.prog5.assignment.bookrental.entity.Book;
import hu.mik.prog5.assignment.bookrental.entity.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setIsbn(rs.getString("isbn"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setPublisher(rs.getString("publisher"));
        book.setPageNumber(rs.getInt("page_number"));
        book.setGenre(Genre.valueOf(rs.getString("genre")));
        book.setRentedBy(rs.getString("rented_by"));
        book.setRentedUntil(rs.getString("rented_until"));
        book.setIsRented(rs.getBoolean("is_rented"));

        return book;
    }
}
