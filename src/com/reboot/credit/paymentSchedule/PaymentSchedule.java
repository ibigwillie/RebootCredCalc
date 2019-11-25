package com.reboot.credit.paymentSchedule;

import java.util.*;

public class PaymentSchedule {
    private List<Payment> paymentList;
    private GregorianCalendar lastPayment;
    private GregorianCalendar currPayment;
    private Payment payment;
    private float payPerPeriod;
    private GregorianCalendar percentAccountingFrom;
    private GregorianCalendar percentAccountingTo;
    private float percentAccounting;

    public PaymentSchedule() {
        paymentList = new ArrayList<>();
    }

    private void addPayment(Payment payment){
        paymentList.add(payment);
    }

    public void build(PaymentType paymentType, float totalAmount, float percent, int duration, int frequencyOfPayment,
                      GregorianCalendar firstPayment){
        currPayment = (GregorianCalendar) firstPayment.clone();
        lastPayment = (GregorianCalendar) firstPayment.clone();
        lastPayment.add(Calendar.MONTH, duration);

        switch(paymentType){
            case ANNUITY:
                payPerPeriod = (float) ((totalAmount * (percent / (12*100))) /
                        (1 - Math.pow(1 + (percent / (12 * 100)), -duration)));
                break;
            case DIFFERENTIAL:
                payPerPeriod = totalAmount / duration;
                break;
        }

        float tempDebt = totalAmount;

        while(currPayment.compareTo(lastPayment) < 0) {
            payment = new Payment();
            payment.setDate((GregorianCalendar) currPayment.clone());

            percentAccountingFrom = (GregorianCalendar) currPayment.clone();
            percentAccountingTo = (GregorianCalendar) currPayment.clone();

            percentAccountingFrom.add(Calendar.DAY_OF_MONTH, 1);
            percentAccountingTo.add(Calendar.MONTH, 1);

//        percentAccountingTo.add(Calendar.DAY_OF_MONTH, -1);
//            System.out.println(currPayment.getTime());
//            System.out.println(percentAccountingFrom.getTime());
//            System.out.println(percentAccountingTo.getTime());
//
//            System.out.println((percentAccountingTo.getTimeInMillis() - percentAccountingFrom.getTimeInMillis())
//                    / (24 * 60 * 60 * 1000) + 1);

            percentAccounting = tempDebt * percent / 100 / currPayment.getActualMaximum(Calendar.DAY_OF_YEAR)
                    * ((percentAccountingTo.getTimeInMillis() - percentAccountingFrom.getTimeInMillis()) / (24 * 60 * 60 * 1000) + 1);

            payment.setAmountPercent(percentAccounting);


            if (tempDebt < payPerPeriod){
                payment.setAmount(tempDebt);
                payment.setAmountMainDebt(tempDebt - percentAccounting);
                payment.setRemainingDebt(0);
            }
            else {
                payment.setAmount(payPerPeriod);
                payment.setAmountMainDebt(payPerPeriod - percentAccounting);
                payment.setRemainingDebt(tempDebt - payment.getAmountMainDebt());
            }
            payment.setCurrentDebt(tempDebt);

            payment.setFromDate((GregorianCalendar) percentAccountingFrom.clone());
            payment.setToDate((GregorianCalendar) percentAccountingTo.clone());

            paymentList.add(payment);
            currPayment.add(Calendar.MONTH, 1);
            tempDebt -= payment.getAmountMainDebt();
            System.out.printf("%d\t%s\t%f\t%f\t%f\n", paymentList.size(), payment.getDate().getTime(), payment.getAmount(),
                    payment.getAmountMainDebt(), payment.getAmountPercent());
        }
        System.out.println("");
    }
}
