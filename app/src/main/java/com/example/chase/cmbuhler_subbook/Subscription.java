package com.example.chase.cmbuhler_subbook;

/**
 * Created by Chase on 2018-01-17.
 *
 * This class provides an outline for a subscription object
 * that will be used by SubList to keep track of information about
 * a particular subscription the user adds.
 */

public class Subscription {

    private String name;
    private int year;
    private int month;
    private int day;
    private float charge;
    private String comment;

    /**
     *
     * Constructor:
     * Name: Name of the subscription
     * Year/Month/Day: Start date of the subscription.
     * Charge: Monthly charge of the subscription.
     * Comment: (Optional) Comment about the subscription.
     */
    public Subscription(String name, int year, int month, int day, float charge){
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
        this.charge = charge;
        this.comment = null;
    }

    /**
     *
     * Constructor when comment has a value
     */
    public Subscription(String name, int year, int month, int day, float charge,
                        String comment){
        this(name, year, month, day, charge);
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public static String getMonth(int month){
        switch(month){
            case 0: return "January";
            case 1: return "February";
            case 2: return "March";
            case 3: return "April";
            case 4: return "May";
            case 5: return "June";
            case 6: return "July";
            case 7: return "August";
            case 8: return "September";
            case 9: return "October";
            case 10: return "November";
            case 11: return "December";
            default:
                return null;
        }
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public float getCharge() {
        return charge;
    }

    public void setCharge(float charge) {
        this.charge = charge;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
