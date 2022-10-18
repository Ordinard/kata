package org.bank.dao.models;

import java.util.UUID;

public class Customer {

    private UUID uuid;

    private String name;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }
}
