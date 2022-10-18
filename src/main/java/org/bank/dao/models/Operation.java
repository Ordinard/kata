package org.bank.dao.models;

import java.sql.Date;
import java.util.UUID;

public class Operation {

    private UUID uuid;

    private UUID accountUuid;

    private long amount;

    private Date date;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(UUID accountUuid) {
        this.accountUuid = accountUuid;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Operation(UUID uuid, UUID accountUuid, long amount, Date date) {
        this.uuid = uuid;
        this.accountUuid = accountUuid;
        this.amount = amount;
        this.date = date;
    }
}
