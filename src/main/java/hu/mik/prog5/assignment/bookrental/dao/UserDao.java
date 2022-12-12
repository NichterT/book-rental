package hu.mik.prog5.assignment.bookrental.dao;

import hu.mik.prog5.assignment.bookrental.entity.User;

public interface UserDao {

    User findByUsername(String username);

}
