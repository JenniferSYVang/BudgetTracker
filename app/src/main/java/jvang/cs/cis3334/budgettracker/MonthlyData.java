package jvang.cs.cis3334.budgettracker;

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
    TextView monthTV, displayTV;
    Button displayBtn;
    Spinner spinnerOptions;
    String options;
    MonthlyFirebaseData dataSource;
    DatabaseReference myDB;
    ListView showInfo;
    List<Expense> expenseList;
    int index;
    Expense selectedExpense;
    ExpenseAdapter expAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_data);

        // linking widegts to variables
        monthTV = (TextView) findViewById(R.id.textViewMonth);
        displayTV = (TextView) findViewById(R.id.textViewDisplay);
        displayBtn = (Button) findViewById(R.id.buttonView);
        spinnerOptions = (Spinner) findViewById(R.id.spinnerExpenses);

        // get expense passed from previous activity
        Bundle extras = getIntent().getExtras();
        Expense expense = (Expense) extras.getSerializable("Expense");

        setupFirebaseDataChange();

        // display current month and year
        monthTV.setText(expense.getMonth() + " " + expense.getYear());

        //show the expense that was sent from previous activity
        displayTV.setText(expense.toString());

        displayBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                options = spinnerOptions.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), "You selected " + options, Toast.LENGTH_LONG)
                      .show();
                displayTV.setText(myDB.toString());
            }
        });
    }

    private void setupFirebaseDataChange() {
        dataSource = new MonthlyFirebaseData();
        myDB = dataSource.open(this);
        myDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.d("CIS3334", "Starting onDataChange()");        // debugging log
                expenseList = dataSource.getAllExpenses(dataSnapshot);
                // Instantiate a custom adapter for displaying each expense
                expAdapter = new ExpenseAdapter(MonthlyData.this, android.R.layout.simple_list_item_single_choice, expenseList);
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
