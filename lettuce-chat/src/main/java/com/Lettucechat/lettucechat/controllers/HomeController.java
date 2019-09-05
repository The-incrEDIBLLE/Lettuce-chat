package com.Lettucechat.lettucechat.controllers;

import com.Lettucechat.lettucechat.models.ApplicationUser;
import com.Lettucechat.lettucechat.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class HomeController {

  @Autowired
  ApplicationUserRepository applicationUserRepository;

  // TODO: when logged in, go to match page
  @GetMapping("/")
  public String getRoot(Principal p, Model m){
    ApplicationUser applicationUser = null;
    if(p != null){
      applicationUser = applicationUserRepository.findByUsername(p.getName());
      m.addAttribute("viewedUser", applicationUser);
    }
    m.addAttribute("user", p);
    return "home";
  }

  @GetMapping("/signup")
  public String getSignupPage(){
    return "signup";
  }

  @GetMapping("/login")
  public RedirectView getLoginPage(){
    return new RedirectView("/");
  }

}
