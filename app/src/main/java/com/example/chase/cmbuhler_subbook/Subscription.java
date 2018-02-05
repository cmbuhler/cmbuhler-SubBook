/*
 * Subscription
 *
 * February 4, 2018
 *
 * Copyright © Chase Buhler, CMPUT301, University of Alberta - All rights reserved.
 * You may use, distribute, or modify this code under the terms and conditions of the
 * Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in this project. Otherwise please contact cmbuhler@ualberta.ca
 */

package com.example.chase.cmbuhler_subbook;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This class provides an outline for a subscription object
 * that will be used by SubList to keep track of information about
 * a particular subscription the user adds.
 *
 * @author Chase Buhler
 * @see SubList
 * @see SubAdapter
 */
public class Subscription {

    private String name;
    private Calendar date;
    private float charge;
    private String comment;

    /**
     * Constructs a subscription with a null comment.
     * @param name Name of the subscription
     * @param year Year of the start date
     * @param month Month of the start date
     * @param day Day of the month of the start date
     * @param charge Monthly charge of the subscription.
     */
    public Subscription(String name, int year, int month, int day,
                        float charge){
        this(name, year, month, day, charge, null);
    }

    /**
     * Constructs a subscription with given values
     * @param name Name of the subscription
     * @param year Year of the start date
     * @param month Month of the start date
     * @param day Day of the month of the start date
     * @param charge Monthly charge of the subscription.
     * @param comment A comment about the subscription
     */
    public Subscription(String name, int year, int month, int day, float charge,
                        String comment){
        this.name = name;
        this.charge = charge;
        this.comment = null;
        this.date = new GregorianCalendar(year, month, day);
        this.comment = comment;
    }

    /**
     * Gets the name of this subscription
     * @return the name for this subscription
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of this subscription
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the year associated with this subscription's date.
     * @return the year of this subscription's start date
     */
    public int getYear() {
        return date.get(Calendar.YEAR);
    }

    /**
     * Set the year of this subscription's start date.
     * @param year new year to be set
     */
    public void setYear(int year) {
        date.set(Calendar.YEAR, year);
    }

    /**
     * Get the month associated with this subscription's date.
     * @return the month of this subscription's start date
     */
    public int getMonth() {
        return date.get(Calendar.MONTH);
    }

    /**
     * Set the month of this subscription's start date.
     * @param month new month to be set
     */
    public void setMonth(int month) {
        date.set(Calendar.MONTH, month);
    }

    /**
     * Return a string form of the month
     * @param month an integer from 0-11 representing a month
     * @return the string of that month. 0 -> January, 1 -> February, etc.
     */
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

    /**
     * Get the day associated with this subscription's date.
     * @return the day of this subscription's start date
     */
    public int getDay() {
        return date.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Set the day of this subscription's start date.
     * @param day new day to be set
     */
    public void setDay(int day) {
        date.set(Calendar.DAY_OF_MONTH, day);
    }

    /**
     * Get the charge associated with this subscription.
     * @return the charge of this subscription
     */
    public float getCharge() {
        return charge;
    }

    /**
     * Set the charge of this subscription.
     * @param charge new charge to be set
     */
    public void setCharge(float charge) {
        this.charge = charge;
    }

    /**
     * Get the comment associated with this subscription.
     * @return the comment of this subscription
     */
    public String getComment() {
        return comment;
    }

    /**
     * Set the comment of this subscription.
     * @param comment new comment to be set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Clone this subscription
     * @return a new Subscription with the same member variables.
     */
    @Override
    public Subscription clone(){
        return new Subscription(this.name, date.get(Calendar.YEAR),
                date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH),
                this.charge, this.comment);
    }
}
