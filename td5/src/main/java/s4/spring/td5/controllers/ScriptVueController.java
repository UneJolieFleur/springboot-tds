package s4.spring.td5.controllers;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;



/**
 * ScriptVueController
 */
@Controller
public class ScriptVueController {

    @Autowired
    private VueJS vue;

    @ModelAttribute("vue")
	public VueJS getVue() {
		return new VueJS("#app");
    }
    
    @GetMapping("/search")
    public String searchView(ModelMap model) {
        vue.addData("id");
        vue.addData("s");

        vue.addDataRaw("scripts", "[]");
        vue.addDataRaw("headers", "[{text:'Titre', value:'title'}, {text:'Description', value:'description'},{text:'Categorie', value:'category.name'}, {text:'Langage', value: 'language.name'}]");
        vue.addDataRaw("choices", "[{text:'Titre', value:'1'}, {text:'Description', value:'2'},{text:'Contenu', value:'3'}, {text:'Categorie', value: '4'},  {text:'Langage', value: '5'}]");
        vue.addDataRaw("selected", "[]");
        vue.addWatcher("selected", "this.headers = [];for(let i = 0; i < this.selected.length; i++){let tab=this.selected[i].split(';'); this.headers.push(JSON.parse('{\"text\":\"'+tab[0]+'\", \"value\":\"'+tab[1]+'\"}'))}");
        vue.addWatcher("s","this.search();" );
        vue.addWatcher("id","this.search();" );
        vue.addMethod("search", "let self=this;"+Http.post("http://localhost:8082/rest/script",(Object)"{'id': this.id, 's': this.s}", "self.scripts = response.data;"));
        vue.addMethod("test", "console.log(this.search())");
        vue.addMethod("searchByCategory", "this.s=name; this.id='4'", "name");
        vue.addMethod("searchByLanguage", "this.s=name; this.id='5'", "name");

        model.put("vue",vue);

        return "search";
    }
}