package com.capgemini.repository;

import com.capgemini.model.Account;

public interface IAccountRepository {
public boolean save(Account account);
public Account searchAccount(int accountNumber);
}
