package jvang.cs.cis3334.budgettracker;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jvang5 on 5/3/2017.
 * This represents the Database that we will use to link with the firebase console. The information exchange will be in realtime.
 * Therefore, if an expense is added on the app it will be seen on the firebase console right away.
 */
public class MonthlyFirebaseData {
    // Declare variables
    DatabaseReference dBRef;
    public String year = "2017";
    public Double total;
    public Month today;

    /**
     * Opens the DB up to be able to read/write
     * @param MonthlyData   The activity that is being passed in
     * @return  returns a reference to this DB
     */
    public DatabaseReference open(AppCompatActivity MonthlyData)  {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dBRef = database.getReference();
        return dBRef;
    }

    public void close() {}

    /**
     * This method creates a new new expense with all the variables available EXCLUDING the key identifier.
     *
     * @param year      year of the expense
     * @param month     month of the expense
     * @param type      type of the expense
     * @param amount    amount of the expense
     * @return          return a new expense with a key as well
     */
   public Expense createExpense(String year, String month, String type, Double amount) {
       String key = dBRef.child(year).push().getKey();// ---- Get a new database key for the amount
       Expense newExpense = new Expense(key, year, month, type, amount);// ---- set up new expense
       dBRef.child(year).child(key).setValue(newExpense);// ---- write the expense to Firebase
        return newExpense;
   }

    /**
     * This method will return the total amount the user has spent in the month
     * @param dataSnapshot  what the DB currently looks like
     * @return      returns the total monthly spendings
     */
   public Double getTotalSpendings(DataSnapshot dataSnapshot){
       total = 0.0;
       for(DataSnapshot data : dataSnapshot.child(year).getChildren()){
           String key = data.getKey();
           Expense expense = data.getValue(Expense.class);
           total += expense.getAmount();
       }
       return total;
   }

    /**
     * This method will return all the expense, in a list, that the user has entered or a certain type of expense depending on what the spinnerSelection is
     *
     * @param dataSnapshot          what the DB looks like in that moment
     * @param inSpinnerSelection    the spinner selection or what certain type of expense did the user want to look at?
     * @return  returns the list of the specified expense(s)
     */
   public List<Expense> getAllExpenses(DataSnapshot dataSnapshot, String inSpinnerSelection) { /// pass in month category
        List<Expense> expenseList = new ArrayList<Expense>();
       today = new Month();
        // loop only over the expenses
       for (DataSnapshot data : dataSnapshot.child(year).getChildren()) {
            Expense expense = data.getValue(Expense.class);

           //determine what kind of list to create ... show all, fun, gas, etc.
           if(inSpinnerSelection.equalsIgnoreCase("Fun")){
               if(expense.getType().equalsIgnoreCase("Fun") && expense.getMonth().equalsIgnoreCase(today.getMonth())){
                   expenseList.add(expense);
               }
           }
           else if(inSpinnerSelection.equalsIgnoreCase("Dining Out") && expense.getMonth().equalsIgnoreCase(today.getMonth())){
               if(expense.getType().equalsIgnoreCase("Dining Out")){
                   expenseList.add(expense);
               }
           }
           else if(inSpinnerSelection.equalsIgnoreCase("Clothes") && expense.getMonth().equalsIgnoreCase(today.getMonth())){
               if(expense.getType().equalsIgnoreCase("Clothes")){
                   expenseList.add(expense);
               }
           }
           else if(inSpinnerSelection.equalsIgnoreCase("Miscellaneous")&& expense.getMonth().equalsIgnoreCase(today.getMonth())){
               if(expense.getType().equalsIgnoreCase("Miscellaneous")){
                   expenseList.add(expense);
               }
           }
           else if(inSpinnerSelection.equalsIgnoreCase("Gas") && expense.getMonth().equalsIgnoreCase(today.getMonth())){
               if(expense.getType().equalsIgnoreCase("Gas")){
                   expenseList.add(expense);
               }
           }
           else if(inSpinnerSelection.equalsIgnoreCase("Groceries") && expense.getMonth().equalsIgnoreCase(today.getMonth())){
               if(expense.getType().equalsIgnoreCase("Groceries")){
                   expenseList.add(expense);
               }
           }
           else if(inSpinnerSelection.equalsIgnoreCase("Cellphone") && expense.getMonth().equalsIgnoreCase(today.getMonth())){
               if(expense.getType().equalsIgnoreCase("Cellphone")){
                   expenseList.add(expense);
               }
           }
           else if(inSpinnerSelection.equalsIgnoreCase("Savings") && expense.getMonth().equalsIgnoreCase(today.getMonth())){
               if(expense.getType().equalsIgnoreCase("Savings")){
                   expenseList.add(expense);
               }
           }
           else if(inSpinnerSelection.equalsIgnoreCase("Rent")  && expense.getMonth().equalsIgnoreCase(today.getMonth())){
               if(expense.getType().equalsIgnoreCase("Rent")){
                   expenseList.add(expense);
               }
           }
           else if(inSpinnerSelection.equalsIgnoreCase("Utilities") && expense.getMonth().equalsIgnoreCase(today.getMonth())){
               if(expense.getType().equalsIgnoreCase("Utilities")){
                   expenseList.add(expense);
               }
           }
           else if(inSpinnerSelection.equalsIgnoreCase("Show All") && expense.getMonth().equalsIgnoreCase(today.getMonth())){
               expenseList.add(expense);
           }
        }
        return expenseList;
   }

    /**
     * this method will open the DBreference so that it can be read/written from
     * @param onClickListener   waits until it is called on
     * @return      return a reference to the DB
     */
    public DatabaseReference open(View.OnClickListener onClickListener) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dBRef = database.getReference();
        return dBRef;
    }

    /**
     * This method will remove the specified expense from the DB
     * @param expense   the expense that was selected to be removed
     */
    public void deleteExpense(Expense expense) {
        String key = expense.getKey();
        dBRef.child(year).child(key).removeValue();
    }
}
