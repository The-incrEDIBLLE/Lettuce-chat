package com.Lettucechat.lettucechat.controllers;

import com.Lettucechat.lettucechat.models.ApplicationUser;
import com.Lettucechat.lettucechat.models.ApplicationUserRepository;
import com.Lettucechat.lettucechat.models.Chat;
import com.Lettucechat.lettucechat.models.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Timestamp;

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
}
