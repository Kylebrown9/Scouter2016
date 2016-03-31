package org.ncfrcteams.frcscoutinghub2016.ui.scout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.ncfrcteams.frcscoutinghub2016.R;
import org.ncfrcteams.frcscoutinghub2016.ui.hub.HubActivity;


public class ScoutPrematchActivity extends AppCompatActivity {

    private int orientation = 1;
    private ImageView orientation1;
    private ImageView orientation2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_activity_prematch);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        orientation1 = (ImageView) findViewById(R.id.orientation1);
        orientation2 = (ImageView) findViewById(R.id.orientation2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, ":)", Toast.LENGTH_SHORT).show();
    }

    public void orientation1Selected(View view){
        orientation1.setAlpha((float) 1.0);
        orientation2.setAlpha((float) 0.6);
        orientation = 1;
    }

    public void orientation2Selected(View view){
        orientation1.setAlpha((float) 0.6);
        orientation2.setAlpha((float) 1.0);
        orientation = 2;
    }

    public void launchScouter(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            Intent intent = new Intent(this, ScoutMainActivity.class);
            intent.putExtra("Match Setup", scanResult.getContents());
            intent.putExtra("Orientation", orientation);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Failed Scan", Toast.LENGTH_SHORT).show();
        }
    }

    public void launchHub(View view){
        Intent intent = new Intent(this, HubActivity.class);
        startActivity(intent);
        finish();
    }
}
