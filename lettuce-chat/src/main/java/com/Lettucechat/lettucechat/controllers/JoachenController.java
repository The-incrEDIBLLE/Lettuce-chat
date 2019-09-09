package com.Lettucechat.lettucechat.controllers;

import com.Lettucechat.lettucechat.models.ApplicationUser;
import com.Lettucechat.lettucechat.models.ApplicationUserRepository;
import com.Lettucechat.lettucechat.models.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
// this is maybe not the best name for a controller
// I'd prefer calling this an AboutUsController, or just putting it in the HomeController.
public class JoachenController {

    // You don't need any of these things here!
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    ChatRepository chatRepository;

    @GetMapping("/team")
    public String getTeamPage(){
        return "team";
    }

}