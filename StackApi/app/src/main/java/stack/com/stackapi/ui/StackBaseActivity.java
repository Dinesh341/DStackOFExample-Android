package stack.com.stackapi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.pnikosis.materialishprogress.ProgressWheel;

import stack.com.stackapi.R;
import stack.com.stackapi.adapter.ViewPagerAdapter;
import stack.com.stackapi.ui.fragment.MonthlyStackValues;
import stack.com.stackapi.ui.fragment.StackUserProfile;
import stack.com.stackapi.ui.fragment.VoteStackValues;
import stack.com.stackapi.ui.fragment.HotStackValues;
import stack.com.stackapi.ui.fragment.InterstingStackValues;
import stack.com.stackapi.ui.fragment.RecentStackValues;
import stack.com.stackapi.ui.fragment.WeekStackValues;
import stack.com.stackapi.utils.AppConstants;

public class StackBaseActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProgressWheel progressWheel;
    private String strProfileCheck;
    private static Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
        strProfileCheck = getIntent().getExtras().getString("Profile");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        Intent intent = getIntent();
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RecentStackValues(), getString(R.string.Recent));
        adapter.addFragment(new HotStackValues(),getString(R.string.Hot));
        adapter.addFragment(new InterstingStackValues(), getString(R.string.Intersting));
        adapter.addFragment(new VoteStackValues(), getString(R.string.Featured));
        adapter.addFragment(new WeekStackValues(), getString(R.string.week));
        adapter.addFragment(new MonthlyStackValues(), getString(R.string.monthly));
        if(strProfileCheck.equals("Create")){
            adapter.addFragment(new StackUserProfile(), getString(R.string.profile));
        }
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu=menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if(strProfileCheck.equals("Create")){
            menu.findItem(R.id.action_logout).setVisible(true);
        }else{
            menu.findItem(R.id.action_logout).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            clearCookies();
            Toast.makeText(getApplicationContext(), "LogOut Successfully", Toast.LENGTH_SHORT).show();
            AppConstants.savePreferences(this, "AcessToken", "0");
            Intent intent= new Intent(this,InitialActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void clearCookies(){
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
    }

}
