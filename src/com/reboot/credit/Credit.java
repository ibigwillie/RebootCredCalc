package com.reboot.credit;

import com.reboot.credit.paymentSchedule.PaymentSchedule;
import com.reboot.credit.paymentSchedule.PaymentType;

import java.util.GregorianCalendar;

public class Credit {
    private float amount;
    private float percent;
    private float initialFee;
    private int duration;
    private PaymentType paymentType;
    private GregorianCalendar firstPayment;
    private int frequencyOfPayment;
    private PaymentSchedule paymentSchedule;

    private float totalAmount;

    public Credit() {
    }

    public Credit(float amount, float percent, float initialFee, int duration, PaymentType paymentType,
                  GregorianCalendar firstPayment, int frequencyOfPayment) {
        this.amount = amount;
        this.percent = percent;
        this.initialFee = initialFee;
        this.duration = duration;
        this.paymentType = paymentType;
        this.firstPayment = firstPayment;
        this.frequencyOfPayment = frequencyOfPayment;

        this.totalAmount = this.amount - this.initialFee;
        if (this.totalAmount < 0){
//            todo: throw exception
        }

        getPaymentSchedule(this.paymentType, this.totalAmount, this.percent, this.duration, this.frequencyOfPayment,
                this.firstPayment);
    }

    private void getPaymentSchedule(PaymentType paymentType, float totalAmount, float percent, int duration,
                                    int frequencyOfPayment, GregorianCalendar firstPayment){
        paymentSchedule = new PaymentSchedule();
        paymentSchedule.build(paymentType, totalAmount, percent, duration, frequencyOfPayment, firstPayment);
    }
}
