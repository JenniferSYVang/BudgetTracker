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

/**
 * This represents the activity that will get the expense amount from the user. It is a simple layout
 * that just had a textView, button and editText.
 */
public class GetAmount extends AppCompatActivity {

    // Declare Variables
    TextView title;
    EditText amount;
    Button save;
    String btn,year, month;
    Expense expense;
    Double amountDbl;
    DatabaseReference myDB;
    MonthlyFirebaseData myRef;
    Month date;

    /**
     * This determines what the screen will look like when it starts up. It displays a message that that prompts the user to enter in an amount while
     * showing the expense that was selected as well -> Amount spent on GAS.
     *
     * @param savedInstanceState the last state that this activity was in
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_amount);

        // Declaring variables
        date = new Month();
        expense = new Expense();
        Bundle extras = getIntent().getExtras();
        btn = extras.getString(MainActivity.CATEGORIES);
        myRef = new MonthlyFirebaseData();
        myDB = myRef.open(this);

        // Linking variables with widgets
        title = (TextView) findViewById(R.id.textViewAmount);
        amount = (EditText) findViewById(R.id.editTextAmount);
        save = (Button) findViewById(R.id.buttonSave);

        title.setText("Amount spent on " + btn.toUpperCase() + ": $"); // prompt

        /**
         * This method controls what happens when the save button is clicked. If there is no input then the screen won't move on to the next screen.
         * However, if there is an input then a whole expense will be created, with all 5 variables, and then this expense will be passes to the last
         * activity, MonthlyData.
         */
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!amount.getText().toString().equalsIgnoreCase("")) {
                    amountDbl = Double.parseDouble(amount.getText().toString());
                    if (btn.equalsIgnoreCase("Fun")) {
                        expense.setType("Fun");
                        expense.setAmount(amountDbl);
                    } else if (btn.equalsIgnoreCase("Dining Out")) {
                        expense.setType("Dining Out");
                        expense.setAmount(amountDbl);
                    } else if (btn.equalsIgnoreCase("Clothes")) {
                        expense.setType("Clothes");
                        expense.setAmount(amountDbl);
                    } else if (btn.equalsIgnoreCase("Miscellaneous")) {
                        expense.setType("Miscellaneous");
                        expense.setAmount(amountDbl);
                    } else if (btn.equalsIgnoreCase("Gas")) {
                        expense.setType("Gas");
                        expense.setAmount(amountDbl);
                    } else if (btn.equalsIgnoreCase("Groceries")) {
                        expense.setType("Groceries");
                        expense.setAmount(amountDbl);
                    } else if (btn.equalsIgnoreCase("Cellphone")) {
                        expense.setType("Cellphone");
                        expense.setAmount(amountDbl);
                    }else if (btn.equalsIgnoreCase("Savings")) {
                        expense.setType("Savings");
                        expense.setAmount(amountDbl);
                    } else if (btn.equalsIgnoreCase("Rent")) {
                        expense.setType("Rent");
                        expense.setAmount(amountDbl);
                    } else if (btn.equalsIgnoreCase("Utilities")) {
                        expense.setType("Utilities");
                        expense.setAmount(amountDbl);
                    } else {
                        Toast.makeText(getApplicationContext(), "ERROR! Something happened that wasn't suppose to. ", Toast.LENGTH_LONG)
                            .show();
                    }
                    year = date.getYear();
                    month = date.getMonth();
                    myRef.createExpense(year, month, expense.getType(), amountDbl);
                    expense.setMonth(month);
                    expense.setYear(year);

                    // Pass the expense to the next activity
                    Intent actMonthlyData = new Intent(GetAmount.this, MonthlyData.class);
                    actMonthlyData.putExtra("Expense", expense);
                    startActivity(actMonthlyData);
                }
            }
        });
    }
}
