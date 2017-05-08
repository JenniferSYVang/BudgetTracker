package jvang.cs.cis3334.budgettracker;

import java.util.Date;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class GetAmount extends AppCompatActivity {

    // Declare Variables
    TextView title;
    EditText amount;
    Button save;
    String btn, mnth, year, monthHolder;
    Expense expense;
    Double amountDbl;
    Date today;
    DatabaseReference myDB;
    MonthlyFirebaseData myRef;
    Month date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_amount);

        date = new Month();
        // Declaring variables
        expense = new Expense();
        Bundle extras = getIntent().getExtras();
        btn = extras.getString("Categories");

        myRef = new MonthlyFirebaseData();
        myDB = myRef.open(this);

        // Linking variables with widgets
        title = (TextView) findViewById(R.id.textViewAmount);
        amount = (EditText) findViewById(R.id.editTextAmount);
        save = (Button) findViewById(R.id.buttonSave);

        title.setText("Amount spent on " + btn.toUpperCase() + ": $");

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                amountDbl = Double.parseDouble(amount.getText().toString());
                if (btn.equalsIgnoreCase("Fun")) {
                    expense.setType("Fun");
                    expense.setAmount(amountDbl);
                }
                else if(btn.equalsIgnoreCase("Dining Out")){
                    expense.setType("Dining Out");
                    expense.setAmount(amountDbl);
                }
                else if(btn.equalsIgnoreCase("Clothes")){
                    expense.setType("Clothes");
                    expense.setAmount(amountDbl);
                }
                else if(btn.equalsIgnoreCase("Miscellaneous")){
                    expense.setType("Miscellaneous");
                    expense.setAmount(amountDbl);
                }
                else if(btn.equalsIgnoreCase("Gas")){
                    expense.setType("Gas");
                    expense.setAmount(amountDbl);
                }
                else if(btn.equalsIgnoreCase("Groceries")){
                    expense.setType("Groceries");
                    expense.setAmount(amountDbl);
                }
                else if(btn.equalsIgnoreCase("Cellphone")){
                    expense.setType("Cellphone");
                    expense.setAmount(amountDbl);
                }
                else if(btn.equalsIgnoreCase("Savings")){
                    expense.setType("Savings");
                    expense.setAmount(amountDbl);
                }
                else if(btn.equalsIgnoreCase("Rent")){
                    expense.setType("Rent");
                    expense.setAmount(amountDbl);
                }
                else if(btn.equalsIgnoreCase("Utilities")){
                    expense.setType("Utilities");
                    expense.setAmount(amountDbl);
                }
                else {
                    Toast.makeText(getApplicationContext(), "ERROR! Something happened that wasn't suppose to. ", Toast.LENGTH_LONG)
                            .show();
                }

                year = date.getYear();
                monthHolder = date.getMonth();
                myRef.createExpense(year, monthHolder, expense.getType(), amountDbl);
                expense.setMonth(monthHolder);
                expense.setYear(year);

                // Pass the expense to the activity
                Intent actMonthlyData = new Intent(GetAmount.this, MonthlyData.class);
                actMonthlyData.putExtra("Expense", expense);
                startActivity(actMonthlyData);
            }
        });

    }
}
