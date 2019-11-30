package com.reboot.credit.paymentSchedule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class PaymentSchedule {
    private List<Payment> paymentList;

    public PaymentSchedule() {
        paymentList = new ArrayList<>();
    }

    private void addPayment(Payment payment){
        paymentList.add(payment);
    }

    private double getPayPerPeriod(PaymentType paymentType, double creditAmount, double percent, int duration){
        double pay = 0;
        switch(paymentType) {
            case ANNUITY:
                pay = (creditAmount * (percent / (12 * 100))) /
                        (1 - Math.pow(1 + (percent / (12 * 100)), -duration));
                break;
            case DIFFERENTIAL:
                pay = creditAmount / duration;
                break;
        }
        pay = new BigDecimal(pay).setScale(2, RoundingMode.UP).doubleValue();
        return pay;
    }

    private double getPercentAmountPerPeriod(){
        return 0;
    }

    private int getDaysCountByFrequencyOfPayment(GregorianCalendar payDay, int frequencyOfPayment){
        GregorianCalendar dayFrom = (GregorianCalendar) payDay.clone();

        dayFrom.add(Calendar.MONTH, -1);
        dayFrom.add(Calendar.DAY_OF_MONTH, 1);
//        System.out.printf("Day from: %s\nDay to  : %s\n", dayFrom.getTime(),payDay.getTime());
//        System.out.printf("%d\n", (int) ((payDay.getTimeInMillis() - dayFrom.getTimeInMillis()) / (24 * 60 * 60 * 1000) + 1));

        return (int) ((payDay.getTimeInMillis() - dayFrom.getTimeInMillis()) / (24 * 60 * 60 * 1000) + 1);
    }

    public void build(PaymentType paymentType, double creditAmount, double percent, int duration, int frequencyOfPayment,
                      GregorianCalendar firstPayment){
        Payment payment;
        GregorianCalendar percentAccountingFrom;
        GregorianCalendar percentAccountingTo;
        double percentAccounting;

        double payPerPeriod = getPayPerPeriod(paymentType, creditAmount, percent, duration);

        GregorianCalendar currPayment = (GregorianCalendar) firstPayment.clone();
        GregorianCalendar lastPayment = (GregorianCalendar) firstPayment.clone();
        lastPayment.add(Calendar.MONTH, duration);

        double tempDebt = creditAmount;

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
//            System.out.printf("\tDay from: %s\n\tDay to  : %s\n", percentAccountingFrom.getTime(),percentAccountingTo.getTime());
//            System.out.printf("\t%d\n", (int) ((percentAccountingTo.getTimeInMillis() - percentAccountingFrom.getTimeInMillis()) / (24 * 60 * 60 * 1000) + 1));

//            percentAccounting = tempDebt * percent / 100 / currPayment.getActualMaximum(Calendar.DAY_OF_YEAR)
//                    * ((percentAccountingTo.getTimeInMillis() - percentAccountingFrom.getTimeInMillis()) / (24 * 60 * 60 * 1000) + 1);
            percentAccounting = tempDebt * percent / 100 / currPayment.getActualMaximum(Calendar.DAY_OF_YEAR)
                    * getDaysCountByFrequencyOfPayment(currPayment, frequencyOfPayment);
            percentAccounting = new BigDecimal(percentAccounting).setScale(2, RoundingMode.UP).doubleValue();
            payment.setAmountPercent(percentAccounting);

            payment.setCurrentDebt(tempDebt);

            switch(paymentType){
                case ANNUITY:
                    // для аннуитета, последний платеж добиваем
                    if (tempDebt < payPerPeriod){
                        payment.setAmount(tempDebt);
                        payment.setAmountMainDebt(tempDebt - percentAccounting);
                        payment.setRemainingDebt(0);
                    }
                    else {
                        payment.setAmount(payPerPeriod);
                        payment.setAmountMainDebt(payPerPeriod - percentAccounting);
                        payment.setRemainingDebt(tempDebt - payment.getAmountMainDebt());
                        tempDebt -= payment.getAmountMainDebt();
                    }

                    break;
                case DIFFERENTIAL:
                    if (tempDebt < payPerPeriod){
                        payment.setAmount(tempDebt + percentAccounting);
                        payment.setAmountMainDebt(tempDebt);
                        payment.setRemainingDebt(0);
                    }
                    else {
                        payment.setAmount(payPerPeriod + percentAccounting);
                        payment.setAmountMainDebt(payPerPeriod);
                        payment.setRemainingDebt(tempDebt - payPerPeriod);
                        tempDebt -= payPerPeriod;
                    }
                    break;
            }

            payment.setFromDate((GregorianCalendar) percentAccountingFrom.clone());
            payment.setToDate((GregorianCalendar) percentAccountingTo.clone());

            addPayment(payment);
//            paymentList.add(payment);
            currPayment.add(Calendar.MONTH, 1);

            System.out.printf("%d\t%s\t%.2f\t%.2f\t%.2f\t\t%.2f\t%.2f\n", paymentList.size(), payment.getDate().getTime(), payment.getAmount(),
                    payment.getAmountMainDebt(), payment.getAmountPercent(), payment.getCurrentDebt(),
                    payment.getRemainingDebt());
        }
        System.out.println("");
    }
}
