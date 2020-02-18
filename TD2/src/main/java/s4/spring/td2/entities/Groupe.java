package s4.spring.td2.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Groupe {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
     
    @ManyToOne
    private Organization organization;
    
    @ManyToMany
    @JoinTable(name = "user_group")
    private List<User> users;
    
}