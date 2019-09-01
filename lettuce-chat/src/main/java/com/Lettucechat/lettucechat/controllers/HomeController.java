package com.Lettucechat.lettucechat.controllers;

import com.Lettucechat.lettucechat.models.ApplicationUser;
import com.Lettucechat.lettucechat.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

  @Autowired
  ApplicationUserRepository applicationUserRepository;

  // TODO: fix this
  @GetMapping("/")
  public String getRoot(Principal p, Model m){
    ApplicationUser applicationUser = null;
    if(p != null){
      applicationUser = applicationUserRepository.findByUsername(p.getName());
//      m.addAttribute("viewedUser", applicationUser);
//      return "profile";
    }
    m.addAttribute("user", applicationUser);
    return "home";
  }

  @GetMapping("/signup")
  public String getSignupPage(){
    return "signup";
  }

  @GetMapping("/login")
  public String getLoginPage(){
    return "login";
  }

}
