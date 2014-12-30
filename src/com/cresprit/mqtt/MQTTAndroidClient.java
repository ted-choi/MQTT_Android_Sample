package com.cresprit.mqtt;

import com.cresprit.mqtt.R;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
//import com.google.android.gms.maps.MapView;

public class MQTTAndroidClient extends Activity {


	public static final String BROKER_URL = "tcp://192.168.0.113:1883";
    public static final String SERVICE_CLASSNAME = "com.cresprit.mqtt.MQTTService";
    private Button btnConnect;
    private RadioGroup rGrp;
    private RadioButton rBtn0;
    private RadioButton rBtn1;
    private RadioButton rBtn2;
    private EditText edtServerUri;
    private EditText edtClientId;
    private EditText edtUsername;
    private String serverUri;
    private String clientId;
    private String username;//e586fa6833374682a9d4dfcd6f4c6ec3
    private Connection connection;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnConnect = (Button) findViewById(R.id.btnConnect);
        rGrp = (RadioGroup) findViewById(R.id.group);
        rBtn0 = (RadioButton)findViewById(R.id.rBtn1);
        rBtn1 = (RadioButton)findViewById(R.id.rBtn2);
        rBtn2 = (RadioButton)findViewById(R.id.rBtn3);
        
        rBtn1.setEnabled(false);
        rBtn2.setEnabled(false);
        edtServerUri = (EditText)findViewById(R.id.edtServerUri);
        edtServerUri.setText("192.168.0.122");
        edtServerUri.setSelection(edtServerUri.getText().length());
        
        edtClientId = (EditText)findViewById(R.id.edtClientid);
        edtClientId.setText("4a87cf6a423e4174b96b8008d60dab68");
        edtUsername = (EditText)findViewById(R.id.edtUsername);
        edtUsername.setText("4a87cf6a423e4174b96b8008d60dab68");
        
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	
            	serverUri = edtServerUri.getText().toString();            	
            	clientId = edtClientId.getText().toString();
            	username = edtUsername.getText().toString();
            	            	
            	if("".equals(serverUri))
            	{
            		Toast.makeText(MQTTAndroidClient.this, "Insert Server URI", Toast.LENGTH_SHORT).show();;
            		return;
            	}              	
            	else
            	{
            		serverUri = "tcp://"+serverUri+":1884";		
            	}
            	
            	if("".equals(clientId))
            	{
            		Toast.makeText(MQTTAndroidClient.this, "Insert Client ID", Toast.LENGTH_SHORT).show();;
            		return;
            	}           	
            	            	
            	if("".equals(username))
            	{
            		Toast.makeText(MQTTAndroidClient.this, "Insert UserName", Toast.LENGTH_SHORT).show();
            		return;
            	}        
            	
            	connection = ConnectionMgr.getConnectionInstance(serverUri);
            	
            	
            	connection.setClientId(clientId);
            	connection.setUsername(username);
            	
                if(true == connection.connect())
                {
                    Intent intent = new Intent(MQTTAndroidClient.this, ConnectedActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MQTTAndroidClient.this, "Can't connect to MQTT Server", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if(serviceIsRunning())
		{
			stopService();
		}
		
		super.onDestroy();
	}
	
    @Override
    protected void onResume() {
        super.onResume();
        //updateButton();
    }


    private void stopService() {

        final Intent intent = new Intent(this, MQTTService.class);
        stopService(intent);
    }

    private boolean serviceIsRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (SERVICE_CLASSNAME.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
