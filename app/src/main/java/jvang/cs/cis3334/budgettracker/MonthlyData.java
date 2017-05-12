package jvang.cs.cis3334.budgettracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.List;

/**
 * This is the final Activity in the app. It displays all the information that was entered by the user.
 * There is a spinner available that will let the user narrow down the list to a certain expense or show all of them.
 * If a certain expense is selected then the total amount of that will certain expense will show up as well as the
 * percentage it took out of all the monthly expenses added up together. This is also where the listView contents
 * will be set and maintained as expenses are getting added or subtracted from the month.
 */
public class MonthlyData extends AppCompatActivity {
    TextView monthTV, extraInfoTV;
    Button displayBtn, newBtn, deleteBtn;
    Spinner spinnerOptions;
    String options;
    MonthlyFirebaseData dataSource;
    DatabaseReference myDB;
    ListView showInfo;
    List<Expense> expenseList;
    Expense expense, expenseGetTotal;
    ExpenseAdapter expAdapter;
    Double total;

    /**
     * This is method determines what the screen will look like when it loads up initially. It also sets up our listView
     * and buttons onClickListeners.
     *
     * @param savedInstanceState  The state this activity was last in is saved and loaded on intialization of the page
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_data);

        // linking widegts to variables
        monthTV = (TextView) findViewById(R.id.textViewMonth);
        displayBtn = (Button) findViewById(R.id.buttonView);
        spinnerOptions = (Spinner) findViewById(R.id.spinnerExpenses);
        showInfo = (ListView) findViewById(R.id.showInfoLV);
        newBtn = (Button) findViewById(R.id.buttonNew);
        deleteBtn = (Button) findViewById(R.id.buttonDelete);
        extraInfoTV = (TextView) findViewById(R.id.textViewExtraInfo);
        setupFirebaseDataChange(spinnerOptions.getSelectedItem().toString());

        // get expense passed from previous activity
        Bundle extras = getIntent().getExtras();
        expense = (Expense) extras.getSerializable("Expense");

        monthTV.setText(expense.getMonth() + " " + expense.getYear());  // display current month and year

        /**
         * This button is set to change the list depending on what the user has selected in the spinner. If the user picked "Fun"
         * it is only going to show the "Fun" expenses.
         */
        displayBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                total = 0.0;
                options = spinnerOptions.getSelectedItem().toString();

