package com.Lettucechat.lettucechat.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;
  long creator_id;
  long chat_id;
  String body;
  String timestamp;

}
