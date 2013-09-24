package com.barajasr.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MagneticSensor extends Activity implements SensorEventListener{
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private final String xAxis = "X-Axis:";
    private final String yAxis = "Y-Axis:";
    private final String zAxis = "Z-Axis:";
    private TextView XAxis;
    private TextView YAxis;
    private TextView ZAxis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.magnetic);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_NORMAL);

        XAxis = (TextView)findViewById(R.id.magneticX);
        YAxis = (TextView)findViewById(R.id.magneticY);
        ZAxis = (TextView)findViewById(R.id.magneticZ);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.magnetic_sensor, menu);
        return true;
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        XAxis.setText(xAxis + " " + sensorEvent.values[0] + " μT");
        YAxis.setText(yAxis + " " + sensorEvent.values[1] + " μT");
        ZAxis.setText(zAxis + " " + sensorEvent.values[2] + " μT");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
