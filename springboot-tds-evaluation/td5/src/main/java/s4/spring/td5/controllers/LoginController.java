package s4.spring.td5.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import s4.spring.td5.entities.User;
import s4.spring.td5.entities.UserRepository;

@Controller
public class LoginController{

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/index")
    public String logIn(){

        initAdmin();
        return "index";
    }

    @GetMapping("signup")
    public String signUp(){

        return "/signup";

    }

    @PostMapping("/signup")
    public  RedirectView signup(@RequestParam String identity,@RequestParam String login,@RequestParam String password,@RequestParam String email){

        String view="";
        //test if all informations are well filled
        if(identity.equals("")||login.equals("")||password.equals("")||email.equals("")){
            view="signup";
        }
        else{
            this.userRepo.save(new User(login, password, email, identity));
            view="index";
        }
            
        return new RedirectView(view);
    } 

    @PostMapping("/login")
    public RedirectView logIn(@RequestParam String login,@RequestParam String password,HttpSession session){
    
        String view = "";
        User user = this.userRepo.findByLogin(login);

        if(user!=null){
            if(user.getPassword().equals(password)){
                session.setAttribute("user", user);
                System.out.println(session.getAttribute("user"));
                view="/scripts/index";
            }
            else{
                view="/index";
            }
        }
        else{
            view="/index";
        }

        return new RedirectView(view);
    }

    @GetMapping("scripts/logout")
    public RedirectView logout(HttpSession session){
        session.removeAttribute("user");
        return new RedirectView("/index");
    }

    private void initAdmin() {
        if(userRepo.findAll().size()==0){
            User admin = new User("admin","admin","admin@admin.fr","Admin");
            this.userRepo.save(admin);
        }
    }
    

}