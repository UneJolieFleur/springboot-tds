package s4.spring.td5.entities;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{

    List<User> findAll();

    User findById(int Id);
    
    User findByLogin(String login);

}