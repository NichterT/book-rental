package hu.mik.prog5.assignment.bookrental.dao.rowmapper;

import hu.mik.prog5.assignment.bookrental.dao.RoleDao;
import hu.mik.prog5.assignment.bookrental.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class UserRowMapper implements RowMapper<User> {

    private final RoleDao roleDao;

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long userId = rs.getLong("id");

        return User.builder()
                .id(userId)
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .roles(this.roleDao.findByUserId(userId))
                .build();
    }
}
