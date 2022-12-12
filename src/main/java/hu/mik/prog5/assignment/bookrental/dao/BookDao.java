package hu.mik.prog5.assignment.bookrental.dao;

import hu.mik.prog5.assignment.bookrental.entity.Book;
import hu.mik.prog5.assignment.bookrental.entity.Genre;

import java.util.List;

public interface BookDao extends CrudDao<Book, Long> {

    List<Book> findByGenre(Genre genre);

    List<Book> findByUsername(String username);

}
