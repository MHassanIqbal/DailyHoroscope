package t.hkb.designhoroscope.Activities;


import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import t.hkb.designhoroscope.Adapters.gridAdapter;
import t.hkb.designhoroscope.Adapters.recyclerAdapter;
import t.hkb.designhoroscope.Notification.NotificationReciver;
import t.hkb.designhoroscope.R;
import t.hkb.designhoroscope.RecyclerView.FlipAnimation;
import t.hkb.designhoroscope.RecyclerView.MyDividerItemDecoration;
import t.hkb.designhoroscope.RecyclerView.recyclerTouchListener;
import t.hkb.designhoroscope.database.DatabaseHelper;
import t.hkb.designhoroscope.database.model.Table;

public class Main3Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public TextView remChecker;
    public ImageView notificationImg1, notificationImg2;
    private List<Table> reminderList = new ArrayList<Table>();
    private RecyclerView recyclerView;
    private DatabaseHelper db;
    private RecyclerView.Adapter adapter;
    private SimpleDateFormat mFormat;
    private GridView simpleGrid;
    private AlertDialog alertDialog;
    private Calendar c;
    private FrameLayout frameLayout;
    private SwipeRefreshLayout swipeRefresh;
    private int hour, min, height, flag = 0;
    public int Fposition;
    private final Context context = this;
    private final String[] starNames = {"Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"};
    private final String[] dateRangesActionBar = {"Mar 21- Apr 19", "Apr 20 - May 20", "May 21- June 20", "Jun 21 - Jul 22", "Jul 23 - Aug 22", "Aug 23-Sep 22", "Sep 23-Oct 22", "Oct 23-Nov 21", "Nov 22-Dec 21", "Dec 22-Jan 19", "Jan 20-Feb 18", "Feb 19-Mar 20"};
    private final int[] logos = {R.drawable.ic_aries, R.drawable.ic_taurus, R.drawable.ic_gemini, R.drawable.ic_cancer,
            R.drawable.ic_leo, R.drawable.ic_virgo, R.drawable.ic_libra, R.drawable.ic_scorpius,
            R.drawable.ic_sagittarius_symbol, R.drawable.ic_capricornius, R.drawable.ic_aquarius, R.drawable.ic_pisces};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        remChecker = (TextView) findViewById(R.id.remChecker);
        notificationImg1 = (ImageView) findViewById(R.id.notificationImg1);
        notificationImg2 = (ImageView) findViewById(R.id.notificationImg2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        frameLayout = (FrameLayout) findViewById(R.id.fragment_placeholder);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
            }
        });

        db = new DatabaseHelper(this);
        refreshList();
        recyclerView.addOnItemTouchListener(new recyclerTouchListener(Main3Activity.this, recyclerView, new recyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, final int position) {
//                Toast.makeText(Main3Activity.this, "Item position: "+position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {
                Fposition = position;
                Snackbar snack = Snackbar.make(findViewById(R.id.fragment_placeholder), "Do you really want to delete?", Snackbar.LENGTH_LONG);
                snack.setAction("DELETE", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: Your Action
                        db.deleteNote(reminderList.get(Fposition));
                        swipeRefresh.setRefreshing(true);
                        int calInt = db.deleteNote(reminderList.get(Fposition));
                        cancelAlaram(calInt);//delete the reminder
                        Toast.makeText(context, " Alarm Deleted", Toast.LENGTH_SHORT).show();
                        refreshList();
                        swipeRefresh.setRefreshing(false);
                    }
                });

                snack.show();

                swipeRefresh.setRefreshing(false);
            }
        }));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompts, (ViewGroup) findViewById(R.id.gridView));

                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                height = metrics.heightPixels;

                simpleGrid = (GridView) promptsView.findViewById(R.id.gridView);
                gridAdapter gridAdapter = new gridAdapter(getApplicationContext(), logos, starNames, height);
                simpleGrid.setAdapter(gridAdapter);
                simpleGrid.setOnItemClickListener((AdapterView.OnItemClickListener) context);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptsView);
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();// show it

            }
        });

        toggleEmptyNotes();//check if the db is empty or not

    }

    private void refreshList() {
        reminderList.clear();
        reminderList.addAll(db.getAllNotes());
        adapter = new recyclerAdapter(context, reminderList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(context, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(adapter);
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        swipeRefresh.setRefreshing(true);
        alertDialog.dismiss();

        c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        min = c.get(Calendar.MINUTE);

        TimePickerDialog mTimePicker = new TimePickerDialog(Main3Activity.this, R.style.TimePickerTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                c.set(Calendar.HOUR_OF_DAY, selectedHour);
                c.set(Calendar.MINUTE, selectedMinute);


                if (mFormat == null)
                    mFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

                DatabaseHelper db = new DatabaseHelper(Main3Activity.this);
                int i = (int) db.insertNote(starNames[position], mFormat.format(c.getTime()));
                startAlaram(c, i);
//                Toast.makeText(context,i+": "+starNames[position]+" : "+ mFormat.format(c.getTime()), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "Reminder Set", Toast.LENGTH_SHORT).show();
                refreshList();
                swipeRefresh.setRefreshing(false);
            }
        }, hour, min, true);
        mTimePicker.setTitle(starNames[position] + " Set Notification Time");
        swipeRefresh.setRefreshing(false);
        mTimePicker.show();

    }

    @SuppressLint("NewApi")
    public void startAlaram(Calendar calendar, int i) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationReciver.class);

        intent.putExtra("STAR", starNames[Fposition]);
        intent.putExtra("starDatesRanges", dateRangesActionBar[Fposition]);
        intent.putExtra("alarmPosition", Fposition);
//        Bundle bundleReminder = new Bundle();
//        bundleReminder.putString("STAR",starNames[Fposition]);
//        bundleReminder.putString("starDatesRanges",dateRangesActionBar[Fposition]);
//        bundleReminder.putInt("alarmPosition",Fposition);
//        bundleReminder.putParcelable("alarmList", (Parcelable) reminderList);
//        intent.putExtras(bundleReminder);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, i, intent, 0);
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(calendar.DATE, 1);
            swipeRefresh.setRefreshing(false);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public void cancelAlaram(int i) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, i, new Intent(context, NotificationReciver.class), 0);
        alarmManager.cancel(pendingIntent);
    }

    private void toggleEmptyNotes() {

        // you can check notesList.size() > 0
        if (db.getNotesCount() > 0) {
            remChecker.setVisibility(View.GONE);
        } else {
            remChecker.setVisibility(View.VISIBLE);
        }

    }

}


