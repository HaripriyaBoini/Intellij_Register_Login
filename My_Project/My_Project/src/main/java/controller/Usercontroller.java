package controller;

import Service.UserService;
import model.User_Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Usercontroller {

    @Autowired
    private final UserService userservice;

    public Usercontroller(UserService userservice) {
        this.userservice = userservice;
    }


    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new User_Model());;
        return "register_page";
    }
    @GetMapping("/login")
    public String getLoginPage(Model model){

        model.addAttribute("loginRequest",new User_Model());
        return "login_page";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute User_Model usermodel){
        User_Model registeredUser = userservice.registerUser(usermodel.getLogin(), usermodel.getPassword(), usermodel.getEmail());
          return registeredUser==null?"error_page":"redirect:/login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute User_Model usermodel, Model model){
        User_Model authenticated = userservice.authenticate(usermodel.getLogin(), usermodel.getPassword());
         if(authenticated!=null){
             model.addAttribute("userLogin",authenticated.getLogin());
                     return "personal_page";
         }else{
             return "error_page";
         }
    }
}
