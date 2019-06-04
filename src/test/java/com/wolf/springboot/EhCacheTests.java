package com.wolf.springboot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wolf.springboot.domain.sys.User;
import com.wolf.springboot.domain.sys.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EhCacheTests {

	@Autowired
	private UserRepository userRepository;

	@Before
	public void before() throws Exception {
		User data = new User();
		data.setAge(30);
		data.setName("wux");
	}

	@Test
	public void test() throws Exception {
		
		User u1 = userRepository.getOne(7L);
		System.out.println("第一次查询：" + u1.getAge());

		
	}

}
