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
    private SensorManager sensorManager = null;
    private Sensor sensor = null;
    private final String xAxis = "X-Axis:";
    private final String yAxis = "Y-Axis:";
    private final String zAxis = "Z-Axis:";
    private final String unit = "μT";
    private TextView XAxis;
    private TextView YAxis;
    private TextView ZAxis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.magnetic);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
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
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        XAxis.setText(xAxis + " " +
                String.format("%.2f", sensorEvent.values[0]) + " " + unit);
        YAxis.setText(yAxis + " " +
                String.format("%.2f", sensorEvent.values[1]) + " " + unit);
        ZAxis.setText(zAxis + " " +
                String.format("%.2f", sensorEvent.values[2]) + " " + unit);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
