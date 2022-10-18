package org.bank.dto;

import java.util.UUID;

public class CustomerDTO {

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

    public CustomerDTO(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }
}
