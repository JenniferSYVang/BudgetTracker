package jvang.cs.cis3334.budgettracker;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvang5 on 5/3/2017.
 */

public class MonthlyFirebaseData {
    DatabaseReference dBRef;
    public String month;
    public String year = "2017";

    public DatabaseReference open(AppCompatActivity MonthlyData)  {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dBRef = database.getReference(year);
        return dBRef;
    }

    public void close() {

    }

   public Expense createExpense(String year, String month, String type, Double amount) {
        // ---- Get a new database key for the amount
        String key = dBRef.push().getKey();
        // ---- set up new expense
       Expense newExpense = new Expense(key, year, month, type, amount);
        // ---- write the expense to Firebase
        dBRef.child(year).child(month).child(type).child(key).setValue(amount);
        return newExpense;
   }

   /* public void deleteExpense(Expense inExpense) {
        String key = inExpense.getKey();
        dBRef.child(inExpense.getYear()).child(inExpense.getMonth()).child(inExpense.getType()).removeValue();
    }*/

   public List<Expense> getAllExpenses(DataSnapshot dataSnapshot) {
        List<Expense> expenseList = new ArrayList<Expense>();
        // loop only over the expenses
        for (DataSnapshot data : dataSnapshot.getChildren()) {
            String key = data.getKey();
            Expense expense = data.getValue(Expense.class);
            expense.setKey(key);
            expenseList.add(expense);
        }
        return expenseList;
   }
}
