package jvang.cs.cis3334.budgettracker;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

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
        dBRef = database.getReference();
        return dBRef;
    }

    public void close() {

    }

   public Expense createExpense(String year, String month, String type, Double amount) {
        // ---- Get a new database key for the amount
        String key = dBRef.child(year).push().getKey();
        // ---- set up new expense
       Expense newExpense = new Expense(key, year, month, type, amount);
        // ---- write the expense to Firebase
       dBRef.child(year).child(key).setValue(newExpense);
   //     dBRef.child(year).child(month).child(type).child(key).setValue(newExpense);
        return newExpense;
   }

   /* public void deleteExpense(Expense inExpense) {
        String key = inExpense.getKey();
        dBRef.child(inExpense.getYear()).child(inExpense.getMonth()).child(inExpense.getType()).removeValue();
    }*/

   public List<Expense> getAllExpenses(DataSnapshot dataSnapshot, String inSpinnerSelection) { /// pass in month category
        List<Expense> expenseList = new ArrayList<Expense>();
        // loop only over the expenses

       //for (DataSnapshot data : dataSnapshot.child(year).child("May").child("Clothes").getChildren()) {
       for (DataSnapshot data : dataSnapshot.child(year).getChildren()) {
            String key = data.getKey();
            Expense expense = data.getValue(Expense.class);

           if(inSpinnerSelection.equalsIgnoreCase("Fun")){
               if(expense.getType().equalsIgnoreCase("Fun")){
                   expenseList.add(expense);
               }
           }
           else if(inSpinnerSelection.equalsIgnoreCase("Dining Out")){
               if(expense.getType().equalsIgnoreCase("Dining Out")){
                   expenseList.add(expense);
               }
           }
           else if(inSpinnerSelection.equalsIgnoreCase("Clothes")){
               if(expense.getType().equalsIgnoreCase("Clothes")){
                   expenseList.add(expense);
               }
           }
           else if(inSpinnerSelection.equalsIgnoreCase("Miscellaneous")){
               if(expense.getType().equalsIgnoreCase("Miscellaneous")){
                   expenseList.add(expense);
               }
           }
           else if(inSpinnerSelection.equalsIgnoreCase("Gas")){
               if(expense.getType().equalsIgnoreCase("Gas")){
                   expenseList.add(expense);
               }
           }
           else if(inSpinnerSelection.equalsIgnoreCase("Groceries")){
               if(expense.getType().equalsIgnoreCase("Groceries")){
                   expenseList.add(expense);
               }
           }
           else if(inSpinnerSelection.equalsIgnoreCase("Cellphone")){
               if(expense.getType().equalsIgnoreCase("Cellphone")){
                   expenseList.add(expense);
               }
           }
           else if(inSpinnerSelection.equalsIgnoreCase("Savings")){
               if(expense.getType().equalsIgnoreCase("Savings")){
                   expenseList.add(expense);
               }
           }
           else if(inSpinnerSelection.equalsIgnoreCase("Rent")){
               if(expense.getType().equalsIgnoreCase("Rent")){
                   expenseList.add(expense);
               }
           }
           else if(inSpinnerSelection.equalsIgnoreCase("Utilities")){
               if(expense.getType().equalsIgnoreCase("Utilities")){
                   expenseList.add(expense);
               }
           }
           else{
               expenseList.add(expense);
           }

        }
        return expenseList;
   }

    public DatabaseReference open(View.OnClickListener onClickListener) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dBRef = database.getReference();
        return dBRef;
    }
}
