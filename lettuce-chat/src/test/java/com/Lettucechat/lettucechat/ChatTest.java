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

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ChatTest {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    ChatRepository chatRepository;

    @Test
    public void testChat_constructor() {
        Chat newChat = new Chat();
        assertTrue(newChat instanceof Chat);

        Chat anotherChat = new Chat("test subject");
        assertTrue(anotherChat instanceof Chat);
        assertTrue(anotherChat.getMessages().isEmpty());
    }

    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
    public void testChat_settersandgetters() {
        Chat newChat = new Chat();
        ReflectionTestUtils.setField(newChat, "subject", "test subject" );
        assertTrue(newChat.getSubject().equals("test subject"));

       assertEquals(newChat.getId(), 0);
    }

    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
    public void testChat_addMessage() {

        Message newMessage = new Message();
        Chat newChat = new Chat();
        newChat.setMessages(new ArrayList<>());

        newChat.addMessage(newMessage);

        assertTrue(newChat.getMessages().contains(newMessage));

    }

    @Test
    public void testChat_addParticipant() {
        Chat newChat = new Chat();
        ApplicationUser newUser = new ApplicationUser("testuser24", "password", "test11", "user1", "url1", "bio1", "vegetarian");

        newChat.setParticipants(new HashSet<>());
        newChat.addParticipant(newUser);
        //ReflectionTestUtils.setField(newChat, "subject", "test subject" );
        assertTrue(newChat.getParticipants().contains(newUser));
    }

}
