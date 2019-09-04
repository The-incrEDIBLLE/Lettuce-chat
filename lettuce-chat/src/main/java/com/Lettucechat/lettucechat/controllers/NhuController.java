package com.Lettucechat.lettucechat.controllers;

import com.Lettucechat.lettucechat.models.ApplicationUser;
import com.Lettucechat.lettucechat.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class NhuController {

  @Autowired
  ApplicationUserRepository applicationUserRepository;

  @Autowired
  PasswordEncoder encoder;

  @PutMapping("/profile/edit")
  public String updateUser(Model m, Principal p, long viewedUserId, String username, String firstName, String lastName,
                                 String password, String imgUrl, String bio, String dietaryRestriction){
    ApplicationUser applicationUser = applicationUserRepository.findById(viewedUserId).get();
    applicationUser.setUsername(username);
    applicationUser.setFirstName(firstName);
    applicationUser.setLastName(lastName);
    applicationUser.setPassword(encoder.encode(password));
    applicationUser.setImgUrl(imgUrl);
    applicationUser.setBio(bio);
    applicationUser.setDietaryRestriction(dietaryRestriction);
    applicationUserRepository.save(applicationUser);
    m.addAttribute("viewedUser", applicationUser);
    m.addAttribute("user", p);
    return "profile";
  }

  @GetMapping("/profile/edit")
  public String getEditProfileForm(Principal p, Model m){
    ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
    m.addAttribute("viewedUser", applicationUser);
    return "editProfile";
  }

//  @DeleteMapping("/profile")
//  public RedirectView deleteUser(long viewedUserId){
//
//    return new RedirectView("/logout");
//  }
}
