package com.example.administrator.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager msm;
    private Sensor macc;
    private Sensor mori;
    private Sensor mlight;
    private TextView tacc;
    private TextView tori;
    private TextView tlight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tacc=(TextView) this.findViewById(R.id.accelerometer);
        tori=(TextView) this.findViewById(R.id.orientation);
        tlight=(TextView) this.findViewById(R.id.light);
        msm=(SensorManager) getSystemService(SENSOR_SERVICE);
        macc=msm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mori=msm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mlight=msm.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x=event.values[SensorManager.DATA_X];
        float y=event.values[SensorManager.DATA_Y];
        float z=event.values[SensorManager.DATA_Z];
        if(event.sensor.getType()==Sensor.TYPE_ORIENTATION){
            tori.setText("位置:"+x+","+y+","+z);
        }else if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            tacc.setText("加速度："+x+","+y+","+z);
        }else if(event.sensor.getType()==Sensor.TYPE_LIGHT){
            tlight.setText("光线："+event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        msm.registerListener(this,macc,SensorManager.SENSOR_DELAY_NORMAL);
        msm.registerListener(this,mori,SensorManager.SENSOR_DELAY_NORMAL);
        msm.registerListener(this,mlight,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        msm.unregisterListener(this);
    }
}
