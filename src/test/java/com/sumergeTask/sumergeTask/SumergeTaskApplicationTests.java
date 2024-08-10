package com.sumergeTask.sumergeTask;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SumergeTaskApplicationTests {

	@Test
	void contextLoads() {
	}

}
