package org.ncfrcteams.frcscoutinghub2016.ui.hub;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.ncfrcteams.frcscoutinghub2016.R;
import org.ncfrcteams.frcscoutinghub2016.communication.http.PostClass;
import org.ncfrcteams.frcscoutinghub2016.communication.sms_server.SmsReceiver;
import org.ncfrcteams.frcscoutinghub2016.ui.CustomPageAdapter;
import org.ncfrcteams.frcscoutinghub2016.ui.CustomViewPager;

import java.util.ArrayList;

public class HubActivity extends AppCompatActivity implements SmsReceiver.SmsListener,
         HubCreateFragment.HubCreateFragListener, HubListFragment.HubListFragListener, HubContentsFragment.HubContentsFragListener {

    private CustomViewPager hubViewPager;
    private ArrayList<Fragment> fragments;
    private String user = "test";
    private String pass = "test";
    private SmsReceiver smsReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        hubViewPager = (CustomViewPager) findViewById(R.id.hubViewPager);
        hubViewPager.setPagingEnabled(false);
        setSupportActionBar(toolbar);

        fragments = new ArrayList<>();
        fragments.add(HubCreateFragment.newInstance());
        fragments.add(HubListFragment.newInstance());
        fragments.add(HubContentsFragment.newInstance("Details"));

        ArrayList<String> fragTitles = new ArrayList<>();
        fragTitles.add("Create");
        fragTitles.add("Schedule");
        fragTitles.add("Details");

        CustomPageAdapter myPageAdapter = new CustomPageAdapter(getSupportFragmentManager(), fragments, fragTitles);
        hubViewPager.setAdapter(myPageAdapter);
        hubViewPager.setCurrentItem(1);

        smsReceiver = new SmsReceiver(this);
        smsReceiver.register();
        smsReceiver.setSmsListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            smsReceiver.unregister();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.hubnew:
                hubViewPager.setCurrentItem(0);
                return true;
            case R.id.hubview:
                hubViewPager.setCurrentItem(1);
                return true;
            case R.id.hubpush:
                if (user.equals("test")){
                    //TODO prompt for user / pass
                    user = "4828";
                    pass = "RoboEagles4828";
                }
                sendPostRequest(user, pass, ((HubListFragment) fragments.get(1)).getDatabase());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void smsReceived(String number, String message) {
        Toast.makeText(this, "sms from "+ number + ":\n\n" + message, Toast.LENGTH_LONG).show();
    }

    public void sendPostRequest(String user, String pass, String data) {
        String urlstring = "http://pavanec2.us.to/frc/database/update/view/index.php";
        new PostClass(this, urlstring, user, pass, data, true).execute();
    }

    @Override
    public void autopush() {
        Toast.makeText(this, "autopush?", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addNewMatch(int[] teams, int matchnum, boolean isQual, String phonenum) {
        ((HubListFragment)fragments.get(1)).addNewMatch(teams, matchnum, isQual, phonenum);
    }
}