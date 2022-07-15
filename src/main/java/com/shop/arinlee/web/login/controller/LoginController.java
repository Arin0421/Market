package com.shop.arinlee.web.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginMember(){
        return "login/loginform";
    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("longinErrorMsg","아이디 또는 비밀번호를 확인해주세요.");
        return "/login/loginform";
    }
}
