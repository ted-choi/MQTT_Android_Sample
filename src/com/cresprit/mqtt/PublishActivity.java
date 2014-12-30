package com.cresprit.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import com.cresprit.mqtt.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PublishActivity extends Activity {
	EditText edtFeed;
	EditText edtMessage;
	Button btnPublish;
	MqttClient client;
	Connection connection;
	String feed;
	String message;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.publish);
		edtFeed = (EditText)findViewById(R.id.edtFeed);
		edtFeed.setText("5459f2f41d41c81e2ef1604e");
		edtFeed.setSelection(edtFeed.getText().length());
		
		edtMessage = (EditText)findViewById(R.id.edtMessage);
		edtMessage.setText("");
		
		btnPublish = (Button)findViewById(R.id.btnPublish);
		btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	feed = edtFeed.getText().toString();
            	message = edtMessage.getText().toString();
            	
            	
            	if("".equals(feed))
            	{
            		Toast.makeText(PublishActivity.this, "Insert Topic", Toast.LENGTH_SHORT).show();
                	return;
            	}
            	
            	if("".equals(message))
            	{
            		Toast.makeText(PublishActivity.this, "Insert Message", Toast.LENGTH_SHORT).show();
                	return;
            	}
            	
            	connection =  ConnectionMgr.getConnectionInstance();
            	connection.connect();            	
            	connection.setFeed(feed);
            	connection.setMessage(message);
            	
            	
        
				try {
					connection.publish();
					Toast.makeText(PublishActivity.this, "publish Success", Toast.LENGTH_SHORT).show();
				} catch (MqttException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(PublishActivity.this, "publis Fail", Toast.LENGTH_SHORT).show();
				}
            }
        });
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	
	
}