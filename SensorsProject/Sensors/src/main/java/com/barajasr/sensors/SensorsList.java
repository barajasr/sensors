package com.barajasr.sensors;

import android.app.Service;
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
                // sensor id [1, 17]
                String item = sensorList.get(position).getName() +
                        ":" + sensorList.get(position).getType();
                Toast toast = Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT);
                toast.show();
            }

        });
    }

    private void launchSensor(int id) {
        switch (id) {
            case Sensor.TYPE_ACCELEROMETER:
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
            case Sensor.TYPE_GRAVITY:
            case Sensor.TYPE_GYROSCOPE:
            case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
            case Sensor.TYPE_LIGHT:
            case Sensor.TYPE_LINEAR_ACCELERATION:
            case Sensor.TYPE_MAGNETIC_FIELD:
            case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
            case Sensor.TYPE_PRESSURE:
            case Sensor.TYPE_PROXIMITY:
            case Sensor.TYPE_RELATIVE_HUMIDITY:
            case Sensor.TYPE_ROTATION_VECTOR:
            case Sensor.TYPE_SIGNIFICANT_MOTION:
            default:
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Unrecognized sensor id", Toast.LENGTH_SHORT);
                toast.show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sensors_list, menu);
        return true;
    }
    
}
