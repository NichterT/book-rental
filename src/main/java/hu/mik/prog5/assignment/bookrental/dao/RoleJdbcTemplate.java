package hu.mik.prog5.assignment.bookrental.dao;

import hu.mik.prog5.assignment.bookrental.dao.rowmapper.RoleRowMapper;
import hu.mik.prog5.assignment.bookrental.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class RoleJdbcTemplate implements RoleDao {

    private final JdbcTemplate jdbcTemplate;
    private final RoleRowMapper rowMapper;

    @Override
    public Role findById(Long id) {
        return this.jdbcTemplate.queryForObject("SELECT r.id, r.role_name FROM role r WHERE r.id = ?", this.rowMapper, id);
    }

    @Override
    public List<Role> findByUserId(Long id) {
        return this.jdbcTemplate.query("SELECT r.id, r.role_name FROM role r JOIN user_role ur on r.id = ur.role_id JOIN users u on u.id = ur.user_id WHERE U.id = ?", this.rowMapper, id);
    }

}

