package com.rohasoft.www.gcash.modal;

/**
 * Created by Ayothi selvam on 09-11-2017.
 */

public class Settlement {

    String dueDate,payAmount,status;

    public Settlement(String dueDate, String payAmount, String status) {
        this.dueDate = dueDate;
        this.payAmount = payAmount;
        this.status = status;
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
