package com.wolf.springboot;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.wolf.springboot.web.PublicController;
import com.wolf.springboot.web.sys.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	@Test
	public void contextLoads() {
	}

	private MockMvc mvc;

	private MockMvc userMvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new PublicController()).build();
		userMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
	}

	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("Hello World")));
	}

	@Test
	public void testUserController() throws Exception {
		RequestBuilder request = null;
		// 1、get查一下user列表，应该为空
		request = MockMvcRequestBuilders.get("/users/");
		userMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[]")));

		// 2、post提交一个user
		request = MockMvcRequestBuilders.post("/users/").param("id", "1").param("name", "测试大师").param("age", "20");
		userMvc.perform(request).andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("success")));

		// 3、get获取user列表，应该有刚才插入的数据
		request = MockMvcRequestBuilders.get("/users/");
		userMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
				MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"id\":1,\"name\":\"测试大师\",\"age\":20}]")));

		// 4、put修改id为1的user
		request = MockMvcRequestBuilders.put("/users/1").param("name", "测试终极大师").param("age", "30");
		userMvc.perform(request).andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("success")));

		// 5、get一个id为1的user
		request = MockMvcRequestBuilders.get("/users/1");
		userMvc.perform(request).andExpect(
				MockMvcResultMatchers.content().string(Matchers.equalTo("{\"id\":1,\"name\":\"测试终极大师\",\"age\":30}")));

		// 6、del删除id为1的user
		request = MockMvcRequestBuilders.delete("/users/1");
		userMvc.perform(request).andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("success")));

		// 7、get查一下user列表，应该为空
		request = MockMvcRequestBuilders.get("/users/");
		userMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[]")));
	}

}
