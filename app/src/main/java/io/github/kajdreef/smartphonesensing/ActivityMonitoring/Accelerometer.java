package io.github.kajdreef.smartphonesensing.ActivityMonitoring;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.util.Log;


/**
 * Created by kajdreef on 23/04/15.
 *
 * Implementation of the accelerometer.
 * It acquires the data and then writes it to a file.
 */
public class Accelerometer extends AbstractSensor {

    private Writer wr;

    public static ActivityType state = ActivityType.NONE;

    public static void setState(ActivityType newState){
        Accelerometer.state = newState;
    }

    public Accelerometer(SensorManager sm){
        super(sm);
        wr = new Writer("accelerometerData.txt");
        type = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor,int accuracy){

    }

    @Override
    public void onSensorChanged(SensorEvent event){
        double x,y,z;

        // Check if changed sensor is the Accelerometer.
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            x=event.values[0];
            y=event.values[1];
            z=event.values[2];

            // Log accelerometer data to the console output.
            Log.d("State: \t\t" + Accelerometer.state, "Accelerometer");

            // Add data to File
            wr.appendData(x, y, z, Accelerometer.state);
        }
    }
}
