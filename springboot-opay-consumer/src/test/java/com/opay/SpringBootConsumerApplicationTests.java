package com.opay;

import com.alibaba.dubbo.config.annotation.Reference;
import com.opay.entity.AccountDo;
import com.opay.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootConsumerApplicationTests {

//	@Reference(url = "dubbo://127.0.0.1:20880")
	@Reference
	private AccountService accountService;


	@Test
	public void contextLoads() {
		accountService.saveAccount("hah");
		System.out.println(1111111);
	}

	/**
	 * 测试账户的保存
	 */
	@Test
	public void testSaveAccount() {
		AccountDo account = new AccountDo();
		account.setIdCard("130102198603075954");
		account.setName("wangwu");
		account.setNickName("王五麻子");
		account.setBalance(new BigDecimal(100));
		boolean save = accountService.save(account);
		System.out.println(save);
	}

	/**
	 * 测试修改账户
	 */
	@Test
	public void testUpdateAccount() {
		AccountDo account = new AccountDo();
		account.setId(new BigInteger("1"));
//		account.setName("zhangsan");
		account.setIdCard("110101199003078435");
//		account.setMobileNumber("15677889900");
		boolean update = accountService.update(account);
		System.out.println(update);
	}

}
