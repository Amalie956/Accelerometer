package com.example.accelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);

        // Initialize the sensor objects
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Register the accelerometer sensor
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Unregister the accelerometer sensor
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Get the accelerometer data
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        // Do something with the accelerometer data
        System.out.println(y);
        textView.setText(String.format("x = %f\ny = %f\nz = %f", x, y, z));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something when the accuracy of the sensor changes
    }
}
