package com.reboot;

import com.reboot.credit.Credit;
import com.reboot.credit.paymentSchedule.PaymentType;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Credit credit = new Credit(1000000, 11.1F, 0, 60, PaymentType.ANNUITY,
                new GregorianCalendar(2019, Calendar.NOVEMBER, 25), 1);
    }
}
