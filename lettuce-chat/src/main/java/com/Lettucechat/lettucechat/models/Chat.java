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
  long creatorId;
  long receiverId;
  @ManyToMany(mappedBy = "chats")
  Set<ApplicationUser> participants;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "chat")
  List<Message> messages;

  public void addParticipant(ApplicationUser participant){
    participants.add(participant);
  }
  public Chat(){}
  public Chat(String subject){
    this.subject = subject;
    //this.creatorId = creatorId;
    //this.receiverId = receiverId;
    this.messages = new ArrayList<>();
  }

  public Set<ApplicationUser> getParticipants() {
    return participants;
  }

  public long getId() {
    return id;
  }

  public long getCreatorId() {
    return creatorId;
  }

  public long getReceiverId() {
    return receiverId;
  }

  public String getSubject() {
    return subject;
  }

  public List<Message> getMessages() {
    return messages;
  }

  // TODO: fn to add messages to the chat
}
