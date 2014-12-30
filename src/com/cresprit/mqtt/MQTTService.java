package com.cresprit.mqtt;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.MemoryPersistence;


public class MQTTService extends Service {

    public static final String BROKER_URL = "tcp://192.168.0.122:1884";

    /* In a real application, you should get an Unique Client ID of the device and use this, see
    http://android-developers.blogspot.de/2011/03/identifying-app-installations.html */
    public static final String clientId = "android-client";
    
    private MqttClient mqttClient;
    private MqttConnectOptions options;

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {

        try {
            mqttClient = new MqttClient(BROKER_URL, clientId, new MemoryPersistence());
            options = new MqttConnectOptions();
            options.setUserName("4a87cf6a423e4174b96b8008d60dab68");
            options.setPassword("default".toCharArray());
            mqttClient.setCallback(new SubscribeCallback(this));
            mqttClient.connect(options);

            mqttClient.subscribe("5459f2f41d41c81e2ef1604e");

        } catch (MqttException e) {
            Toast.makeText(getApplicationContext(), "Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        try {
            mqttClient.disconnect(0);
        } catch (MqttException e) {
            Toast.makeText(getApplicationContext(), "Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
