package jvang.cs.cis3334.budgettracker;

/**
 * Created by mat on 5/8/17.
 */
import java.util.Date;

public class Month {
    private Date today;// = new Date();
    private String month;

    public Month(){

    }

    public Month (Date inDate){
        today = inDate;
    }

    public Date getToday() {

        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }
    public String getYear(){
        today = new Date();

        month = today.toString();
        month = (month.substring(24,28));

        return month;
    }

    public String getMonth(){
        today = new Date();

        month = today.toString();
       // year = month.substring(24,28);

        if(month.contains("Jan")) {
            month = "January";
        }
        else if(month.contains("Feb")) {
            month = "February";
        }
        else if(month.contains("Mar")) {
            month = "March";
        }
        else if(month.contains("Apr")) {
            month = "April";
        }
        else if(month.contains("May")) {
            month = "May";
        }
        else if(month.contains("Jun")) {
            month = "June";
        }
        else if(month.contains("Jul")) {
            month = "July";
        }
        else if(month.contains("Aug")) {
            month = "August";
        }
        else if(month.contains("Sep")) {
            month = "September";
        }
        else if(month.contains("Oct")) {
            month = "October";
        }
        else if(month.contains("Nov")) {
            month = "Novemeber";
        }
        else if(month.contains("Dec")) {
            month = "December";
        }

        return month;
    }
}
