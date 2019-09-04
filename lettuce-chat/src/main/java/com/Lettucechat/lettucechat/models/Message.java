package com.Lettucechat.lettucechat.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;
  long sender_id;
  String body;
  String createdAt;
  @ManyToOne
  @JoinColumn(name="chat")
  Chat chat;

  public Message(){}
  public Message(long sender_id, String body, Chat chat){
    this.sender_id = sender_id;
    this.body = body;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    this.createdAt = sdf.format(new Timestamp(System.currentTimeMillis()).getTime());
    this.chat = chat;
  }


  public long getId() {
    return id;
  }

  public long getSender_id() {
    return sender_id;
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
