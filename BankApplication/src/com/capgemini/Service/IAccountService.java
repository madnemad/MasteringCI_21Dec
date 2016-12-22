package com.capgemini.Service;

import com.capgemini.Exception.InsufficientBalanceException;
import com.capgemini.Exception.InsufficientInitialBalanceException;
import com.capgemini.Exception.InvalidAccountNumberException;
import com.capgemini.model.Account;

public interface IAccountService {

	Account createAccount(int accountNumber, int amount) throws InsufficientInitialBalanceException;
	int withdrawAmount(int amount, int accountNumber) throws InsufficientBalanceException , InvalidAccountNumberException;
	int showBalance(int accountNumber) throws InvalidAccountNumberException;
	int depositeAmount(int amount , int accountNumber) throws InvalidAccountNumberException;
	String  fundTransfer(int amount , int accountNumber , int tranferAccountNumber) throws InvalidAccountNumberException , InsufficientBalanceException;
}