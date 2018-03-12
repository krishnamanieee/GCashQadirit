package com.rohasoft.www.gcash.modal;

/**
 * Created by Ayothi selvam on 09-11-2017.
 */

public class Settlement {

    String dueDate,payAmount,status, partnerCode,password;

    public Settlement(String dueDate, String payAmount, String status) {
        this.dueDate = dueDate;
        this.payAmount = payAmount;
        this.status = status;
    }

    public Settlement(String partnerCode, String password) {
        this.partnerCode = partnerCode;
        this.password = password;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public String getstatus() {
        return status;
    }
}
