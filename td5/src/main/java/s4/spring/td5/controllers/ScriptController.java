
package s4.spring.td5.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import s4.spring.td5.entities.Category;
import s4.spring.td5.entities.CategoryRepository;
import s4.spring.td5.entities.History;
import s4.spring.td5.entities.HistoryRepository;
import s4.spring.td5.entities.Language;
import s4.spring.td5.entities.LanguageRepository;
import s4.spring.td5.entities.Script;
import s4.spring.td5.entities.ScriptRepository;
import s4.spring.td5.entities.User;
import s4.spring.td5.entities.UserRepository;

@Controller
public class ScriptController {

    @Autowired
    private ScriptRepository scriptRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private LanguageRepository languageRepo;

    @Autowired
    private HistoryRepository historyRepo;


    @GetMapping("/scripts/index")
    public String indexScripts(ModelMap model, HttpSession session){

        initScripts();
        User user = (User) session.getAttribute("user");

        if(user != null){

            user = this.userRepo.findById(user.getId());

            model.put("user", user);

            return "/scripts/index";

        }
        else{
            return "index";
        }

    }

    @GetMapping("scripts/new")
    public String newScript(ModelMap model, HttpSession session)
    {
        User user = (User)session.getAttribute("user");
        if(user!=null) {
            List<Language> lang = this.languageRepo.findAll();
            List<Category> cat = this.categoryRepo.findAll();
            model.put("languages", lang);
            model.put("categories", cat);
            return "scripts/new";
        }
        else
        {
            return "index";
        }
    }

    @PostMapping(value = {"/scripts/submit", "/scripts/submit/{id}"})
    public RedirectView add ( @PathVariable(required = false) String id,@RequestParam String title, @RequestParam String description, @RequestParam String content,
                            @RequestParam String creationDate,@RequestParam String language,@RequestParam String category, @RequestParam String comment,HttpSession session){

        Script script = id!=null?this.scriptRepo.findById(Integer.parseInt(id)):null;

        if(script==null){
            
            script = new Script(title,description,content,creationDate);
        }
        else{

            script.setTitle(title);
            script.setCreationDate(creationDate);
            script.setContent(content);
            script.setDescription(description);

        }

        Language language1 = this.languageRepo.findByName(language);

        Category category1 = this.categoryRepo.findByName(category.toString());

        User user = (User)session.getAttribute("user");

        user = this.userRepo.findById(user.getId());
        List<Script> scripts = user.getScripts();

        script = setHistory(script, comment, content);
        script.setUser(user);
        script.setCategory(category1);
        script.setLanguage(language1);

        scripts.add(script);
        user.setScripts(scripts);
        this.userRepo.save(user);

        return new RedirectView("/scripts/index");
    }

    @GetMapping("/scripts/{id}")
    public String edit(ModelMap model, @PathVariable String id,HttpSession session){

        User user = (User)session.getAttribute("user");

        if(user!=null) {

            Script script = this.scriptRepo.findById(Integer.parseInt(id));
            List<Language> languages = this.languageRepo.findAll();
            List<Category> categories = this.categoryRepo.findAll();
            model.put("languages", languages);
            model.put("categories", categories);
            model.put("script", script);
            return "scripts/edit";
        }
        else{

            return "index";

        }
    }

    private Script setHistory(Script script, String comment, String content) {
        if(script.getHistory()==null)
        {
            History history = new History(script.getCreationDate(),content,comment,script.getId());
            List<History> histories = new ArrayList<>();
            histories.add(history);
            script.setHistory(histories);

        }
        else
        {
            History history = new History(script.getCreationDate(),content,comment,script.getId());
            List<History> histories = script.getHistory();
            histories.add(history);
            script.setHistory(histories);
            history.setScripts(script);
            this.historyRepo.save(history);
        }
        return script;
    }

    private void initScripts() {

        Language language1 = new Language("Java");
        Language language2 = new Language("PHP");
        Language language3 = new Language("C");
        Language language4 = new Language("Python");

        Category category1 = new Category("config files");
        Category category2 = new Category("bash scripts");
        Category category3 = new Category("automatisation scripts");

        if(this.languageRepo.findAll().size()==0)
        {
            this.languageRepo.save(language1);
            this.languageRepo.save(language2);
            this.languageRepo.save(language3);
            this.languageRepo.save(language4);
        }
        if(this.categoryRepo.findAll().size()==0)
        {
            this.categoryRepo.save(category1);
            this.categoryRepo.save(category2);
            this.categoryRepo.save(category3);
        }
    }
}