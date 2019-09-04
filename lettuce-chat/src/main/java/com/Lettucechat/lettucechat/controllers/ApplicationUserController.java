package com.Lettucechat.lettucechat.controllers;

import com.Lettucechat.lettucechat.models.ApplicationUser;
import com.Lettucechat.lettucechat.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class ApplicationUserController {

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  ApplicationUserRepository applicationUserRepository;

  @PostMapping("/users")
  public RedirectView createUser(String username, String password, String firstName, String lastName, String imgUrl,
                                 String bio,
                                 String dietaryRestriction){
    ApplicationUser alreadyExists = applicationUserRepository.findByUsername(username);
    if(alreadyExists == null) {
      ApplicationUser newUser = new ApplicationUser(username, encoder.encode(password), firstName, lastName, imgUrl,
          bio, dietaryRestriction);
      applicationUserRepository.save(newUser);
      Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
      SecurityContextHolder.getContext().setAuthentication(authentication);
      return new RedirectView("/profile");
    } else {
      // TODO: send modal alert for username already exists
      return new RedirectView("/");
    }
  }

  @GetMapping("/profile")
  public String getProfile(Principal p, Model m){
    ApplicationUser applicationUser = null;
    if(p != null){
      applicationUser = applicationUserRepository.findByUsername(p.getName());
    }
    m.addAttribute("viewedUser", applicationUser);
//    System.out.println("--------------------------" + applicationUser.getId());
//    why did you show yourself and then not work??
    m.addAttribute("user", p);
    return "profile";
  }

  @GetMapping("/users/{id}")
  public String getOneUser(@PathVariable long id, Principal p, Model m){
    ApplicationUser applicationUser = applicationUserRepository.findById(id).get();
    m.addAttribute("viewedUser", applicationUser);
    m.addAttribute("user", p);
    return "profile";
  }

  @PutMapping("/profile/edit")
  public String updateUser(Model m, Principal p, long viewedUserId, String firstName, String lastName,
                           String imgUrl, String bio, String dietaryRestriction){
    ApplicationUser applicationUser = applicationUserRepository.findById(viewedUserId).get();
    applicationUser.setFirstName(firstName);
    applicationUser.setLastName(lastName);
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

  // TODO: delete user and their chats
//  @DeleteMapping("/profile")
//  public RedirectView deleteUser(long viewedUserId){
//
//    return new RedirectView("/logout");
//  }
}
