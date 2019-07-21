package com.revolut.payment.service;

import java.util.Optional;

import com.revolut.payment.dto.request.NewAccountRequest;
import com.revolut.payment.dto.request.TransactionRequest;
import com.revolut.payment.dto.response.FundTransferResponse;
import com.revolut.payment.model.Account;

public interface AccountService {
	
	public Account createAccount(NewAccountRequest newAccountRequest);
	
	public Optional<Account> getAccount(Long accountId);
	
	public Optional<Account> deposit(Long accountId, TransactionRequest transactionRequest);
	
	public Optional<Account> withdraw(Long accountId, TransactionRequest transactionRequest);
	
	public FundTransferResponse fundTransfer(Long fromAccountId, Long toAccountId, TransactionRequest transactionRequest);

}
