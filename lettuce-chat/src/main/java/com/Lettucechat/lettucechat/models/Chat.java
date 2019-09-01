package com.Lettucechat.lettucechat.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class Chat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;
  long participant1_id;
  long participant2_id;
  String subject;
  List<Message> message;
}
