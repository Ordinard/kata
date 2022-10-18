package org.bank.dao.models;

import java.util.UUID;

public class Account {

    private UUID uuid;

    private long totalAmount;

    private UUID customerUuid;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public UUID getCustomerUuid() {
        return customerUuid;
    }

    public void setCustomerUuid(UUID customerUuid) {
        this.customerUuid = customerUuid;
    }

    public Account(UUID uuid, long totalAmount, UUID customerUuid) {
        this.uuid = uuid;
        this.totalAmount = totalAmount;
        this.customerUuid = customerUuid;
    }
}
