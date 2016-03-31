package org.ncfrcteams.frcscoutinghub2016.ui.hub;

import android.net.Uri;
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
import org.ncfrcteams.frcscoutinghub2016.matchdata.schedule.MatchDescriptor;
import org.ncfrcteams.frcscoutinghub2016.ui.CustomPageAdapter;
import org.ncfrcteams.frcscoutinghub2016.ui.CustomViewPager;

import java.util.ArrayList;

public class HubActivity extends AppCompatActivity implements HubContentsFragment.OnHubContentsFragListener,
        HubListFragment.OnHubListFragListener, SmsReceiver.SmsListener {

    public Toolbar toolbar;
    public CustomViewPager hubViewPager;
    public CustomPageAdapter myPageAdapter;
    public ArrayList<Fragment> fragments;
    public String user = "test";
    public String pass = "test";
    public SmsReceiver smsReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        hubViewPager = (CustomViewPager) findViewById(R.id.hubViewPager);
        hubViewPager.setPagingEnabled(false);
        setSupportActionBar(toolbar);

        fragments = new ArrayList<>();
        fragments.add(HubContentsFragment.newInstance("Create"));
        fragments.add(HubListFragment.newInstance());
        fragments.add(HubContentsFragment.newInstance("Details"));

        ArrayList<String> fragTitles = new ArrayList<>();
        fragTitles.add("Create");
        fragTitles.add("Matches");
        fragTitles.add("Details");

        myPageAdapter = new CustomPageAdapter(getSupportFragmentManager(), fragments, fragTitles);
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

                int[] teams = {1991,22,45553,5134,643,833};
                ((HubListFragment)fragments.get(1)).mySchedule.add(new MatchDescriptor(this, 1, teams));

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
                sendPostRequest(user, pass, "hhh");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void smsReceived(String number, String message) {
        Toast.makeText(this, "sms from "+ number + ":\n\n" + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onHubContentFragInteraction(Uri uri) {
    }

    @Override
    public void onHubListFragInteraction(Uri uri) {

    }

    public void sendPostRequest(String user, String pass, String data) {
        String urlstring = "http://pavanec2.us.to/frc/database/update/view/index.php";
        new PostClass(this, urlstring, user, pass, data, true).execute();
    }

}