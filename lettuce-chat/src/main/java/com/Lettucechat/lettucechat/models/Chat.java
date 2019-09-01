package com.Lettucechat.lettucechat.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Chat {

  // TODO: Many to many relationship with application user
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;
  long creator_id;
  long participant_id;
  String subject;
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "chat_id")
  List<Message> messages;

  public Chat(){}
  public Chat(long creator_id, long participant_id, String subject){
    this.creator_id = creator_id;
    this.participant_id = participant_id;
    this.subject = subject;
    this.messages = new ArrayList<>();
  }

  public long getId() {
    return id;
  }

  public long getCreator_id() {
    return creator_id;
  }

  public long getParticipant_id() {
    return participant_id;
  }

  public String getSubject() {
    return subject;
  }

  public List<Message> getMessages() {
    return messages;
  }

  // TODO: fn to add messages to the chat
}
