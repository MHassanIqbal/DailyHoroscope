package t.hkb.designhoroscope.Activities;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.format.DateFormat;
import android.text.style.RelativeSizeSpan;
import android.util.Size;
import android.view.MenuItem;
import android.view.Window;

import java.util.Calendar;
import java.util.Date;

import t.hkb.designhoroscope.Adapters.ViewPagerAdapter;
import t.hkb.designhoroscope.Fragments.Tomorrow;
import t.hkb.designhoroscope.Fragments.Today;
import t.hkb.designhoroscope.Fragments.Yesterday;
import t.hkb.designhoroscope.R;

import static java.lang.System.in;


public class Main2Activity extends AppCompatActivity {

    private Toolbar   toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String    starNames,dateRangesActionBar;
    private int       logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent= getIntent();
        starNames = intent.getStringExtra("message");
//        logo = intent.getIntExtra("icon",0);
        dateRangesActionBar = intent.getStringExtra("starDatesRanges");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setIcon(logo);
        getSupportActionBar().setTitle(" "+starNames+" ("+dateRangesActionBar+")");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    Date d = new Date();
    Calendar cal = Calendar.getInstance();

    CharSequence pd  = DateFormat.format("MMM d, yyyy ", (long) (d.getTime()-8.64e+7));
    CharSequence cd  = DateFormat.format("MMM d, yyyy ", d.getTime());
    CharSequence nd  = DateFormat.format("MMM d, yyyy ", (long) (d.getTime()+8.64e+7));

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter Viewpageradapter = new ViewPagerAdapter(getSupportFragmentManager());
        String s = "Today";

        Viewpageradapter.addFragment(new Yesterday(), "Yesterday\n"+pd);
        Viewpageradapter.addFragment(new Today(),  s+"\n"+cd);
        Viewpageradapter.addFragment(new Tomorrow(), "Tommorow\n "+nd);
        viewPager.setAdapter(Viewpageradapter);
        viewPager.setCurrentItem(1);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

}
