package jvang.cs.cis3334.budgettracker;

import java.util.Date;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MonthlyData extends AppCompatActivity {
    TextView month;
    Date today = new Date();
    String mnth;
    String monthHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_data);

        month = (TextView) findViewById(R.id.textViewMonth);

        mnth = today.toString();

        if(mnth.contains("Jan")) {
            monthHolder = "January";
        }
        else if(mnth.contains("Feb")) {
            monthHolder = "February";
        }
        else if(mnth.contains("Mar")) {
            monthHolder = "March";
        }
        else if(mnth.contains("Apr")) {
            monthHolder = "April";
        }
        else if(mnth.contains("May")) {
            monthHolder = "May";
        }
        else if(mnth.contains("Jun")) {
            monthHolder = "June";
        }
        else if(mnth.contains("Jul")) {
            monthHolder = "July";
        }
        else if(mnth.contains("Aug")) {
            monthHolder = "August";
        }
        else if(mnth.contains("Sep")) {
            monthHolder = "September";
        }
        else if(mnth.contains("Oct")) {
            monthHolder = "October";
        }
        else if(mnth.contains("Nov")) {
            monthHolder = "Novemeber";
        }
        else if(mnth.contains("Dec")) {
            monthHolder = "December";
        }
        month.setText(monthHolder);
    }
}
