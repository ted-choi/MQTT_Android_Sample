package com.cresprit.mqtt;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.cresprit.mqtt.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SubscribeActivity extends Activity {
	EditText edtFeed;
	Button btnSubscribe;
	String feed;
	Connection connection;
	MessageAdapter adapter;
	ListView tvMessages;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.subscribe);
		edtFeed = (EditText)findViewById(R.id.edtFeed);
		
		edtFeed.setText("5459f2f41d41c81e2ef1604e");
        edtFeed.setSelection(edtFeed.getText().length()); 
		
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edtFeed.getWindowToken(), 0);
        
		btnSubscribe = (Button)findViewById(R.id.btnSubscribe);
		btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	feed = edtFeed.getText().toString();
            	
            	if("".equals(feed))
            	{
            		Toast.makeText(SubscribeActivity.this, "Insert Topic", Toast.LENGTH_SHORT).show();
                	return;
            	}
            	
            	final Intent intent = new Intent(SubscribeActivity.this, MQTTService.class);
                   startService(intent);
//            	connection =  ConnectionMgr.getConnectionInstance();
//            	connection.setFeed(feed);
//            	connection.connect();   
//				try {
//					connection.subscribe(SubscribeActivity.this);
//					Toast.makeText(SubscribeActivity.this, "Subscribe Success", Toast.LENGTH_SHORT).show();
//				} catch (MqttException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					Toast.makeText(SubscribeActivity.this, "Subscribe Fail", Toast.LENGTH_SHORT).show();
//				}
            }
        });
		
		tvMessages = (ListView)findViewById(R.id.listMessages);
		adapter = new MessageAdapter(SubscribeActivity.this,R.id.listMessages, null );
		tvMessages.setAdapter(adapter);
		
		registerReceiver(mBroadcastReceiver, new IntentFilter(ConnectionMgr.MSG_RECEIVE));
		
	}
	
	final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			adapter.notifyDataSetChanged();
		}
	};
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		unregisterReceiver(mBroadcastReceiver);
		super.onDestroy();
	}

}