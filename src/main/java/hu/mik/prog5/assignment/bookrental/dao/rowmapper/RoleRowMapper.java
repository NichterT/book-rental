package hu.mik.prog5.assignment.bookrental.dao.rowmapper;

import hu.mik.prog5.assignment.bookrental.entity.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RoleRowMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Role.builder().id(rs.getLong("id")).name(rs.getString("role_name")).build();
    }

}
