package com.reboot.credit.paymentSchedule;

import java.util.Date;
import java.util.GregorianCalendar;

public class Payment {
    private GregorianCalendar date;
    private float amount;
    private float amountMainDebt;
    private float amountPercent;
    private float remainingDebt;
    private float currentDebt;
    private GregorianCalendar fromDate;
    private GregorianCalendar toDate;

    public Payment() {
    }

    public Payment(GregorianCalendar date, float amount, float amountMainDebt, float amountPercent, float remainingDebt) {
        this.date = date;
        this.amount = amount;
        this.amountMainDebt = amountMainDebt;
        this.amountPercent = amountPercent;
        this.remainingDebt = remainingDebt;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getAmountMainDebt() {
        return amountMainDebt;
    }

    public void setAmountMainDebt(float amountMainDebt) {
        this.amountMainDebt = amountMainDebt;
    }

    public float getAmountPercent() {
        return amountPercent;
    }

    public void setAmountPercent(float amountPercent) {
        this.amountPercent = amountPercent;
    }

    public float getRemainingDebt() {
        return remainingDebt;
    }

    public void setRemainingDebt(float remainingDebt) {
        this.remainingDebt = remainingDebt;
    }

    public GregorianCalendar getFromDate() {
        return fromDate;
    }

    public void setFromDate(GregorianCalendar fromDate) {
        this.fromDate = fromDate;
    }

    public GregorianCalendar getToDate() {
        return toDate;
    }

    public void setToDate(GregorianCalendar toDate) {
        this.toDate = toDate;
    }

    public float getCurrentDebt() {
        return currentDebt;
    }

    public void setCurrentDebt(float currentDebt) {
        this.currentDebt = currentDebt;
    }
}
