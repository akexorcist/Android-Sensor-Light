package app.akexorcist.sensor_light;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

public class Main extends Activity {
	TextView textLight;
	SensorManager sensorManager;
	Sensor sensor;
 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
 
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		
		textLight = (TextView) findViewById(R.id.textLight);
	}
 
	public void onResume() {
		super.onResume();
		sensorManager.registerListener(lightListener, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}
 
	public void onStop() {
		super.onStop();
		sensorManager.unregisterListener(lightListener);
	}

	public SensorEventListener lightListener = new SensorEventListener() {
		public void onAccuracyChanged(Sensor sensor, int acc) { }
 
		public void onSensorChanged(SensorEvent event) {
			float x = event.values[0];

			textLight.setText((int)x + " lux");
		}
	};
}