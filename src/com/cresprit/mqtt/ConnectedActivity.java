package com.cresprit.mqtt;
import com.cresprit.mqtt.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class ConnectedActivity extends Activity {
	private Button btnSubscribe;
	private Button btnPublish;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.connected);

        btnSubscribe = (Button) findViewById(R.id.btnSubscribe);
        btnPublish = (Button) findViewById(R.id.btnPublish);
        
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	Intent intent = new Intent(ConnectedActivity.this, SubscribeActivity.class);
                startActivity(intent); 
            }
        });
        
        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	Intent intent = new Intent(ConnectedActivity.this, PublishActivity.class);
                startActivity(intent); 
            }
        });

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	
}