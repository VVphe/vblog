package com.vv.blog.vblog;

import com.vv.blog.vblog.service.Impl.JedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VblogApplicationTests {

	@Autowired
	private JedisService jedisService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getCategoryValueTest() {
		System.out.println(jedisService.getCategoryValue("categoryCount","Java"));
	}

}
