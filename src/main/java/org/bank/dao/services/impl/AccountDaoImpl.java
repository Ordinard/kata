package org.bank.dao.services.impl;

import org.bank.dao.configuration.DBConfiguration;
import org.bank.dao.configuration.DBInit;
import org.bank.dao.models.Account;
import org.bank.dao.models.Customer;
import org.bank.dao.models.Operation;
import org.bank.dao.services.api.AccountDao;
import org.bank.dto.OperationDTO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class AccountDaoImpl implements AccountDao {
    @Override
    public void deposit(OperationDTO operationDTO) {
        doOperation(operationDTO, true);
    }

    @Override
    public void withdraw(OperationDTO operationDTO) {
        doOperation(operationDTO, false);
    }

    @Override
    public List<OperationDTO> getHistory(UUID accountUuid) {
        final List<OperationDTO> operationDTOList = new LinkedList<>();
        try (final Connection connection = DBConfiguration.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement("select * from operation where account_uuid = ?");
            statement.setString(1, accountUuid.toString());
            final ResultSet operationRS = statement.executeQuery();
            while (operationRS.next()) {
                operationDTOList.add(new OperationDTO(operationRS.getLong(3), null, operationRS.getDate(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operationDTOList;
    }

    /**
     * Does a deposit or a withdrawal over an {@link Account}
     *
     * @param operationDTO The {@link Operation}
     * @param isPositive True for deposit, False for withdrawal
     */
    private void doOperation(OperationDTO operationDTO, boolean isPositive) {
        try (final Connection connection = DBConfiguration.getConnection()) {
            connection.setAutoCommit(false);
            final PreparedStatement statementUpdate = connection.prepareStatement("insert into operation values(?, ?, ?, ?)");
            final Operation operation = new Operation(
                    UUID.randomUUID(),
                    DBInit.ACCOUNT_1_UUID,
                    operationDTO.getAmount(),
                    new Date(System.currentTimeMillis())
            );
            statementUpdate.setString(1, operation.getUuid().toString());
            statementUpdate.setString(2, operation.getAccountUuid().toString());
            statementUpdate.setLong(3, isPositive ? operation.getAmount() : -operation.getAmount());
            statementUpdate.setDate(4, operation.getDate());
            statementUpdate.executeUpdate();
            final PreparedStatement statementGet = connection.prepareStatement("select total_amount from account where uuid = ?");
            statementGet.setString(1, DBInit.ACCOUNT_1_UUID.toString());
            final long totalAmount = statementGet.executeQuery().getLong(1);
            final PreparedStatement statementUpdateAmount = connection.prepareStatement("update account set total_amount = ?");
            statementUpdateAmount.setLong(1, totalAmount + (isPositive ? operationDTO.getAmount() : -operationDTO.getAmount()));
            statementUpdateAmount.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
