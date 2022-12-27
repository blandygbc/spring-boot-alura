package com.blandygbc.forum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@TestPropertySource(locations = { "classpath:application-test.properties" })
class ForumApplicationTests {

	@Test
	void contextLoads() {
		Assertions.assertTrue(true);
	}

}
