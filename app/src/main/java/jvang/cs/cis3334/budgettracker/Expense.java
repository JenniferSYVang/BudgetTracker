package jvang.cs.cis3334.budgettracker;

import java.io.Serializable;

/**
 * Created by jvang5 on 5/3/2017.
 */

public class Expense implements Serializable{
    private String type;
    private Double amount;

    public Expense(){
        type = "";
        amount = 0.0;
    }

    public Expense(String inType, Double inAmount){
        type = inType;
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

    @Override
    public String toString(){
        return "The expense entered was: " + type + " = " + amount;
    }

}
