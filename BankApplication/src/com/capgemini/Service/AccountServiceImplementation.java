package com.capgemini.Service;

import com.capgemini.Exception.InsufficientBalanceException;
import com.capgemini.Exception.InsufficientInitialBalanceException;
import com.capgemini.Exception.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.IAccountRepository;

public class AccountServiceImplementation implements IAccountService {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.capgemini.Service.IAccountService#createAccount(int, int)
	 */
	IAccountRepository accountRepository;

	public AccountServiceImplementation(IAccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public Account createAccount(int accountNumber, int amount) throws InsufficientInitialBalanceException {

		if (amount < 500) {
			throw new InsufficientInitialBalanceException();
		}

		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		if (amount > 500) {
			accountRepository.save(account);
		}

		return account;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.capgemini.Service.IAccountService1#withdrawAmount(int, int)
	 */
	@Override
	public int withdrawAmount(int amount, int accountNumber)
			throws InsufficientBalanceException, InvalidAccountNumberException {
		Account account1 = new Account();

		account1.setAccountNumber(101);
		account1.setAmount(500);

		if (amount > account1.getAmount() || amount == 0) {
			throw new InsufficientBalanceException();
		}

		if (amount <= account1.getAmount() && accountNumber == account1.getAccountNumber()) {

			account1.setAmount(account1.getAmount() - amount);
			accountRepository.save(account1);
		} else {
			throw new InvalidAccountNumberException();
		}

		return amount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.capgemini.Service.IAccountService1#withdrawAmount(int, int)
	 */
	@Override
	public int showBalance(int acountNumber) throws InvalidAccountNumberException {

		Account account = new Account();

		account.setAccountNumber(101);
		account.setAmount(600);

		if (acountNumber != account.getAccountNumber()) {
			throw new InvalidAccountNumberException();
		} else {
			account = accountRepository.searchAccount(acountNumber);
		}
		return account.getAmount();

	}

	@Override
	public int depositeAmount(int amount, int accountNumber) throws InvalidAccountNumberException {
		Account account = new Account();
		int totalAmount;
		account.setAmount(500);
		account.setAccountNumber(101);

		if (accountNumber != account.getAccountNumber()) {
			throw new InvalidAccountNumberException();
		} else {
			totalAmount = amount + account.getAmount();
			account.setAmount(totalAmount);
			accountRepository.save(account);
		}

		return totalAmount;
	}

	@Override
	public String  fundTransfer(int amount, int accountNumber, int tranferAccountNumber)
			throws InvalidAccountNumberException, InsufficientBalanceException {
		int totalAmount;
		Account account1 = new Account();
		account1.setAccountNumber(101);
		account1.setAmount(500);

		Account account2 = new Account();
		account2.setAccountNumber(102);
		account2.setAmount(600);

		if (accountNumber != account1.getAccountNumber() || tranferAccountNumber != account2.getAccountNumber()) {
			throw new InvalidAccountNumberException();
		} else if (amount > account1.getAmount()) {
			throw new InsufficientBalanceException();
		} else {
			totalAmount = amount + account2.getAmount();
			account2.setAmount(totalAmount);
			account1.setAmount(account1.getAmount()-amount);
			accountRepository.save(account1);
			accountRepository.save(account2);
		}
		
		return "transfered";
	}
}
