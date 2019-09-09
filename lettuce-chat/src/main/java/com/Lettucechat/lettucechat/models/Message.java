package com.Lettucechat.lettucechat.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;
  // why not the actual applicationuser?
  ApplicationUser sender;
  String body;
  String createdAt;

  @ManyToOne
  @JoinColumn(name="chat")
  Chat chat;

  //Constructor
  public Message(){}
  public Message(ApplicationUser sender, String body, Chat chat){
    this.sender = sender;
    this.body = body;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    this.createdAt = sdf.format(new Timestamp(System.currentTimeMillis()).getTime());
    this.chat = chat;
  }

//Getters and setters
  public long getId() {
    return id;
  }

  public ApplicationUser getSender() {
    return sender;
  }

  public String getBody() {
    return body;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public Chat getChat() {
    return chat;
  }

}
