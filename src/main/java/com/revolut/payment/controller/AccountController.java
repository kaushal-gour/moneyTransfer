package com.revolut.payment.controller;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revolut.payment.dto.request.NewAccountRequest;
import com.revolut.payment.dto.request.TransactionRequest;
import com.revolut.payment.dto.response.FundTransferResponse;
import com.revolut.payment.dto.response.ResponseDto;
import com.revolut.payment.dto.response.ResponseHeader;
import com.revolut.payment.exception.AccountCreationException;
import com.revolut.payment.exception.AccountNotFoundException;
import com.revolut.payment.model.Account;
import com.revolut.payment.service.AccountService;
import com.revolut.payment.validation.AccountTypeValidator;
import com.revolut.payment.validation.CurrencyValidator;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AccountService accountService;

	@Autowired
	CurrencyValidator currencyValidator;
	
	@Autowired
	AccountTypeValidator accountTypeValidator;

	@PostMapping("/account")
	public ResponseEntity<ResponseDto<Account>> createAccount(@Valid @RequestBody NewAccountRequest newAccountRequest,
			Errors errors) {

		ResponseDto<Account> response = new ResponseDto<>();
		ResponseHeader header = new ResponseHeader("CREATE");

		currencyValidator.validate(newAccountRequest.getCurrency(), errors);
		if (errors.hasErrors()) {
			return new ResponseEntity(createErrorString(errors), HttpStatus.BAD_REQUEST);
		}
		
		accountTypeValidator.validate(newAccountRequest.getType(), errors);
		if(errors.hasErrors()) {
			return new ResponseEntity(createErrorString(errors), HttpStatus.BAD_REQUEST);
		}

		Account account = accountService.createAccount(newAccountRequest);
		if (null != account) {
			LOGGER.info("Account created successfully.");
			header.setStatus("SUCCESS");
			response.setBody(account);
			response.setHeader(header);
		} else {
			LOGGER.error("Error in creating account.");
			throw new AccountCreationException("We are facing technical issue. Please try after some time.");
		}

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/account/{accountId}/balance")
	public ResponseEntity<ResponseDto<Account>> getBalanace(@PathVariable(required = true) Long accountId) {
		ResponseDto<Account> response = new ResponseDto<>();
		ResponseHeader header = new ResponseHeader("CHECK_BALANCE");

		Optional<Account> account = accountService.getAccount(accountId);
		if (account.isPresent()) {
			header.setStatus("SUCCESS");
			response.setBody(account.get());
			response.setHeader(header);
		} else {
			LOGGER.error("Account not found with id: " + accountId);
			throw new AccountNotFoundException("Account not found");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/account/{accountId}/deposit")
	public ResponseEntity<ResponseDto<Account>> deposit(@PathVariable Long accountId,
			@Valid @RequestBody TransactionRequest transactionRequest, Errors errors) {

		currencyValidator.validate(transactionRequest.getCurrency(), errors);
		if (errors.hasErrors()) {
			return new ResponseEntity(createErrorString(errors), HttpStatus.BAD_REQUEST);
		}

		ResponseDto<Account> response = new ResponseDto<>();
		ResponseHeader header = new ResponseHeader("DEPOSIT");
		Optional<Account> account = accountService.deposit(accountId, transactionRequest);

		if (account.isPresent()) {
			header.setStatus("SUCCESS");
			response.setBody(account.get());
		} else {
			header.setStatus("FAILED");
		}

		response.setHeader(header);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/account/{accountId}/withdraw")
	public ResponseEntity<ResponseDto<Account>> withdraw(@PathVariable(required = true) Long accountId,
			@Valid @RequestBody TransactionRequest transactionRequest, Errors errors) {

		currencyValidator.validate(transactionRequest.getCurrency(), errors);
		if (errors.hasErrors()) {
			return new ResponseEntity(createErrorString(errors), HttpStatus.BAD_REQUEST);
		}

		ResponseDto<Account> response = new ResponseDto<>();
		ResponseHeader header = new ResponseHeader("WITHDRAW");
		Optional<Account> account = accountService.withdraw(accountId, transactionRequest);

		if (account.isPresent()) {
			header.setStatus("SUCCESS");
			response.setBody(account.get());
		} else {
			header.setStatus("FAILED");
		}

		response.setHeader(header);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/account/{fromAccountId}/transfer/{toAccountId}")
	public ResponseEntity<ResponseDto<FundTransferResponse>> fundTransfer(
			@PathVariable(required = true) Long fromAccountId, @PathVariable(required = true) Long toAccountId,
			@Valid @RequestBody TransactionRequest transactionRequest, Errors errors) {

		currencyValidator.validate(transactionRequest.getCurrency(), errors);
		if (errors.hasErrors()) {
			return new ResponseEntity(createErrorString(errors), HttpStatus.BAD_REQUEST);
		}

		ResponseDto<FundTransferResponse> response = new ResponseDto<>();
		ResponseHeader header = new ResponseHeader("FUND_TRANSFER");

		if (fromAccountId.equals(toAccountId)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		FundTransferResponse fundTransferResponse = accountService.fundTransfer(fromAccountId, toAccountId,
				transactionRequest);
		if (null != fundTransferResponse) {
			header.setStatus("SUCCESS");
			response.setBody(fundTransferResponse);
		} else {
			header.setStatus("FAILED");
		}
		response.setHeader(header);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private String createErrorString(Errors errors) {
		return errors.getAllErrors().stream().map(ObjectError -> ObjectError.getCode())
				.collect(Collectors.joining(","));
	}

}
