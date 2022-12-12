package hu.mik.prog5.assignment.bookrental.dao;

import java.util.List;

public interface CrudDao<T, ID> {

    T create(T object);

    T findById(ID id);

    List<T> findAll();

    T update(T object);

    boolean delete(ID id);
}
