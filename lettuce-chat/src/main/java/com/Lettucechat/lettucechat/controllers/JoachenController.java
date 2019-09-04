package com.Lettucechat.lettucechat.controllers;

import com.Lettucechat.lettucechat.models.ApplicationUserRepository;
import com.Lettucechat.lettucechat.models.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JoachenController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    ChatRepository chatRepository;

//    @GetMapping("/mychats")
//    public String getMyChatsPage(){
//        return "allUserChats";
//    }

    @GetMapping("/team")
    public String getTeamPage(){
        return "team";
    }
}