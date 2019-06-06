package com.opay;

import com.alibaba.dubbo.config.annotation.Reference;
import com.opay.entity.AccountDo;
import com.opay.entity.BankCardDo;
import com.opay.service.AccountService;
import com.opay.service.BankCardService;
import com.opay.service.TransferService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootConsumerApplicationTests {

//	@Reference(url = "dubbo://127.0.0.1:20880")
	@Reference
	private AccountService accountService;
	@Reference
	private BankCardService bankCardService;
	/**
	 * 银行卡转账实现类
	 */
	@Reference(group = "bankCardTransfer")
	private TransferService bankCardTransfer;
	/**
	 * 余额转账接口实现类
	 */
	@Reference(group = "balanceTransfer")
	private TransferService balanceTransfer;


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
		account.setIdCard("1301021986030759544545");
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
		account.setId(1L);
//		account.setName("zhangsan");
		account.setIdCard("110101199003078435");
//		account.setMobileNumber("15677889900");
		boolean update = accountService.update(account);
		System.out.println(update);
	}

	/**
	 * 测试绑定银行卡
	 */
	@Test
	public void testBindBankCard() {
		BankCardDo bankCard = new BankCardDo();
		bankCard.setCardNumber("6222021206521025212");
		bankCard.setAccountId(1L);
		bankCard.setBankName("招商银行");
		bankCard.setMobileNumber("13789765678");
		Boolean aBoolean = bankCardService.saveBindCard(bankCard);
		System.out.println(aBoolean);
	}

}
