package com.Lettucechat.lettucechat.controllers;

import com.Lettucechat.lettucechat.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
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

  //Add chat. The new chat is added to both the participants in the chat
  // I'd prefer this to just be /chats, and take the id of the user as a variable in the body, not in the path.
  // It's more semantically RESTful that way; the fact that it's a create is handles by the fact that it's a post request.
  @PostMapping("/chat/create/{id}")
  public RedirectView createChat(@PathVariable long id, Principal p){
    ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
    ApplicationUser messageToUser = applicationUserRepository.findById(id).get();
    String subject =
        currentUser.getId() + ": " + currentUser.getUsername().toUpperCase() + " & " + messageToUser.getId() + ": " + messageToUser.getUsername().toUpperCase() +
        " get lunch!";
    Chat newChat = new Chat(subject);
    chatRepository.save(newChat);
    // why does following exist? is it used for anything in your application?
    currentUser.addFollowing(messageToUser);
    currentUser.addChat(newChat);
    applicationUserRepository.save(currentUser);

    messageToUser.addFollowing(currentUser);
    messageToUser.addChat(newChat);
    applicationUserRepository.save(messageToUser);

    return new RedirectView("/chat/" + newChat.getId());
  }

  //Just add messages to existing chat. Do not create a new chat
  @PostMapping("/chat/{id}")
  public RedirectView addMessage(@PathVariable long id, String body, Principal p){
    // This doesn't have any guard against me posting to a chat I don't belong to!
    // The code needs to check that the principal is one of the people involved in the chat,
    // or else this will let me post to arbitrary chats.
    ApplicationUser creator = applicationUserRepository.findByUsername(p.getName());
    Chat ch = chatRepository.findById(id).get();
    Message newMessage = new Message(creator, body, ch);
    messageRepository.save(newMessage);
    ch.addMessage(newMessage);
    chatRepository.save(ch);
    return new RedirectView("/chat/" + id);
  }

  //Get random match based on dietary restriction of the logged in user. Return a no match page if there are no matches
  @GetMapping("/users")
  public String getRandomUser(Model m, Principal p){
    ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
    List<ApplicationUser> allUsers = applicationUserRepository.findAll();
    List<ApplicationUser> matchedUsers = new ArrayList<>();
    Set<ApplicationUser> followedUsers = loggedInUser.getFollowedUsers();
    // instead of using followedUsers here, you could use their list of chats; right now you're saving the same data twice,
    // both in the chats table and in the follows table.
    for (ApplicationUser user: allUsers){
      if (user.getDietaryRestriction().equals(loggedInUser.getDietaryRestriction()) && !followedUsers.contains(user)) {
        if (loggedInUser != user) {
          matchedUsers.add(user);
        }
      }
    }
    if (matchedUsers.isEmpty()){
      m.addAttribute("user", p);
      return "nomatch";
    }

    int numberOfMatches = matchedUsers.size();
    m.addAttribute("matchedUser", matchedUsers.get((int)(Math.random() * numberOfMatches)));
    m.addAttribute("user", p);
    return "randomUser";

  }

  //Get messages for chats to display in chat history page
  @GetMapping("/chat/{id}")
  public String getChat(@PathVariable long id, Principal p, Model m) {
    // This will let me get a chat that isn't mine if I guess its ID!
    Chat ch = chatRepository.findById(id).get();
    m.addAttribute("chat", ch);
    m.addAttribute("messages", ch.getMessages());
    m.addAttribute("user", p);
    return "chatbox";
  }

}