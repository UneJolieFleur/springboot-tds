package s4.spring.td5.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer>{

    List<Category> findAll();

    Category findByName(String name);

    Category findById(int id);

}