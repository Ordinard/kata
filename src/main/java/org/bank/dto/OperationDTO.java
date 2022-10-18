package org.bank.dto;

import java.util.Date;

public class OperationDTO {

    private long amount;

    private CustomerDTO customerDTO;

    private Date date;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public CustomerDTO getCustomer() {
        return customerDTO;
    }

    public void setCustomer(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OperationDTO(long amount, CustomerDTO customerDTO, Date date) {
        this.amount = amount;
        this.customerDTO = customerDTO;
        this.date = date;
    }
}
