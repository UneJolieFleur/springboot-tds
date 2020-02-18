package s4.spring.td2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import s4.spring.td2.entities.OrgaRepository;
import s4.spring.td2.entities.Organization;
@SessionAttributes({"orgas","edit"})
@Controller
@RequestMapping("/orgas/")
public class OrgasController {
	
     
    @Autowired
    private OrgaRepository repo;
     
   
    
    @PostMapping("/new")
    @ResponseBody
    public RedirectView newOrga(Organization orga, @SessionAttribute List<Organization> orgas) {
        repo.saveAndFlush(orga);
        orgas.add(orga);
        return new RedirectView("/orgas/index");
    }
    
    @PostMapping("/edit/{id}")
    @ResponseBody
    public RedirectView editOrga(@PathVariable int id,@SessionAttribute Organization orga, @SessionAttribute("orgas") List<Organization> orgas) {
        repo.getOne(orga.getId()).setName(orga.getName());
        repo.getOne(orga.getId()).setDomain(orga.getDomain());
        repo.getOne(orga.getId()).setAliases(orga.getAliases());
        for(int i=0;i<orgas.size();i++) {
        	if(id==orgas.get(i).getId()) {
        		orgas.get(i).setName(orga.getName());
        		orgas.get(i).setDomain(orga.getDomain());
        		orgas.get(i).setAliases(orga.getAliases());

        	}
        }
        return new RedirectView("/orgas/index");
    }
    
    
    @GetMapping("index")
    public String listeOrga() {
    	return "main";
    }
    
    @GetMapping("edit/{id}")
    public String editOrga1(@PathVariable int id,@SessionAttribute Organization edit, @SessionAttribute("orgas") List<Organization> orgas) {
    	for(Organization o:orgas){
	    	if(o.getId()==id) {
	    		edit.setId(o.getId());
	    		edit.setName(o.getName());
	    		edit.setDomain(o.getDomain());
	    		edit.setAliases(o.getAliases());
	    		}
    	}
    	return "editOrga1";
    }
     @GetMapping("new")
     public String addOrga() {
    	 return "addOrga";
     }
     
     
    @ModelAttribute("orgas")
    public List<Organization> getList(){
    	return repo.findAll();
    }
    @ModelAttribute("edit")
    public Organization getEdit() {
    	return new Organization();
    }
    
    
    
}