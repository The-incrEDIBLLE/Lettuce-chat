package com.Lettucechat.lettucechat;

import com.Lettucechat.lettucechat.models.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MessageTest {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    MessageRepository messageRepository;

    @Test
    public void testMessage_constructor() {
        Message newMessage = new Message();
        assertTrue(newMessage instanceof Message);

        Chat newChat = new Chat();

        Message anotherMessage = new Message(1, "test message", newChat);
        assertTrue(anotherMessage instanceof Message);
        assertEquals(anotherMessage.getSender_id(), 1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        String createdAt = sdf.format(new Timestamp(System.currentTimeMillis()).getTime());

        assertEquals(anotherMessage.getCreatedAt(), createdAt);
    }

    @Test
    public void testMessage_settersandgetters() {
        Chat newChat = new Chat();
        Message anotherMessage = new Message(1, "test message", newChat);

        assertTrue(anotherMessage.getBody().equals("test message"));
        assertEquals(anotherMessage.getId(), 0);
        assertEquals(anotherMessage.getSender_id(), 1);
        assertEquals(anotherMessage.getChat(), newChat);
    }

}
