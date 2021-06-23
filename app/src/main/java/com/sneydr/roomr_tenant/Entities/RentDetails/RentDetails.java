package com.sneydr.roomr_tenant.Entities.RentDetails;

import android.view.animation.Animation;

import com.sneydr.roomr_tenant.App.UI.CircularProgressBarAnimation;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


public class RentDetails {

    private String rentDueDate;
    private int baseRent;
    private int parkingAmount;
    private String rentMadePayableTo;

    public RentDetails(String rentDueDate, int baseRent, int parkingAmount, String rentMadePayableTo) {
        this.rentDueDate = rentDueDate;
        this.baseRent = baseRent;
        this.parkingAmount = parkingAmount;
        this.rentMadePayableTo = rentMadePayableTo;

    }

    public String getTotalRent() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(baseRent + parkingAmount);
    }


    public String getRentDueDate() {
        return rentDueDate;
    }

    public int getBaseRent() {
        return baseRent;
    }


    public int getParkingAmount() {
        return parkingAmount;
    }

    public String getRentMadePayableTo() {
        return rentMadePayableTo;
    }


    public String getFormattedDaysLeft() {
        CalendarHandler calendarHandler = new CalendarHandler();
        int daysLeft = calendarHandler.getDaysUntilDueDate(this.rentDueDate) - calendarHandler.getDayInMonth();
        return daysLeft + "\ndays";
    }

    public int getDayInMonth() {
        CalendarHandler calendarHandler = new CalendarHandler();
        return calendarHandler.getDayInMonth();
    }

    public int getTotalDaysInMonth() {
        CalendarHandler calendarHandler = new CalendarHandler();
        return calendarHandler.getTotalDaysInMonth();
    }

    public String getFormattedDueDate() {
        CalendarHandler calendarHandler = new CalendarHandler();
        return calendarHandler.getFormattedDueDate(this.rentDueDate);
    }

}
