package com.example.pathfind.web;

import com.example.pathfind.model.binding.UserRegisterBindingModel;
import com.example.pathfind.service.impl.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("userRegistrationDto", new UserRegisterBindingModel());
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterBindingModel userRegistrationDto,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegistrationDTO",userRegistrationDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto",bindingResult);

            return "redirect:/register";
        }
        this.authService.register(userRegistrationDto);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @ModelAttribute
    public UserRegisterBindingModel initForm(Model model){
       return new UserRegisterBindingModel();
    }


}