                Toast.makeText(getApplicationContext(), "You selected " + options, Toast.LENGTH_LONG)
                        .show(); // to inform the user of what they selected
                if (options.equalsIgnoreCase("Fun")) {
                    setupFirebaseDataChange("Fun");
                } else if (options.equalsIgnoreCase("Dining Out")) {
                    setupFirebaseDataChange("Dining Out");
                } else if (options.equalsIgnoreCase("Clothes")) {
                    setupFirebaseDataChange("Clothes");
                } else if (options.equalsIgnoreCase("Miscellaneous")) {
                    setupFirebaseDataChange("Miscellaneous");
                } else if (options.equalsIgnoreCase("Gas")) {
                    setupFirebaseDataChange("Gas");
                } else if (options.equalsIgnoreCase("Groceries")) {
                    setupFirebaseDataChange("Groceries");
                } else if (options.equalsIgnoreCase("Cellphone")) {
                    setupFirebaseDataChange("Cellphone");
                } else if (options.equalsIgnoreCase("Savings")) {
                    setupFirebaseDataChange("Savings");
                } else if (options.equalsIgnoreCase("Rent")) {
                    setupFirebaseDataChange("Rent");
                } else if (options.equalsIgnoreCase("Utilities")) {
                    setupFirebaseDataChange("Utilities");
                } else {
                    setupFirebaseDataChange("Show All");
                }
            }
        });

        /**
         * if the user clicks on the "Enter new Expense" then they will be re-routed back to the first Activity with the buttons.
         */
        newBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent actMonthlyData = new Intent(MonthlyData.this, MainActivity.class);
                startActivity(actMonthlyData);
            }
        });

        /**
         * if the deleteBtn is clicked then the user will be able to select a menu from the list. The item that is selected is deleted
         * automatically from the list. If the user wants to delete another expense they will have to press on the deleteBtn again.
         */
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dataSource = new MonthlyFirebaseData();
                myDB = dataSource.open(this);

                /**
                 * This is the code that will actually delete the expense from the list and database
                 */
                showInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapter, View parent,
                                            int position, long id) {
                        Toast.makeText(getApplicationContext(), "You deleted " + expenseList.get(position).toString(), Toast.LENGTH_LONG)
                                .show(); // to show the user which expense they deleted
                        dataSource.deleteExpense(expenseList.get(position));
                        expAdapter.remove(expenseList.get(position));
                        expAdapter.notifyDataSetChanged();
                        showInfo.setOnItemClickListener(null);
                    }
                });
            }
        });
    }

    /**
     * This is to keep the list in the listView updated as new Expenses are getting added and deleted. Therefore any changes made
     * will be seen in realtime for the user.
     *
     * @param spinnerSelectionA    this String represents the expense the user wanted to see whether it was all the expense for the month or a
     *                             specific expense with the percentage this expense takes up when compared with all the other expenses
     */
    private void setupFirebaseDataChange(final String spinnerSelectionA) {
        dataSource = new MonthlyFirebaseData();
        myDB = dataSource.open(this);

        myDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //spinnerSelection = "all";
                //Log.d("CIS3334", "Starting onDataChange()");        // debugging log
                total = 0.0;
                expenseList = dataSource.getAllExpenses(dataSnapshot, spinnerSelectionA);
                for (int i = 0; i < expenseList.size(); i++) {
                    expenseGetTotal = expenseList.get(i);
                    total += expenseGetTotal.getAmount();
                }
                total = Double.parseDouble(new DecimalFormat("##.##").format(total));
                if (spinnerSelectionA.equalsIgnoreCase("Utilities")) {
                    extraInfoTV.setText("Total Utilities Spendings = " + String.valueOf(total) + "  =  " + String.valueOf(new DecimalFormat("##.##").format(total / dataSource.getTotalSpendings(dataSnapshot) * 100)) + "%");
                } else if (spinnerSelectionA.equalsIgnoreCase("Fun")) {
                    extraInfoTV.setText("Total FUN for the Month = " + String.valueOf(total) + "  =  " + String.valueOf(new DecimalFormat("##.##").format(total / dataSource.getTotalSpendings(dataSnapshot) * 100)) + "%");
                } else if (spinnerSelectionA.equalsIgnoreCase("Dining Out")) {
                    extraInfoTV.setText("Total DINING OUT for the Month = " + String.valueOf(total) + "  =  " + String.valueOf(new DecimalFormat("##.##").format(total / dataSource.getTotalSpendings(dataSnapshot) * 100)) + "%");
                } else if (spinnerSelectionA.equalsIgnoreCase("Miscellaneous")) {
                    extraInfoTV.setText("Total MISCELLANEOUS for the month = " + String.valueOf(total) + "  =  " + String.valueOf(new DecimalFormat("##.##").format(total / dataSource.getTotalSpendings(dataSnapshot) * 100)) + "%");
                } else if (spinnerSelectionA.equalsIgnoreCase("Gas")) {
                    extraInfoTV.setText("Total GAS for the Month = " + String.valueOf(total) + "  =  " + String.valueOf(new DecimalFormat("##.##").format(total / dataSource.getTotalSpendings(dataSnapshot) * 100)) + "%");
                } else if (spinnerSelectionA.equalsIgnoreCase("Clothes")) {
                    extraInfoTV.setText("Total CLOTHES for the Month = " + String.valueOf(total) + "  =  " + String.valueOf(new DecimalFormat("##.##").format(total / dataSource.getTotalSpendings(dataSnapshot) * 100)) + "%");
                } else if (spinnerSelectionA.equalsIgnoreCase("Groceries")) {
                    extraInfoTV.setText("Total GROCERIES for the Month" + String.valueOf(total) + "  =  " + String.valueOf(new DecimalFormat("##.##").format(total / dataSource.getTotalSpendings(dataSnapshot) * 100)) + "%");
                } else if (spinnerSelectionA.equalsIgnoreCase("Cellphone")) {
                    extraInfoTV.setText("Total CELLPHONE for the Month" + String.valueOf(total) + "  =  " + String.valueOf(new DecimalFormat("##.##").format(total / dataSource.getTotalSpendings(dataSnapshot) * 100)) + "%");
                } else if (spinnerSelectionA.equalsIgnoreCase("Savings")) {
                    extraInfoTV.setText("Total SAVINGS for the Month" + String.valueOf(total) + "  =  " + String.valueOf(new DecimalFormat("##.##").format(total / dataSource.getTotalSpendings(dataSnapshot) * 100)) + "%");
                } else if (spinnerSelectionA.equalsIgnoreCase("Rent")) {
                    extraInfoTV.setText("Total Rent for the Month  =  " + String.valueOf(total) + "  =  " + String.valueOf(new DecimalFormat("##.##").format(total / dataSource.getTotalSpendings(dataSnapshot) * 100)) + "%");
                } else {
                    extraInfoTV.setText("Total SPENDINGS for the Month = " + String.valueOf(total));
                }

                expAdapter = new ExpenseAdapter(getApplicationContext(), android.R.layout.simple_list_item_single_choice, expenseList);     // Instantiate a custom adapter for displaying each expense
                showInfo.setAdapter(expAdapter);// Apply the adapter to the list
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
