package s4.spring.td2.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgaRepository extends JpaRepository<Organization, Integer> {
    List<Organization> findByDomain(String domain);
    List<Organization> findByName(String name);
}