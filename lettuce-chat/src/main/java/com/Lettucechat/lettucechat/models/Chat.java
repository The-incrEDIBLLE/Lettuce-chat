package com.Lettucechat.lettucechat.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Chat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;
  String subject;
  @ManyToMany(mappedBy = "chats")
  Set<ApplicationUser> participants;

  //List of messages in a chat
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "chat", cascade = CascadeType.ALL)
  List<Message> messages;

  //Helper method to add participant to chat
  public void addParticipant(ApplicationUser participant){
    participants.add(participant);
  }

  //Constructors
  public Chat(){}

  public Chat(String subject){
    this.subject = subject;
    this.messages = new ArrayList<>();
  }

  //Getters and setters
  public Set<ApplicationUser> getParticipants() {
    return this.participants;
  }

  public long getId() {
    return id;
  }

  public String getSubject() {
    return this.subject;
  }

  public List<Message> getMessages() {
    return this.messages;
  }

  //Helper method to add messages
  public void addMessage(Message msg){
    messages.add(msg);
  }

  public void setMessages(List<Message> messages) {
    this.messages = messages;
  }

  public void setParticipants(Set<ApplicationUser> participants) {
    this.participants = participants;
  }
  public void setSubject(String subject) {
    this.subject = subject;
  }

}
