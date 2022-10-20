package com;

import com.service.userServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroSpringbootApplicationTests {

	@Autowired
	userServiceImpl userService;


	@Test
	void contextLoads() {
		System.out.println(userService.queryUserByName("root"));
	}

}
