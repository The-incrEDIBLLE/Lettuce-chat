package com.Lettucechat.lettucechat;

import com.Lettucechat.lettucechat.models.ApplicationUser;
import com.Lettucechat.lettucechat.models.ApplicationUserRepository;
import com.Lettucechat.lettucechat.models.Chat;
import com.Lettucechat.lettucechat.models.ChatRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationUserTest {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    ChatRepository chatRepository;

    //Happy path tests. These constructors, getters and setters are not called in edge cases
    @Test
    public void testApplicationUser_constructor() {
        ApplicationUser newUser = new ApplicationUser("testuser21", "password", "test1", "user", "url", "bio", "vegetarian");
        applicationUserRepository.save(newUser);

        ApplicationUser userFromDB = applicationUserRepository.findByUsername("testuser21");
        String firstNameFromDB = userFromDB.getFirstName();
        assertEquals("test1", firstNameFromDB);

    }

    @Test
    public void testApplicationUser_settersandgetters() {
        ApplicationUser newUser = new ApplicationUser();
        ReflectionTestUtils.setField(newUser, "username", "user1" );
        assertTrue(newUser.getUsername().equals("user1"));

        newUser.setBio("bio");
        newUser.setPassword("password");
        newUser.setImgUrl("url");
        newUser.setDietaryRestriction("gluten-free");
        newUser.setFirstName("user first name");
        newUser.setLastName("user last name");

        assertTrue(newUser.getBio().equals("bio"));
        assertTrue(newUser.getPassword().equals("password"));
        assertTrue(newUser.getFirstName().equals("user first name"));
        assertTrue(newUser.getLastName().equals("user last name"));
        assertTrue(newUser.getImgUrl().equals("url"));
        assertTrue(newUser.getDietaryRestriction().equals("gluten-free"));

    }

    @Test
    public void testAdd_Following() {
        ApplicationUser newUser1 = new ApplicationUser("testuser23", "password", "test11", "user1", "url1", "bio1", "vegetarian");
        ApplicationUser newUser2 = new ApplicationUser("testuser24", "password", "test12", "user2", "url2", "bio2", "vegetarian");

        // This piece shouldn't be necessary; the fact that you have to do this in your test is an indicator that you should set that in your constructor.
        // (And same for things like chats where you find yourself doing this same bit of logic.)
        newUser1.setFollowedUsers(new HashSet<>());
        newUser1.addFollowing(newUser2);
        assertTrue(newUser1.getFollowedUsers().contains(newUser2));
    }

    @Test
    public void testAdd_Chat() {
        ApplicationUser newUser1 = new ApplicationUser("testuser1", "password", "test1", "user1", "url1", "bio1", "vegetarian");

        Chat myChat = new Chat("subject");

        newUser1.setChats(new HashSet<>());
        newUser1.addChat(myChat);
        assertTrue(newUser1.getChats().contains(myChat));
    }
}
