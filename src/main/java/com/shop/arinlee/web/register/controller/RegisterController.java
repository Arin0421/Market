package com.shop.arinlee.web.register.controller;

import com.shop.arinlee.global.error.exception.BusinessException;
import com.shop.arinlee.web.register.dto.RegisterFormDto;
import com.shop.arinlee.web.register.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @GetMapping(value="/register")
    public String register(Model model){
        model.addAttribute("registerFormDto",new RegisterFormDto());
        return "register/registerForm";
    }

    @PostMapping("/register")
    public String addMember(@Valid @ModelAttribute RegisterFormDto registerFormDto, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "register/registerForm";
        }
        try{
            registerService.registerMember(registerFormDto);
        }catch (BusinessException e){
            e.printStackTrace();
            bindingResult.reject("errorMessage",e.getMessage());
            return "register/registerForm";
        }

        return "redirect:/login";
    }
}
