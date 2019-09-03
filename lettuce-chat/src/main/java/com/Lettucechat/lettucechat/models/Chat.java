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

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "chat_id")
  List<Message> messages;

  public Chat(){}
  public Chat(String subject){
    this.subject = subject;
    this.messages = new ArrayList<>();
  }

  public long getId() {
    return id;
  }

  public String getSubject() {
    return subject;
  }

  public List<Message> getMessages() {
    return messages;
  }

  // TODO: fn to add messages to the chat
}
