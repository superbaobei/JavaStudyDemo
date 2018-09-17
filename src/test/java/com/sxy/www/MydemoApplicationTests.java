package com.sxy.www;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MydemoApplicationTests {

	@Test
	public void contextLoads() {
		String input = "nenew";
		System.out.print("32bit result:" + DigestUtils.md5Hex(input) + "\n");
		System.out.print("16bit result:"
				+ DigestUtils.md5Hex(input).substring(8, 24) + "\n");
	}

}
