package com.Lettucechat.lettucechat.controllers;

import com.Lettucechat.lettucechat.models.ApplicationUser;
import com.Lettucechat.lettucechat.models.ApplicationUserRepository;
import com.Lettucechat.lettucechat.models.Chat;
import com.Lettucechat.lettucechat.models.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    ChatRepository chatRepository;

//    @PostMapping("/chat/create")
//    public RedirectView createPost(String body, Principal p){
//        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
//        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
//        Chat newChat = new Chat(currentUser.getId(), body);
//        chatRepository.save(newChat);
//        return new RedirectView("/users/" + currentUser.getId());
//    }

    @GetMapping("/users")
    public String getAllUsers(Model m, Principal p){

        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        List<ApplicationUser> allUsers = applicationUserRepository.findAll();
        List<ApplicationUser> matchedUsers = new ArrayList<>();
        for (ApplicationUser user: allUsers){
            if (user.getDietaryRestriction().equals(loggedInUser.getDietaryRestriction())) {
                if (loggedInUser != user) {
                    matchedUsers.add(user);
                }
            }
        }

        int numberOfMatches = matchedUsers.size();
        m.addAttribute("matchedUser", matchedUsers.get((int)(Math.random() * numberOfMatches)));
        return "randomUser";

    }
    @GetMapping("/chat/create")
    public String getChat(){
        return "chatbox";
    }
}
