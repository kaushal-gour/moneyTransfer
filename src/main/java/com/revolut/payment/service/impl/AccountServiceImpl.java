package com.revolut.payment.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revolut.payment.dto.request.NewAccountRequest;
import com.revolut.payment.dto.request.TransactionRequest;
import com.revolut.payment.dto.response.FundTransferResponse;
import com.revolut.payment.exception.AccountNotFoundException;
import com.revolut.payment.exception.LowBalanceException;
import com.revolut.payment.model.Account;
import com.revolut.payment.repo.AcountRepository;
import com.revolut.payment.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AcountRepository accountRepository;

	public Account createAccount(NewAccountRequest newAccountRequest) {

		Account account = new Account();
		account.setBalance(0.00);
		account.setCurrency(newAccountRequest.getCurrency().toUpperCase());
		account.setType(newAccountRequest.getType().toUpperCase());
		return accountRepository.save(account);

	}

	@Override
	public Optional<Account> getAccount(Long accountId) {

		return accountRepository.findById(accountId);

	}

	@Override
	public Optional<Account> deposit(Long accountId, TransactionRequest transactionRequest) {
		Optional<Account> account = accountRepository.findById(accountId);
		if (!account.isPresent()) {
			LOGGER.error("Account not found for accountID: "+accountId);
			throw new AccountNotFoundException("Account not found");
		}
		account.get().setBalance(account.get().getBalance() + transactionRequest.getAmount());
		accountRepository.save(account.get());
		return account;
	}

	@Override
	public Optional<Account> withdraw(Long accountId, TransactionRequest transactionRequest) {
		Optional<Account> account = accountRepository.findById(accountId);
		if (!account.isPresent()) {
			LOGGER.error("Account not found for accountID: "+accountId);
			throw new AccountNotFoundException("Account not found");
		}
		if(account.get().getBalance()< transactionRequest.getAmount()) {
			LOGGER.error("Insufficient balance for  accountID: "+accountId);
			throw new LowBalanceException("Insufficient balance");
		}
		account.get().setBalance(account.get().getBalance() - transactionRequest.getAmount());
		accountRepository.save(account.get());
		return account;
	}

	@Override
	public FundTransferResponse fundTransfer(Long fromAccountId, Long toAccountId, TransactionRequest transactionRequest) {
		Optional<Account> fromAccount = accountRepository.findById(fromAccountId);
		Optional<Account> toAccount = accountRepository.findById(toAccountId);
		if(!fromAccount.isPresent() || !toAccount.isPresent()) {
			LOGGER.error("Account not found");
			throw new AccountNotFoundException("Account not found");
		} 
		if(fromAccount.get().getBalance() < transactionRequest.getAmount()) {
			LOGGER.error("Insufficient balance");
			throw new LowBalanceException("Insufficient balance");
		}
		fromAccount.get().setBalance(fromAccount.get().getBalance() - transactionRequest.getAmount());
		toAccount.get().setBalance(toAccount.get().getBalance() + transactionRequest.getAmount());
		
		accountRepository.save(fromAccount.get());
		accountRepository.save(toAccount.get());
		
		FundTransferResponse response = new FundTransferResponse();
		response.setFrom(fromAccount.get());
		response.setTo(toAccount.get());
		
		return response;
	}

}
