package jvang.cs.cis3334.budgettracker;

import java.io.Serializable;

/**
 * Created by jvang5 on 5/3/2017.
 */

public class Expense implements Serializable{
    private String key;
    private String type;
    private Double amount;
    private String year;
    private String month;

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
    public Expense(Double inAmount){
        amount = inAmount;
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

    public void setKey(String key){this.key = key; }


    @Override
    public String toString(){
        return year+ " " + month + " " + type + " " + amount;
    }
}
