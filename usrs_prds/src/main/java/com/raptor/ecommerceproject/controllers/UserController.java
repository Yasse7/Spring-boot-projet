package com.raptor.ecommerceproject.controllers;

import com.raptor.ecommerceproject.models.Order;
import com.raptor.ecommerceproject.models.User;
import com.raptor.ecommerceproject.services.IOrderService;
import com.raptor.ecommerceproject.services.IUserService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService orderService;


    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


    @GetMapping("/register")
    public String createView(){
        return"User/register";
    }

    @PostMapping("/save")
    public String saveUser(User user){
        user.setTypeUser("USER");
        logger.info("Registered user data: {}",user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);

        return "redirect:/";
    }
    @GetMapping("/login")
    public String viewLogin(){
        return "User/login";
    }

    @GetMapping("/access")
    public String access(User user, HttpSession session){
        logger.info("users: {}",user);
        Optional<User> userOptional=userService.findById(Long.parseLong(session.getAttribute("idUser").toString()));
        if(userOptional.isPresent()){
                session.setAttribute("idUser",userOptional.get().getId());
                if(userOptional.get().getTypeUser().equals("ADMIN")){
                    return "redirect:/manager";
                }else{
                    return "redirect:/";
                }
        }else{
            logger.info("user don't exist");
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("idUser");
        return "redirect:/";
    }









}
