package org.bank.services.impl;

import org.bank.dao.models.Account;
import org.bank.dao.services.api.AccountDao;
import org.bank.dao.services.impl.AccountDaoImpl;
import org.bank.dto.OperationDTO;
import org.bank.services.api.BankService;

import java.util.List;
import java.util.UUID;

public class BankServiceImpl implements BankService {

    /** The DAO for {@link Account} */
    private final AccountDao accountDao;

    public BankServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public BankServiceImpl() {
        this.accountDao = new AccountDaoImpl();
    }

    @Override
    public void deposit(OperationDTO operation) {
        this.accountDao.deposit(operation);
    }

    @Override
    public void withdraw(OperationDTO operation) {
        this.accountDao.withdraw(operation);
    }

    @Override
    public List<OperationDTO> getHistory(UUID accountUuid) {
        return this.accountDao.getHistory(accountUuid);
    }
}
