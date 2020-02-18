package S4.spring.td1.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import S4.spring.td1.models.Element;

@Controller
@SessionAttributes("items")
public class MainController {
	private List<Element> listItems;
	@ModelAttribute("items") 
    public List<Element> getItems(){
        return new ArrayList<>();
    }
	
	@GetMapping("/items")
	public String listItems(ModelMap model) {
		model.put("listItems", getItems());
		return "ListItems";
	}
	
	@GetMapping("/items/new")
	public String newItem() {
		return "AddItems";
	}
	
	 @PostMapping("/items/addNew")
	 public RedirectView addNew(@RequestParam String nom,@SessionAttribute List<Element> items) {
		 items.add(new Element(nom, 0));
	     return new RedirectView("/items/");
	 }
	 
	 
	 
}
