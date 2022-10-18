package unit;

import org.bank.dao.services.api.AccountDao;
import org.bank.dto.CustomerDTO;
import org.bank.dto.OperationDTO;
import org.bank.services.api.BankService;
import org.bank.services.impl.BankServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.*;

public class TestBankService {

    /** The service for {@link OperationDTO} */
    private final BankService bankService;

    /** One mock {@link CustomerDTO} */
    private final CustomerDTO customerDTO1;

    /** One mock for {@link AccountDao} */
    private final MockAccountDao accountDao;

    public TestBankService() {
        this.accountDao = new MockAccountDao();
        this.bankService = new BankServiceImpl(this.accountDao);
        this.customerDTO1 = new CustomerDTO(UUID.randomUUID(), "Customer1");
    }

    @Before
    public void before() {
        this.accountDao.reset();
    }

    @Test
    public void testDeposit() {
        final OperationDTO operation = new OperationDTO(10L, this.customerDTO1, null);
        this.bankService.deposit(operation);
        Assert.assertTrue(this.accountDao.getMethodsCall().containsKey("deposit"));
        Assert.assertEquals(operation, this.accountDao.getMethodsCall().get("deposit"));
    }

    @Test
    public void testWithdraw() {
        final OperationDTO operation = new OperationDTO(10L, this.customerDTO1, null);
        this.bankService.withdraw(operation);
        Assert.assertTrue(this.accountDao.getMethodsCall().containsKey("withdraw"));
        Assert.assertEquals(operation, this.accountDao.getMethodsCall().get("withdraw"));
    }

    @Test
    public void testGetHistory() throws SQLException {
        final UUID accountUuid = UUID.randomUUID();
        final List<OperationDTO> operationDTOList = this.bankService.getHistory(accountUuid);
        Assert.assertNotNull(operationDTOList);
        Assert.assertTrue(this.accountDao.getMethodsCall().containsKey("getHistory"));
        Assert.assertEquals(accountUuid, this.accountDao.getMethodsCall().get("getHistory"));
    }

    private static class MockAccountDao implements AccountDao {

        private final Map<String, Object> methodsCall;

        public Map<String, Object> getMethodsCall() {
            return methodsCall;
        }

        private MockAccountDao() {
            this.methodsCall = new HashMap<>();
        }

        @Override
        public void deposit(OperationDTO operationDTO) {
            this.methodsCall.put("deposit", operationDTO);
        }

        @Override
        public void withdraw(OperationDTO operationDTO) {
            this.methodsCall.put("withdraw", operationDTO);
        }

        @Override
        public List<OperationDTO> getHistory(UUID accountUuid) {
            this.methodsCall.put("getHistory", accountUuid);
            return new ArrayList<>();
        }

        public void reset() {
            this.methodsCall.clear();
        }
    }

}
