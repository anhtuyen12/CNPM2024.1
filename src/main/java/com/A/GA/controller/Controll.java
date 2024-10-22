package com.A.GA.controller;


import com.A.GA.Model.Login;
import com.A.GA.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class Controll {
    @Autowired
    LoginService loginService;

//   đây là trang view của user
    @GetMapping("/homeUser")
    public String homeUser(Model model) {
        return "homeUser";
    }
// đây là view của trang ADMIN
    @GetMapping("/homeAdmin")
    public String homeAdmin( Model model){
        return "homeAdmin";
    }

//    đây là trang đăng nhập lỗi
    @GetMapping("/loginError")
    public String loginError(Model model){
        return "loginError";
    }
    @GetMapping("/login/home")
    public String homeLogin(Model model) {
        return "Login";
    }

    // điều hướng của trang Login
    @PostMapping("/login/hffffome")
    public RedirectView login(@RequestParam("TKUser") String TKUser,
                              @RequestParam("PassWork") String passWork,
                              @RequestParam("role") String role) {
         Login checkLogin =  loginService.CheckLogin(TKUser, passWork, role);
         if (checkLogin != null){
             if (role.equals("1") && checkLogin.getRole().equals("1")) {
                 return new RedirectView("/homeUser");
             }
             if (role.equals("2") && checkLogin.getRole().equals("2")){
                 return new RedirectView("/homeAdmin");
             }
         }
        return new RedirectView("/loginError");
    }

}
