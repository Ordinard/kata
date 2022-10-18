package org.bank.services.api;

import org.bank.dao.models.Account;
import org.bank.dto.OperationDTO;

import java.util.List;
import java.util.UUID;

public interface BankService {

    /**
     * Add amount to an {@link Account}
     *
     * @param operation The deposit operation
     */
    void deposit(OperationDTO operation);

    /**
     * Remove amount to an {@link Account}
     *
     * @param operation The withdraw operation
     */
    void withdraw(OperationDTO operation);

    /**
     * Get the history of the operations done on an {@link Account}
     *
     * @param accountUuid The identifier of the account
     * @return The history of the operations done on an {@link Account}
     */
    List<OperationDTO> getHistory(UUID accountUuid);

}
