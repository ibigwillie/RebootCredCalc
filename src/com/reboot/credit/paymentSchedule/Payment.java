package com.reboot.credit.paymentSchedule;

import java.util.Date;
import java.util.GregorianCalendar;

public class Payment {
    private GregorianCalendar date;
    private double amount;
    private double amountMainDebt;
    private double amountPercent;
    private double remainingDebt;
    private double currentDebt;
    private GregorianCalendar fromDate;
    private GregorianCalendar toDate;

    public Payment() {
    }

    public Payment(GregorianCalendar date, double amount, double amountMainDebt, double amountPercent, double remainingDebt) {
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmountMainDebt() {
        return amountMainDebt;
    }

    public void setAmountMainDebt(double amountMainDebt) {
        this.amountMainDebt = amountMainDebt;
    }

    public double getAmountPercent() {
        return amountPercent;
    }

    public void setAmountPercent(double amountPercent) {
        this.amountPercent = amountPercent;
    }

    public double getRemainingDebt() {
        return remainingDebt;
    }

    public void setRemainingDebt(double remainingDebt) {
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

    public double getCurrentDebt() {
        return currentDebt;
    }

    public void setCurrentDebt(double currentDebt) {
        this.currentDebt = currentDebt;
    }
}
