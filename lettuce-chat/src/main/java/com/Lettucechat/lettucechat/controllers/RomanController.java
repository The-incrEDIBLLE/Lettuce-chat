//package com.Lettucechat.lettucechat.controllers;
//
//import com.Lettucechat.lettucechat.models.ApplicationUser;
//import com.Lettucechat.lettucechat.models.ApplicationUserRepository;
//import com.Lettucechat.lettucechat.models.ChatRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.security.Principal;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Controller
//public class RomanController {
//
//    @Autowired
//    ApplicationUserRepository applicationUserRepository;
//
//    @Autowired
//    ChatRepository chatRepository;
//    @GetMapping("/users")
//    public String getAllUsers(Model m, Principal p){
//
//        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
//        List<ApplicationUser> allUsers = applicationUserRepository.findAll();
//        List<ApplicationUser> matchedUsers = new ArrayList<>();
//        for (ApplicationUser user: allUsers){
//            if (user.getDietaryRestriction().equals(loggedInUser.getDietaryRestriction())) {
//                if (loggedInUser != user) {
//                    matchedUsers.add(user);
//                }
//                if (user.getDietaryRestriction() != loggedInUser.getDietaryRestriction()) {
//
//                 return "nomatching";
//
//    }
//            }
//        }
//
//        int numberOfMatches = matchedUsers.size();
//        m.addAttribute("matchedUser", matchedUsers.get((int)(Math.random() * numberOfMatches)));
//        return "randomUser";
//
//    }
//
//}
////