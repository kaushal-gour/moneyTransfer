package com.revolut.payment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revolut.payment.model.Account;

@Repository
public interface AcountRepository extends JpaRepository<Account, Long>{

}
