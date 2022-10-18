package org.bank.dao.services.api;

import org.bank.dao.models.Account;
import org.bank.dto.OperationDTO;

import java.util.List;
import java.util.UUID;

public interface AccountDao {

    /**
     * Add amount to an {@link Account}
     *
     * @param operationDTO The deposit operation
     */
    void deposit(OperationDTO operationDTO);

    /**
     * Remove amount to an {@link Account}
     *
     * @param operationDTO The withdraw operation
     */
    void withdraw(OperationDTO operationDTO);

    /**
     * Get the history of the operations done on an {@link Account}
     *
     * @param accountUuid The identifier of the account
     * @return The history of the operations done on an {@link Account}
     */
    List<OperationDTO> getHistory(UUID accountUuid);

}
