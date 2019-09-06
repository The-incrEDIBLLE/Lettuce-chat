package com.Lettucechat.lettucechat.controllers;

import com.Lettucechat.lettucechat.models.ApplicationUser;
import com.Lettucechat.lettucechat.models.ApplicationUserRepository;
import com.Lettucechat.lettucechat.models.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Set;

@Controller
public class ApplicationUserController {

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  ApplicationUserRepository applicationUserRepository;
  private ApplicationUser service;

  //Only add new users. Existing user name should return an alert when signing up with the same user name again
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

  //Profile page - signup takes users directly to profile page
  @GetMapping("/profile")
  public String getProfile(Principal p, Model m){
    ApplicationUser applicationUser = null;
    if(p != null){
      applicationUser = applicationUserRepository.findByUsername(p.getName());
    }
    m.addAttribute("viewedUser", applicationUser);
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

  //Update profile
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

  //Get mapping for profile information
  @GetMapping("/profile/edit")
  public String getEditProfileForm(Principal p, Model m){
    ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
    m.addAttribute("viewedUser", applicationUser);
    m.addAttribute("user", p);
    return "editProfile";
  }

  //Get chat history for logged in user. Page displays subject and the other party in the chat
  @GetMapping("/mychats")
  public String getUsersChats(Model m, Principal p){
    ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
    Set<Chat> chats = loggedInUser.getChats();

    m.addAttribute("chats", chats);
    m.addAttribute("user", p);
    return "allUserChats";
  }

  //Delete profile
  @DeleteMapping("/profile")
  public RedirectView deleteUser(long viewedUserId) {
    applicationUserRepository.deleteById(viewedUserId);
    return new RedirectView("/logout");
  }

}
