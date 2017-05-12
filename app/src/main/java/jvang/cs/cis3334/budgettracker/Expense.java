package jvang.cs.cis3334.budgettracker;

import java.io.Serializable;

/**
 * Created by jvang5 on 5/3/2017.
 * This class represents an Expense. In this app an expense will contain 5 things: key (to be distinguishable),
 * type, amount, year and month. There are 3 constructors and the normal getters/setters for each variable as
 * well as a newly formatted toString() method.
 *
 */
public class Expense implements Serializable{
    //Declaring variables
    private String key;
    private String type;
    private Double amount;
    private String year;
    private String month;

    /**
     * The 3 methods below are constructors
     */
    public Expense(){
    }

    public Expense(String inYear, String inMonth){
        year = inYear;
        month = inMonth;
    }
    public Expense(String inKey, String inYear,  String inMonth, String inType, Double inAmount){
        key = inKey;
        type = inType;
        amount = inAmount;
        year = inYear;
        month = inMonth;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {return year; }

    public void setYear(String year) {this.year = year; }

    public String getMonth() {return month; }

    public void setMonth(String month) {this.month = month; }

    public String getKey() {
        return key;
    }


    /**
     * When an expense's toString is called on this is the format that it will take
     *
     * @return the newly formatted string
     */
    @Override
    public String toString(){
        return year+ " " + month + " " + type + " " + amount;
    }
}
