package com.opay;

import com.alibaba.dubbo.config.annotation.Reference;
import com.opay.constant.ErrorEnum;
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
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootConsumerApplicationTests {

//	@Reference(url = "dubbo://127.0.0.1:20880")
	@Reference(mock = "true", check = false)
//	@Reference(mock = "return null")
	private AccountService accountService;
	@Reference(mock = "true", check = false)
	private BankCardService bankCardService;
	@Reference(mock = "true", check = false)
	private TransactionRecordService transactionRecordService;
	/**
	 * 银行卡转账实现类
	 */
	@Reference(group = "bankCardTransfer", mock = "true", check = false)
	private TransferService bankCardTransfer;
	/**
	 * 余额转账接口实现类
	 */
	@Reference(group = "balanceTransfer", mock = "true", check = false)
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
		account.setIdCard("13011021198603450759544545");
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
		account.setId(111L);
		account.setName("zhangsan");
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
		bankCard.setCardNumber("6222678021206521025212");
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
		transferDTO.setOrderNo(UUID.randomUUID().toString());
		transferDTO.setBankCardId(1L);
		CustomerException customerException = bankCardTransfer.transfer(transferDTO);
		System.out.println(customerException);
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
		transferDTO.setOrderNo(UUID.randomUUID().toString());
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
		long accountId2 = 2L;
		printAccountBalanceAndRecord(accountId);
		printAccountBalanceAndRecord(accountId2);
	}

	private void printAccountBalanceAndRecord(long accountId) {
		AccountDo accountByIdOrIdCard = accountService.getAccountByIdOrIdCard(accountId, null);
		System.out.println("账户id: " + accountId+ "余额为：" + accountByIdOrIdCard.getBalance());
		TransactionRecordDo transactionRecord = new TransactionRecordDo();
		transactionRecord.setFromAccountId(accountId);
		transactionRecord.setType(TransferTypeEnum.TRANSFER.getNum());
		List<TransactionRecordDo> transactionRecordDos = transactionRecordService.listTransactionRecord(transactionRecord);
		if(null != transactionRecordDos) {
			for (TransactionRecordDo transactionRecordDo : transactionRecordDos) {
				System.out.println(transactionRecordDo);
			}
		}
		System.out.println("-------------------------------------");
	}

	private AccountDo accountA = null;
	private AccountDo accountB = null;
	private BankCardDo bankCard = null;
	private boolean flag = true;

	/**
	 * 测试之前删除原有数据，创建好两个账户
	 */
	@Before
	public void before() {
		//删除所有的数据
		accountService.deleteAll();
		bankCardService.deleteAll();
		transactionRecordService.deleteAll();

		accountA = new AccountDo();
		accountA.setName("zhaoliu");
		accountA.setIdCard("110101198810161863");
		accountA.setNickName("赵六");
		accountA.setMobileNumber("15689876677");

		accountB = new AccountDo();
		accountB.setName("wangdashan");
		accountB.setIdCard("110101198810163148");
		accountB.setNickName("wangdashan");
		accountB.setMobileNumber("15689879900");

		bankCard = new BankCardDo();
		bankCard.setMobileNumber("15688000088");
		bankCard.setBankName("建设银行");
		bankCard.setCardNumber("6225021206521025546");
	}

	/**
	 * 流程跑完之后 查询a、b余额和转账记录
	 */
	@After
	public void after() {
		if(null != accountA && null != accountA.getId() &&
			null != accountB && null != accountB.getId()) {
			printAccountBalanceAndRecord(accountA.getId());
			printAccountBalanceAndRecord(accountB.getId());

		}
	}

	/**
	 * 测试新增账户新增银行卡，a充值，a给b转账
	 */
	@Test
	public void testCreateAccountAndTransfer() {
		//新增用户
		saveAccount(accountA);
		saveAccount(accountB);
		//给用户a绑定一张银行卡
		bankCard.setAccountId(accountA.getId());
		bindBankCard(bankCard);
		//用户a银行卡充值100元
		charge(accountA);
		//用户a使用余额给用户b转账99元
		transfer(accountA,accountB);
	}

	/**
	 * 保存用户信息
	 * @param account 新增用户信息
	 */
	private void saveAccount(AccountDo account) {
		if(flag) {
			CustomerException save = accountService.save(account);
			if(save.getCode() != ErrorEnum.SUCCESS.getCode()) {
				log.info("创建用户失败：" + save);
				flag = false;
			}else {
				AccountDo accountDo = (AccountDo) save.getData();
				account.setId(accountDo.getId());
				log.info("新增用户id：{},身份证号：{}",accountDo.getId(),accountDo.getIdCard());
			}
		}
	}

	/**
	 * 绑定银行卡
	 * @param bankCardDo 银行卡信息
	 */
	private void bindBankCard(BankCardDo bankCardDo) {
		if(flag) {
			CustomerException save = bankCardService.saveBindCard(bankCardDo);
			if(save.getCode() != ErrorEnum.SUCCESS.getCode()) {
				log.info("绑定银行卡失败，用户id：{}, 银行卡号：{}, errMsg：{}",bankCardDo.getAccountId(),
						bankCardDo.getCardNumber(), save);
				flag = false;
			}else {
				BankCardDo data = (BankCardDo) save.getData();
				bankCardDo.setId(data.getId());
				log.info("绑定银行卡，用户id：{}, 银行卡id：{}",data.getAccountId(),data.getId());
			}
		}
	}

	/**
	 * 使用银行卡充值
	 * @param account 充值账户信息
	 */
	private void charge(AccountDo account) {
		if(flag) {
			TransferDTO transferDTO = new TransferDTO();
			String s = UUID.randomUUID().toString();
			transferDTO.setOrderNo(s);
			System.out.println("charge: " + s);
			transferDTO.setType(TransferTypeEnum.DEPOSIT.getNum());
			transferDTO.setChannel(TransferChannelEnum.BANKCARD_CHANNEL.getValue());
			transferDTO.setAmount(new BigDecimal(100));
			transferDTO.setFromAccountId(account.getId());
			transferDTO.setBankCardId(bankCard.getId());
			CustomerException transfer = bankCardTransfer.transfer(transferDTO);
			if(transfer.getCode() != ErrorEnum.SUCCESS.getCode()) {
				log.info("充值失败，账户id：{}, errMsg： {}",account.getId(),transfer);
				flag = false;
			}
		}
	}

	/**
	 * 使用余额转账99元
	 * @param fromAccount 发起者账户
	 * @param toAccount 接收者账户
	 */
	private void transfer(AccountDo fromAccount,AccountDo toAccount) {
		if(flag) {
			TransferDTO transferDTO = new TransferDTO();
			String s = UUID.randomUUID().toString();
			transferDTO.setOrderNo(s);
			System.out.println("transfer: " + s);
			transferDTO.setAmount(new BigDecimal(99));
			transferDTO.setFromAccountId(fromAccount.getId());
			transferDTO.setToAccountId(toAccount.getId());
			transferDTO.setType(TransferTypeEnum.TRANSFER.getNum());
			transferDTO.setChannel(TransferChannelEnum.BALANCE_CHANNEL.getValue());
			CustomerException transfer = balanceTransfer.transfer(transferDTO);
			if(null != transfer && transfer.getCode() != ErrorEnum.SUCCESS.getCode()) {
				log.info("转账失败，fromAccountId：{}, toAccountId: {}, errMsg： {}",fromAccount.getId(),
						toAccount.getId(),transfer.getMsg());
				flag = false;
			}
		}
	}

}
