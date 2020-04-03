package s4.spring.td5.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language,Integer>{

    List<Language> findAll();

    Language findByName(String name);
    
    List<Language> findById(int id);

}