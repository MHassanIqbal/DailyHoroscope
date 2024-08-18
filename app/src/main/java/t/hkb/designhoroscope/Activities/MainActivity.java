package t.hkb.designhoroscope.Activities;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Calendar;

import t.hkb.designhoroscope.Adapters.gridAdapter;
import t.hkb.designhoroscope.R;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener,View.OnClickListener {

    final String[] starNames = {"Aries","Taurus","Gemini","Cancer","Leo","Virgo","Libra","Scorpio","Sagittarius","Capricorn","Aquarius","Pisces"};
    final String[] dateRangesActionBar = {"Mar 21- Apr 19","Apr 20 - May 20","May 21- June 20","Jun 21 - Jul 22","Jul 23 - Aug 22","Aug 23-Sep 22","Sep 23-Oct 22","Oct 23-Nov 21","Nov 22-Dec 21","Dec 22-Jan 19","Jan 20-Feb 18","Feb 19-Mar 20"};
    final int []logos = {R.drawable.ic_aries,R.drawable.ic_taurus,R.drawable.ic_gemini,R.drawable.ic_cancer,
            R.drawable.ic_leo,R.drawable.ic_virgo,R.drawable.ic_libra,R.drawable.ic_scorpius,
            R.drawable.ic_sagittarius_symbol,R.drawable.ic_capricornius, R.drawable.ic_aquarius,R.drawable.ic_pisces};

    int year = 0,month = 0,day = 0,height;
    GridView simpleGrid;
    Button fab;
    Calendar c;
    InterstitialAd interstitialAd;
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////////////////////////////////////////////////////////////////////////////////////////////
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Horoscope");
        fab = (Button) findViewById(R.id.fab);
        simpleGrid = (GridView) findViewById(R.id.gridView);
        simpleGrid.setOnItemClickListener(this);
        fab.setOnClickListener(this);

        //////////////////setting the grid layout by gettings its default screen size///////////////
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        height = metrics.heightPixels;

        /////////////Create an object of CustomAdapter and set Adapter to GirdView//////////////////
        gridAdapter gridAdapter = new gridAdapter(getApplicationContext(), logos, starNames,height);
        simpleGrid.setAdapter(gridAdapter);


    }

    private boolean checkInternetConnection() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // ARE WE CONNECTED TO THE NET
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {

            return true;
        }else{
        return false;
    }
    }

    @Override
    public void onBackPressed() {
        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Horoscope");
        alertDialog.setMessage("Do you really want to exit?");
//        alertDialog.setIcon(R.drawable.welcome);

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
               alertDialog.dismiss();

            }
        });
           alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.super.onBackPressed();

            }
        });

        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_reminder) {

                Intent intent =  new Intent(MainActivity.this,Main3Activity.class);
               startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(checkInternetConnection()) {

            // set an Intent to Another Activity
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            intent.putExtra("message", starNames[position]);
            intent.putExtra("starDatesRanges", dateRangesActionBar[position]);
            intent.putExtra("icon", logos[position]);
            startActivity(intent); // start Intent

        }else{
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View view){

        if(checkInternetConnection()) {
            //Canlendar
            c = c.getInstance();
            c.set(1990, 05, 23);
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, R.style.TimePickerTheme,new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    //Calling custom class to Find users respective star to find their horoscope
                    myStarMethod.showDate(month, dayOfMonth, starNames, dateRangesActionBar, MainActivity.this, Main2Activity.class, "message", "starDatesRanges");
                }
            }, year, month, day);
            datePickerDialog.show();


        }else{
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }
}