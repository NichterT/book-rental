package hu.mik.prog5.assignment.bookrental.dao;

import hu.mik.prog5.assignment.bookrental.dao.rowmapper.UserRowMapper;
import hu.mik.prog5.assignment.bookrental.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserJdbcTemplate implements UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper rowMapper;

    @Override
    public User findByUsername(String username) {
        return this.jdbcTemplate.queryForObject("SELECT u.id, u.username, u.password FROM users u WHERE u.username = ?", this.rowMapper, username);
    }

}
