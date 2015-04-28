package io.github.kajdreef.smartphonesensing;

import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import io.github.kajdreef.smartphonesensing.ActivityMonitoring.AbstractSensor;
import io.github.kajdreef.smartphonesensing.ActivityMonitoring.Accelerometer;
import io.github.kajdreef.smartphonesensing.ActivityMonitoring.ActivityType;
import io.github.kajdreef.smartphonesensing.ActivityMonitoring.Reader;


public class MainMenu extends ActionBarActivity {

    SensorManager sm;
    AbstractSensor accelerometer;

    Button walking;
    Button queueing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        sm =(SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = new Accelerometer(sm);

        // Create walk button, when clicked on the button state will change state to WALK.
        walking = (Button) findViewById(R.id.walking);
        walking.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Accelerometer.setState(ActivityType.WALK);
            }
        });

        // Create queueing button, when clicked on the button state will change state to QUEUE.
        queueing = (Button) findViewById(R.id.queueing);
        queueing.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Accelerometer.setState(ActivityType.QUEUE);
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        accelerometer.register();
    }

    @Override
    public void onResume(){
        super.onResume();
        accelerometer.register();
    }

    @Override
    public void onPause(){
        super.onPause();
        accelerometer.unregister();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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

    @Override
    public void onDestroy(){
        super.onDestroy();
        accelerometer.unregister();
    }
}
