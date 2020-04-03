package s4.spring.td5.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface ScriptRepository extends JpaRepository<Script,Integer>{

    List<Script> findAll();

    Script findById(int id);
    
    List<Script> findByTitle(String title);

    ArrayList<Script> searchScripstByTitle(String search);
    ArrayList<Script> searchScripstByContent(String search);
    ArrayList<Script> searchScripstByDescription(String search);
    ArrayList<Script> searchScripstByName(String search);
    ArrayList<Script> searchScripstByCategory(String search);
    
}