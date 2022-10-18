package integration;

import org.bank.dao.configuration.DBConfiguration;
import org.bank.dao.configuration.DBInit;
import org.bank.dao.models.Account;
import org.bank.dao.models.Operation;
import org.bank.dto.CustomerDTO;
import org.bank.dto.OperationDTO;
import org.bank.services.api.BankService;
import org.bank.services.impl.BankServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.List;
import java.util.UUID;

public class TestBankService {

    /** The service for {@link OperationDTO} */
    private final BankService bankService;

    /** One mock {@link CustomerDTO} */
    private final CustomerDTO customerDTO1;

    public TestBankService() {
        this.bankService = new BankServiceImpl();
        this.customerDTO1 = new CustomerDTO(UUID.randomUUID(), "Customer1");
    }

    @Before
    public void before() {
        DBInit.init();
    }

    @Test
    public void testDeposit() throws SQLException {
        final OperationDTO operation = new OperationDTO(10L, this.customerDTO1, null);
        this.bankService.deposit(operation);
        try (Connection connection = DBConfiguration.getConnection()) {
            final Statement statement = connection.createStatement();
            final ResultSet operationRS = statement.executeQuery("select * from operation");
            final Operation operationFound = new Operation(
                    UUID.fromString(operationRS.getString(1)),
                    UUID.fromString(operationRS.getString(2)),
                    operationRS.getLong(3),
                    operationRS.getDate(4)
            );
            Assert.assertNotNull(operationFound);
            Assert.assertNotNull(operationFound.getDate());
            Assert.assertEquals(operation.getAmount(), operationFound.getAmount());
            final ResultSet accountRS = statement.executeQuery("select * from account");
            final Account accountRSFound = new Account(
                    UUID.fromString(accountRS.getString(1)),
                    accountRS.getLong(2),
                    UUID.fromString(accountRS.getString(3))
            );
            Assert.assertNotNull(accountRSFound);
            Assert.assertEquals(operation.getAmount(), accountRSFound.getTotalAmount());
        }
    }

    @Test
    public void testWithdraw() throws SQLException {
        final OperationDTO operation = new OperationDTO(10L, this.customerDTO1, null);
        this.bankService.withdraw(operation);
        try (Connection connection = DBConfiguration.getConnection()) {
            final Statement statement = connection.createStatement();
            final ResultSet operationRS = statement.executeQuery("select * from operation");
            final Operation operationFound = new Operation(
                    UUID.fromString(operationRS.getString(1)),
                    UUID.fromString(operationRS.getString(2)),
                    operationRS.getLong(3),
                    operationRS.getDate(4)
            );
            Assert.assertNotNull(operationFound);
            Assert.assertNotNull(operationFound.getDate());
            Assert.assertEquals(operation.getAmount(), -operationFound.getAmount());
            final ResultSet accountRS = statement.executeQuery("select * from account");
            final Account accountRSFound = new Account(
                    UUID.fromString(accountRS.getString(1)),
                    accountRS.getLong(2),
                    UUID.fromString(accountRS.getString(3))
            );
            Assert.assertNotNull(accountRSFound);
            Assert.assertEquals(operation.getAmount(), -accountRSFound.getTotalAmount());
        }
    }

    @Test
    public void testGetHistory() throws SQLException {
        try (Connection connection = DBConfiguration.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("insert into operation values ('%s', '%s', %d, %s)",
                    UUID.randomUUID().toString(),
                    DBInit.ACCOUNT_1_UUID,
                    10L,
                    new Date(System.currentTimeMillis())
            ));
            statement.executeUpdate(String.format("insert into operation values ('%s', '%s', %d, %s)",
                    UUID.randomUUID().toString(),
                    DBInit.ACCOUNT_1_UUID,
                    -5L,
                    new Date(System.currentTimeMillis())
            ));
        }
        final List<OperationDTO> operationDTOList = this.bankService.getHistory(DBInit.ACCOUNT_1_UUID);
        Assert.assertNotNull(operationDTOList);
        Assert.assertFalse(operationDTOList.isEmpty());
        Assert.assertEquals(2, operationDTOList.size());
    }

}
