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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Declare Variables
    Button funBtn, diningBtn, clothesBtn, miscellaneousBtn, gasBtn, groceriesBtn, cellBtn, savingsBtn, rentBtn, utilitiesBtn;

    public static Context getContext() {
        return MainActivity.getContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Monthly Data", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent actMonthlyData = new Intent(MainActivity.this, MonthlyData.class);
                startActivity(actMonthlyData);
            }
        });

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

        // tells the button what to do when it is clicked on
        funBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSecondScreen("Categories", "Fun");
            }
        });

        diningBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSecondScreen("Categories", "Dining Out");
            }
        });

        clothesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSecondScreen("Categories", "Clothes");
            }
        });

        miscellaneousBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSecondScreen("Categories", "Miscellaneous");
            }
        });

        gasBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSecondScreen("Categories", "Gas");
            }
        });

        groceriesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSecondScreen("Categories", "Groceries");
            }
        });

        cellBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSecondScreen("Categories", "Cellphone");
            }
        });

        savingsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSecondScreen("Categories", "Savings");
            }
        });

        rentBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSecondScreen("Categories", "Rent");
            }
        });

        utilitiesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSecondScreen("Categories", "Utilities");
            }
        });
    }

    // starts a new intent passing an two strings; tag and data that needs to be passed
    public void startSecondScreen(String cat, String expense){
        Intent actGetAmount = new Intent(MainActivity.this, GetAmount.class);
        actGetAmount.putExtra(cat, expense);
        startActivity(actGetAmount);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
