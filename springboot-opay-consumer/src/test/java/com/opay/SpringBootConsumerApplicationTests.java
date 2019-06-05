package com.opay;

import com.alibaba.dubbo.config.annotation.Reference;
import com.opay.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootConsumerApplicationTests {

	@Reference(url = "dubbo://127.0.0.1:20880")
	private AccountService accountService;


	@Test
	public void contextLoads() {
		accountService.saveAccount("hah");
		System.out.println(1111111);
	}

}
