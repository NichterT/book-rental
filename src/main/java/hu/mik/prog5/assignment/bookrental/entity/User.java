package hu.mik.prog5.assignment.bookrental.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
    private List<Role> roles;
    
}
