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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LettuceChatApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ApplicationUserRepository applicationUserRepository;

	@Autowired
	ChatRepository chatRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRootRoute_contains() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(
						org.hamcrest.Matchers.containsString("<h1>Lettuce Chat</h1>")));
	}

	@Test
	public void testSignupRoute_containsForm() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/signup"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(
						org.hamcrest.Matchers.containsString("<form method=\"POST\" action=\"/users\">")));
	}


}

