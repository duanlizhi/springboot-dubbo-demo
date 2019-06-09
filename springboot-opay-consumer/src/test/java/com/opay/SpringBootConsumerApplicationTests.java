package com.opay;

import com.alibaba.dubbo.config.annotation.Reference;
import com.opay.constant.TransferChannelEnum;
import com.opay.constant.TransferTypeEnum;
import com.opay.entity.AccountDo;
import com.opay.entity.BankCardDo;
import com.opay.entity.TransactionRecordDo;
import com.opay.entity.TransferDTO;
import com.opay.exception.CustomerException;
import com.opay.service.AccountService;
import com.opay.service.BankCardService;
import com.opay.service.TransactionRecordService;
import com.opay.service.TransferService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootConsumerApplicationTests {

//	@Reference(url = "dubbo://127.0.0.1:20880")
	@Reference
	private AccountService accountService;
	@Reference
	private BankCardService bankCardService;
	@Reference
	private TransactionRecordService transactionRecordService;
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
		CustomerException save = accountService.save(account);
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
		CustomerException update = accountService.update(account);
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
		CustomerException save = bankCardService.saveBindCard(bankCard);
		System.out.println(save);
	}

	/**
	 * 充值
	 */
	@Test
	public void testChargeTransfer() {
		TransferDTO transferDTO = new TransferDTO();
		transferDTO.setFromAccountId(1L);
		transferDTO.setAmount(new BigDecimal(100));
		transferDTO.setChannel(TransferChannelEnum.BANKCARD_CHANNEL.getValue());
		transferDTO.setType(TransferTypeEnum.DEPOSIT.getNum());
		transferDTO.setOrderNo("3453454354");
		transferDTO.setBankCardId(1L);
		CustomerException customerException = bankCardTransfer.transfer(transferDTO);
		System.out.println(customerException.toString());
	}

	/**
	 * 测试使用余额转账
	 */
	@Test
	public void testBalanceTransfer() {
		TransferDTO transferDTO = new TransferDTO();
		transferDTO.setFromAccountId(1L);
		transferDTO.setToAccountId(2L);
		transferDTO.setAmount(new BigDecimal(99));
		transferDTO.setChannel(TransferChannelEnum.BALANCE_CHANNEL.getValue());
		transferDTO.setType(TransferTypeEnum.TRANSFER.getNum());
		transferDTO.setOrderNo("11111");
		CustomerException customerException = balanceTransfer.transfer(transferDTO);
		System.out.println(customerException);
	}

	/**
	 * 查询账户余额
	 */
	@Test
	public void testQueryAccountBalanceAndTransferRecord() {
		//查询id为1的账户余额
		long accountId = 1L;
		AccountDo accountByIdOrIdCard = accountService.getAccountByIdOrIdCard(accountId, null);
		System.out.println("账户余额为：" + accountByIdOrIdCard.getBalance());
		TransactionRecordDo transactionRecord = new TransactionRecordDo();
		transactionRecord.setFromAccountId(accountId);
		transactionRecord.setType(TransferTypeEnum.TRANSFER.getNum());
		List<TransactionRecordDo> transactionRecordDos = transactionRecordService.listTransactionRecord(transactionRecord);
		if(null != transactionRecordDos) {
			for (TransactionRecordDo transactionRecordDo : transactionRecordDos) {
				System.out.println(transactionRecordDo);
			}
		}
	}

}
