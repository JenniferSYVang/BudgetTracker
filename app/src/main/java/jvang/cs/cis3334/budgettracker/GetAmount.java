package jvang.cs.cis3334.budgettracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class GetAmount extends AppCompatActivity {

    // Declare Variables
    TextView title;
    EditText amount;
    Button save;
    String btn;
    Expense expense;
    Double amountDbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_amount);


        Bundle extras = getIntent().getExtras();
        btn = extras.getString("Categories");

        // Linking variables with widgets
        title = (TextView) findViewById(R.id.textViewAmount);
        amount = (EditText) findViewById(R.id.editTextAmount);
        save = (Button) findViewById(R.id.buttonSave);

        title.setText("Amount spent on " + btn.toUpperCase() + ": $");

        amountDbl = Double.parseDouble(amount.getText().toString());

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (btn.equalsIgnoreCase("Fun")) {
                    Toast.makeText(getApplicationContext(), "Fun amount = $" + amountDbl, Toast.LENGTH_LONG)
                            .show();
                    expense.setType("Fun");
                    expense.setAmount(amountDbl);
                }
                else if(btn.equalsIgnoreCase("Dining Out")){
                    Toast.makeText(getApplicationContext(), "Dining amount = $" + amountDbl, Toast.LENGTH_LONG)
                            .show();
                    expense.setType("Dining Out");
                    expense.setAmount(amountDbl);
                }
                else if(btn.equalsIgnoreCase("Clothes")){
                    Toast.makeText(getApplicationContext(), "Clothes amount = $" + amountDbl, Toast.LENGTH_LONG)
                            .show();
                    expense.setType("Clothes");
                    expense.setAmount(amountDbl);
                }
                else if(btn.equalsIgnoreCase("Miscellaneous")){
                    Toast.makeText(getApplicationContext(), "Miselleanous amount = $" + amountDbl, Toast.LENGTH_LONG)
                            .show();
                    expense.setType("Miscellaneous");
                    expense.setAmount(amountDbl);
                }
                else if(btn.equalsIgnoreCase("Gas")){
                    Toast.makeText(getApplicationContext(), "Gas amount = $" + amountDbl, Toast.LENGTH_LONG)
                            .show();
                    expense.setType("Gas");
                    expense.setAmount(amountDbl);
                }
                else if(btn.equalsIgnoreCase("Groceries")){
                    Toast.makeText(getApplicationContext(), "Groceries amount = $" + amountDbl, Toast.LENGTH_LONG)
                            .show();
                    expense.setType("Groceries");
                    expense.setAmount(amountDbl);
                }
                else if(btn.equalsIgnoreCase("Cellphone")){
                    Toast.makeText(getApplicationContext(), "Cell amount = $" + amountDbl, Toast.LENGTH_LONG)
                            .show();
                    expense.setType("Cellphone");
                    expense.setAmount(amountDbl);
                }
                else if(btn.equalsIgnoreCase("Savings")){
                    Toast.makeText(getApplicationContext(), "Savings amount = $" + amountDbl, Toast.LENGTH_LONG)
                            .show();
                    expense.setType("Savings");
                    expense.setAmount(amountDbl);
                }
                else if(btn.equalsIgnoreCase("Rent/Mortgage")){
                    Toast.makeText(getApplicationContext(), "Rent amount = $" + amountDbl, Toast.LENGTH_LONG)
                            .show();
                    expense.setType("Rent/Mortgage");
                    expense.setAmount(amountDbl);
                }
                else if(btn.equalsIgnoreCase("Utilities")){
                    Toast.makeText(getApplicationContext(), "Utilities amount = $" + amountDbl, Toast.LENGTH_LONG)
                            .show();
                    expense.setType("Utilities");
                    expense.setAmount(amountDbl);
                }
                else {
                    Toast.makeText(getApplicationContext(), "ERROR! Something happened that wasn't suppose to. ", Toast.LENGTH_LONG)
                            .show();
                }

                Intent actMonthlyData = new Intent(GetAmount.this, MonthlyData.class);
                actMonthlyData.putExtra("Expense", expense);
                startActivity(actMonthlyData);
            }
        });

    }
}
