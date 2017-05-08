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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MonthlyData extends AppCompatActivity {
    TextView monthTV;
    Button displayBtn, newBtn, deleteBtn;
    Spinner spinnerOptions;
    String options;
    MonthlyFirebaseData dataSource;
    DatabaseReference myDB;
    ListView showInfo;
    List<Expense> expenseList;
    Expense expense;
    ExpenseAdapter expAdapter;

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
        //deleteBtn = (Button) findViewById(R.id.buttonDelete);

        // get expense passed from previous activity
        Bundle extras = getIntent().getExtras();
        expense = (Expense) extras.getSerializable("Expense");


        setupFirebaseDataChange(spinnerOptions.getSelectedItem().toString());

        // display current month and year
        monthTV.setText(expense.getMonth() + " " + expense.getYear());

        //show the expense that was sent from previous activity
        // displayTV.setText(expense.toString());


        displayBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                options = spinnerOptions.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), "You selected " + options, Toast.LENGTH_LONG)
                        .show();

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

                //displayTV.setText(myDB.toString());

            }
        });

        newBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent actMonthlyData = new Intent(MonthlyData.this, MainActivity.class);
                startActivity(actMonthlyData);
            }
        });

        /*deleteBtn.setOnClickListener(new View.OnClickListener() {
            Expense expense;
            public void onClick(View v) {
                ///////////////////
                dataSource = new MonthlyFirebaseData();
                myDB = dataSource.open(this);

                showInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapter, View parent,
                                            int position, long id) {
                        Toast.makeText(getApplicationContext(), parent.toString(), Toast.LENGTH_LONG)
                                .show();


                    }
                });

            }
        });*/
    }

    private void setupFirebaseDataChange(final String spinnerSelectionA) {
        dataSource = new MonthlyFirebaseData();
        myDB = dataSource.open(this);

        myDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //spinnerSelection = "all";
                //Log.d("CIS3334", "Starting onDataChange()");        // debugging log
                expenseList = dataSource.getAllExpenses(dataSnapshot, spinnerSelectionA);
                // Instantiate a custom adapter for displaying each expense
                expAdapter = new ExpenseAdapter(getApplicationContext(), android.R.layout.simple_list_item_single_choice, expenseList);
                // Apply the adapter to the list
                showInfo.setAdapter(expAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.d("CIS3334", "onCancelled: ");
            }
        });
    }


}
