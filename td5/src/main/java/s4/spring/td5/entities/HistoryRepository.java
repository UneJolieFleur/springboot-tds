package s4.spring.td5.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History,Integer>{

    List<History> findAll();

    History findById(int id);

    List<History> findByDate(String date);

}