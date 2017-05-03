package jvang.cs.cis3334.budgettracker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by jvang5 on 5/3/2017.
 */

public class MonthlyFirebaseData {
    DatabaseReference monthlyDBRef;
    public String currentMonth;

    public DatabaseReference open()  {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        monthlyDBRef = database.getReference(currentMonth);
        return monthlyDBRef;
    }

    public void close() {

    }

   /* public Expense createFish( String species, String weightInOz, String dateCaught) {           //Added String rating as a parameter
        // ---- Get a new database key for the vote
        String key = myFishDbRef.child(FishDataTag).push().getKey();
        // ---- set up the fish object
        Fish newFish = new Fish(key, species, weightInOz, dateCaught);
        // ---- write the vote to Firebase
        myFishDbRef.child(key).setValue(newFish);
        return newFish;
    }*/
}
