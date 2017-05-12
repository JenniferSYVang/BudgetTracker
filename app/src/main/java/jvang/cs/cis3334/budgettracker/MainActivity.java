package jvang.cs.cis3334.budgettracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

/**
 * This app was design to help user keep track of their monthly expense. There are various buttons
 * for the user to click on when the app first initalize. These buttons will represent the various
 * expenses that are available: fun, dining out, clothes, miscellaneous, gas, groceries, cellphone,
 * savings, rent, and utilities. On the next screen/activity their will be a prompt asking the user
 * to enter in the amount that was spent. When this is done the last screen/activity will pop up.
 * In the final screen, MonthlyData, all the monthly information will be displayed.
 *
 *
 * @author  Jennifer Vang
 *@since    2017-5-11
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Declare Variables
    Button funBtn, diningBtn, clothesBtn, miscellaneousBtn, gasBtn, groceriesBtn, cellBtn, savingsBtn, rentBtn, utilitiesBtn;
    Month today;
    public static final String CATEGORIES = "Categories";
    public static final String EXPENSE = "Expense";

    public static Context getContext() {return MainActivity.getContext();} // returns this activity's context


    /**
     * This method will define what the screen will look like when the app first open up.
     * There is an action bar, navigation bar as well as a floating action button that all leads
     * to the last screen/activity (MonthlyData). The variables declared above are linked to the
     * widegts here. This method will also set the buttons to listen for a click from the user.
     *
     *  @param savedInstanceState  This is bundle that contains data from the last use
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        today = new Month(); // getting today's date

        // This sets up the floating action bar to load the MonthlyData Activity when this is clicked
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Monthly Data", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent actMonthlyData = new Intent(MainActivity.this, MonthlyData.class);
                actMonthlyData.putExtra(EXPENSE, new Expense(today.getYear(), today.getMonth()));
                startActivity(actMonthlyData);

            }
        });

        // Setting up navagation panel to the right of the screen
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // link buttons with variables
        funBtn = (Button) findViewById(R.id.buttonFun);
        diningBtn = (Button) findViewById(R.id.buttonOut);
        clothesBtn = (Button) findViewById(R.id.buttonClothes);
        miscellaneousBtn = (Button) findViewById(R.id.buttonMis);
        gasBtn = (Button) findViewById(R.id.buttonGas);
        groceriesBtn = (Button) findViewById(R.id.buttonGroceries);
        cellBtn = (Button) findViewById(R.id.buttonCell);
        savingsBtn = (Button) findViewById(R.id.buttonSavings);
        rentBtn = (Button) findViewById(R.id.buttonRent);
        utilitiesBtn = (Button) findViewById(R.id.buttonUtilities);

        /**
         * The following methods sets the listener up for the buttons. If the button is clicked
         * then two things will be sent to a new method, startSecondScreen(String, String), the
         * tag, "Categories", and the data that goes with this tag (which can be any of the expenses).
         */
        funBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSecondScreen(CATEGORIES, funBtn.getText().toString());
            }
        });

        diningBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSecondScreen(CATEGORIES, diningBtn.getText().toString());
            }
        });

        clothesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSecondScreen(CATEGORIES, clothesBtn.getText().toString());
            }
        });

        miscellaneousBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSecondScreen(CATEGORIES, miscellaneousBtn.getText().toString());
            }
        });

        gasBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSecondScreen(CATEGORIES, gasBtn.getText().toString());
            }
        });

        groceriesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSecondScreen(CATEGORIES, groceriesBtn.getText().toString());
            }
        });

        cellBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSecondScreen(CATEGORIES, cellBtn.getText().toString());
            }
        });

        savingsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSecondScreen(CATEGORIES, savingsBtn.getText().toString());
            }
        });

        rentBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSecondScreen(CATEGORIES, rentBtn.getText().toString());
            }
        });

        utilitiesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSecondScreen(CATEGORIES, utilitiesBtn.getText().toString());
            }
        });
    }

    /**
     * This method is what all the buttons will call when they are clicked. startSecondScreen() will
     * recieve two parameters and with these parameters the method will start the second activity,
     * where there will be a prompt to get the amount.
     *
     * @param cat   This is the tag or what we named as "Categories" above
     * @param expense This will represent the type of expense that is getting passed to the next screen
     */
    public void startSecondScreen(String cat, String expense){
        Intent actGetAmount = new Intent(MainActivity.this, GetAmount.class);
        actGetAmount.putExtra(cat, expense);
        startActivity(actGetAmount);
    }


    /**
     * This controls what happens when the user press the back button. The last screen the app was on
     * will load otherwise if there is no previous screen to the app it will just close and teh phones
     * main screen will show up.
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Inflate the menu; this adds items to the action bar if it is present.
     *
     * @param menu  The variable that is to be inflated
     * @return  This will return a boolean depending on whether or not the menu inflated successfully.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * This is where I handle the action bar item clicks, the menu on the top. When the itme
     * in here is clicked the final activity, MonthlyData, will load.
     *
     * @param item  the menu item
     * @return recursive, returns item that was entered
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_summary) {
            Intent actMonthlyData = new Intent(MainActivity.this, MonthlyData.class);
            actMonthlyData.putExtra(EXPENSE, new Expense(today.getYear(), today.getMonth()));
            startActivity(actMonthlyData);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This will control what happens when the item in the navigation drawer, to the left of the screen,
     * is selected. The final activity, MonthlyData, will appear on the screen if the naviation item is clicked on.
     *
     * @param item  the menuItem that will be in the navigation drawer.
     * @return if activity don start close the navigation drawer
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Summary) {
            Intent actMonthlyData = new Intent(MainActivity.this, MonthlyData.class);
            actMonthlyData.putExtra(EXPENSE, new Expense(today.getYear(), today.getMonth()));
            startActivity(actMonthlyData);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
