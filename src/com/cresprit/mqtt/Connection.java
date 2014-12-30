package com.cresprit.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.internal.MemoryPersistence;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Random;

public class Connection {

    public static final String TOPIC = "53d9d3b01d41c80fc63b3ebf";
    private MqttConnectOptions options;
    private MqttClient client;
    private static String serverUri;
    private String clientID;
    private String userName;
    private String feed;
    private String message;
    
    
    
    public Connection(String _serverUri) {
        try {
        	serverUri = _serverUri;
            client = new MqttClient(serverUri, MqttClient.generateClientId(), new MemoryPersistence());
        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public boolean connect() {

        try {
            options = new MqttConnectOptions();
            options.setUserName(userName);
            options.setPassword("default".toCharArray());
            options.setCleanSession(false);
            options.setKeepAliveInterval(20000);
            client.connect(options);
            
            return true;
            
        } catch (MqttException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void publish() throws MqttException {
        final MqttTopic temperatureTopic = client.getTopic(TOPIC);				
        //final String temperature = "ON";//createRandomNumberBetween(-20, 4);		chchoi	

        final MqttMessage msg = new MqttMessage(String.valueOf(message).getBytes());
        temperatureTopic.publish(msg);

        System.out.println("Published data. Topic: " + temperatureTopic.getName() + "  Message: " + message);
    }

    public void subscribe(Context context) throws MqttException {
    	  
          //options = new MqttConnectOptions();
          //options.setUserName("e586fa6833374682a9d4dfcd6f4c6ec3");
          //options.setPassword("default".toCharArray());
          client.setCallback(new SubscribeCallback(context));
          //client.connect(options);

          //Subscribe to all subtopics of homeautomation
          client.subscribe("53d9d3b01d41c80fc63b3ebf");
    }
    public static int createRandomNumberBetween(int min, int max) {

        return new Random().nextInt(max - min + 1) + min;
    }


    public void setUsername(String _username)
    {
    	this.userName = _username;
    }
    
    public void setClientId(String _clientId)
    {
    	this.clientID = _clientId;
    }
    
    public MqttTopic getTopic()
    {
    	MqttTopic topic = client.getTopic(TOPIC);
    	return topic;
    }
    
    public void setFeed(String _feed)
    {
    	this.feed = _feed;
    }
    
    public void setMessage(String _message)
    {
    	this.message = _message;
    }
}
