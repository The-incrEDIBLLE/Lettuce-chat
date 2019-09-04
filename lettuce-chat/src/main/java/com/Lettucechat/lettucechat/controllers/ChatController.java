package com.Lettucechat.lettucechat.controllers;

import com.Lettucechat.lettucechat.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.awt.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class ChatController {

  @Autowired
  ApplicationUserRepository applicationUserRepository;

  @Autowired
  ChatRepository chatRepository;

  @Autowired
  MessageRepository messageRepository;

  @PostMapping("/chat/create/{id}")
  public RedirectView createChat(@PathVariable long id, String subject, Principal p){
    ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
    ApplicationUser messageToUser = applicationUserRepository.findById(id).get();
    Chat newChat = new Chat(subject);
    chatRepository.save(newChat);
    System.out.println("--------------------------" + newChat.getId());
    currentUser.addFollowing(messageToUser);
    currentUser.addChat(newChat);
    applicationUserRepository.save(currentUser);

    messageToUser.addFollowing(currentUser);
    messageToUser.addChat(newChat);
    applicationUserRepository.save(messageToUser);

    return new RedirectView("/chat/" + newChat.getId());
  }

  @PostMapping("/chat/{id}")
  public RedirectView addMessage(@PathVariable long id, String body, Principal p){
    ApplicationUser creator = applicationUserRepository.findByUsername(p.getName());
    Chat ch = chatRepository.findById(id).get();
    Message newMessage = new Message(creator.getId(), body, ch);
    messageRepository.save(newMessage);
    ch.addMessage(newMessage);
    chatRepository.save(ch);
    return new RedirectView("/chat/" + id);
  }

  @GetMapping("/users")
  public String getRandomUser(Model m, Principal p){
    ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
    List<ApplicationUser> allUsers = applicationUserRepository.findAll();
    List<ApplicationUser> matchedUsers = new ArrayList<>();
    Set<ApplicationUser> followedUsers = loggedInUser.getFollowedUsers();
    for (ApplicationUser user: allUsers){
      if (user.getDietaryRestriction().equals(loggedInUser.getDietaryRestriction()) && !followedUsers.contains(user)) {
        if (loggedInUser != user) {
          matchedUsers.add(user);
        }
      }
    }

    int numberOfMatches = matchedUsers.size();
    m.addAttribute("matchedUser", matchedUsers.get((int)(Math.random() * numberOfMatches)));
    return "randomUser";

  }

  @GetMapping("/chat/{id}") // Changed from "/chat/create/{id}"
  public String getChat(@PathVariable long id, Principal p, Model m) {
    Chat ch = chatRepository.findById(id).get();
    m.addAttribute("chat", ch);
    m.addAttribute("messages", ch.getMessages());
    m.addAttribute("participants", ch.getParticipants());

//    ApplicationUser messageToUser = applicationUserRepository.findById(id).get();
//    ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
//
//    Set<Chat> chats = loggedInUser.getChats();
//    List<Message> messages = new ArrayList<>();
//
//    for (Chat chat : chats) {
//      if (chat.getParticipants().contains(messageToUser)) {
//        messages.addAll(chat.getMessages());
//        m.addAttribute("chat", chat.getId());
//      }
//    }
//    m.addAttribute("messageToUser", messageToUser);
//    m.addAttribute("user", p);
//    if (messages.size() != 0) {
//      m.addAttribute("messages", messages);
//    }
    return "chatbox";

  }

}