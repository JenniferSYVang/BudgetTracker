package jvang.cs.cis3334.budgettracker;

/**
 * Created by jvang5 on 5/8/17.
 * This class just creates a Date that represents today. From this date we will then be able to extract the month and year that we need.
 */
import java.util.Date;

public class Month {
    private Date today;// = new Date();
    private String month;

    public Month(){
        today = new Date();
    }

    /**
     * The Date object today is in the form of HH:MM:SS MTH YEAR. Therefore, we need to get the year out of that String format.
     * @return the year - 4 digits long
     */
    public String getYear(){
        today = new Date();

        month = today.toString();
        month = (month.substring(24,28));

        return month;
    }

    /**
     * The Date object today is in the form of HH:MM:SS MTH YEAR. Therefore, we need to get the month out of that String format. After
     * obtaining the 3 Characters that represents the month we just need to spell the month out and return it.
     * @return the month - 3 characters long
     */
    public String getMonth(){
        today = new Date();
        month = today.toString();

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
