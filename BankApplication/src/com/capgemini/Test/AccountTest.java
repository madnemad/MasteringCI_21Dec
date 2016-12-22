package com.capgemini.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.Exception.InsufficientBalanceException;
import com.capgemini.Exception.InsufficientInitialBalanceException;
import com.capgemini.Exception.InvalidAccountNumberException;
import com.capgemini.Service.AccountServiceImplementation;
import com.capgemini.Service.IAccountService;
import com.capgemini.model.Account;
import com.capgemini.repository.IAccountRepository;

public class AccountTest {

	@Mock
	IAccountRepository accountRepository;

	IAccountService accountService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		accountService = new AccountServiceImplementation(accountRepository);
	}

	@Test(expected = com.capgemini.Exception.InsufficientInitialBalanceException.class)
	public void AmountLessThan500ThrowException() throws InsufficientInitialBalanceException {
		accountService.createAccount(101, 300);
	}

	@Test
	public void ValidInfoAccountCresteSuccessfully() throws InsufficientInitialBalanceException {
		Account account = new Account();

		account.setAccountNumber(102);
		account.setAmount(600);

		when(accountRepository.save(account)).thenReturn(true);

		assertEquals(account, accountService.createAccount(102, 600));
	}

	@Test(expected = InsufficientBalanceException.class)
	public void WithdrawAmountInsuficient() throws InsufficientBalanceException, InvalidAccountNumberException {
		accountService.withdrawAmount(600, 101);

	}

	@Test(expected = InvalidAccountNumberException.class)
	public void WithdrawAmountInvalidAccountNumber()
			throws InsufficientBalanceException, InvalidAccountNumberException {
		accountService.withdrawAmount(100, 102);

	}

	@Test
	public void validDataForWithdrawal() throws InsufficientBalanceException, InvalidAccountNumberException {
		Account account = new Account();

		when(accountRepository.save(account)).thenReturn(true);
		assertEquals(100,accountService.withdrawAmount(100, 101));
	}

	@Test(expected = InvalidAccountNumberException.class)
	public void showBalanceWithInvalidAccountNumber()
			throws InsufficientBalanceException, InvalidAccountNumberException {
		accountService.showBalance(106);

	}

	@Test
	public void showBalanceForValidAccountNumber() throws InvalidAccountNumberException{
		Account account = new Account();
		
		account.setAmount(600);
		
		when(accountRepository.searchAccount(101)).thenReturn(account);
		 assertEquals(account.getAmount(), accountService.showBalance(101));
	}
	@Test(expected = InvalidAccountNumberException.class)
	public void depositeAmountWithInvalidAccountNumber()
			throws InvalidAccountNumberException {
		accountService.depositeAmount(100, 106);
	}
	@Test
	public void depositeAmountForValidAccountNumber() throws InvalidAccountNumberException{
		 Account account = new Account();
		when(accountRepository.save(account)).thenReturn(true);
		 assertEquals(600, accountService.depositeAmount(100 , 101));
	}
	@Test(expected = InvalidAccountNumberException.class)
	public void fundTransferWithInvalidAccountNumber()
			throws InvalidAccountNumberException , InsufficientBalanceException  {
		accountService.fundTransfer(100, 106 , 102);
	}
	@Test(expected = InvalidAccountNumberException.class)
	public void fundTransferWithInvalidTransferedAccountNumber()
			throws InvalidAccountNumberException , InsufficientBalanceException  {
		accountService.fundTransfer(100, 101 , 106);
	}
	@Test(expected = InsufficientBalanceException.class)
	public void fundTransferWithInvalidTranferAmount()
			throws InvalidAccountNumberException , InsufficientBalanceException  {
		accountService.fundTransfer(700, 101 , 102);
	}
	@Test 
	public void fundTransferWithValidData()
			throws InvalidAccountNumberException , InsufficientBalanceException  {
		Account account = new Account();
		when(accountRepository.save(account)).thenReturn(true);
		assertEquals("transfered",	accountService.fundTransfer(100, 101 , 102));
	}
	
}
