package hu.mik.prog5.assignment.bookrental.dao;

import hu.mik.prog5.assignment.bookrental.entity.Role;

import java.util.List;

public interface RoleDao {

    Role findById(Long id);

    List<Role> findByUserId(Long id);
    
}
