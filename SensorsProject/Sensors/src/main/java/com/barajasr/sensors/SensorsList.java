package com.barajasr.sensors;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SensorsList extends Activity {
    private SensorManager sensorManager = null;
    private List<Sensor> sensorList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get reference to Sensor Manager
        sensorManager = (SensorManager)getSystemService(Service.SENSOR_SERVICE);

        // Get list of sensors
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        List<Map<String, String>> sensorData = new ArrayList<Map<String,String>>();
        for (Sensor sensor: sensorList) {
            Map<String, String> data = new HashMap<String, String>();
            data.put("name", sensor.getName());
            data.put("vendor", sensor.getVendor());
            sensorData.add(data);
        }

        ListView listView= (ListView)findViewById(R.id.listView);
        String[] from = new String[] {"name", "vendor"};
        int[] to = new int[] {R.id.name, R.id.vendor};
        SimpleAdapter adapter = new SimpleAdapter(this, sensorData, R.layout.list_item, from, to);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                // sensor id [1, 17], not counting TYPE_ALL = -1
                String item = sensorList.get(position).getName() +
                        ":" + sensorList.get(position).getType();
                Toast.makeText(getApplicationContext(),
                        item,
                        Toast.LENGTH_SHORT)
                        .show();
                launchSensor(sensorList.get(position).getType());
            }

        });
    }

    private void launchSensor(int id) {
        Intent intent;
        switch (id) {
            case Sensor.TYPE_ACCELEROMETER:
                intent = new Intent(SensorsList.this, AccelerometerSensor.class);
                startActivity(intent);
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE: break;
            case Sensor.TYPE_GAME_ROTATION_VECTOR: break;
            case Sensor.TYPE_GRAVITY: break;
            case Sensor.TYPE_GYROSCOPE: break;
            case Sensor.TYPE_GYROSCOPE_UNCALIBRATED: break;
            case Sensor.TYPE_LIGHT: break;
            case Sensor.TYPE_LINEAR_ACCELERATION: break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                intent = new Intent(SensorsList.this, MagneticSensor.class);
                startActivity(intent);
                break;
            case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED: break;
            case Sensor.TYPE_ORIENTATION:
                Toast.makeText(getApplicationContext(),
                        "Sensor.TYPE_ORIENTATION has been deprecated.",
                        Toast.LENGTH_SHORT)
                        .show();
                break;
            case Sensor.TYPE_PRESSURE: break;
            case Sensor.TYPE_PROXIMITY:
                intent = new Intent(SensorsList.this, ProximitySensor.class);
                startActivity(intent);
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY: break;
            case Sensor.TYPE_ROTATION_VECTOR: break;
            case Sensor.TYPE_SIGNIFICANT_MOTION: break;
            case Sensor.TYPE_TEMPERATURE:
                Toast.makeText(getApplicationContext(),
                        "Sensor.TYPE_TEMPERATURE has been deprecated.",
                        Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                Toast.makeText(getApplicationContext(),
                        "Unrecognized sensor id" + id,
                        Toast.LENGTH_SHORT)
                        .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sensors_list, menu);
        return true;
    }
    
}
