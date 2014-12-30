package com.cresprit.mqtt;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cresprit.mqtt.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;




public class MessageAdapter extends BaseAdapter{
	Context context;
	JSONObject  json;
	
	class MessageHolder {
		View v;
		
		TextView message;
		TextView time;
		
		MessageHolder(View v){
			this.v = v;
		}

		TextView getTextView(){
			if (message == null) {
				message = (TextView)v.findViewById(R.id.message);
			}
			return message;
		}
		
		TextView getTextViewTime(){
			if(time == null) {
				time = (TextView)v.findViewById(R.id.time);
			}
			return time;
		}
	}
	
	
	public MessageAdapter(Context _context, int textViewResourceId,
			String[] objects) {
	
		// TODO Auto-generated constructor stub
		context = _context;
	}



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = null;
		TextView message;
		TextView time;
		String value ="";
		//JSONObject data;
		JSONObject data_streams;
		JSONArray data_points;
		JSONObject data;
		JSONArray jvalue;
		JSONObject jvalue2;
		
		String text="";
		String datetime ="";
		
		MessageHolder holder;

		if( convertView == null ) {
			v = LayoutInflater.from(context).inflate(R.layout.listview, null);
			holder = new MessageHolder(v);
			v.setTag(holder);
		}
		else {
			v = convertView;
			holder = (MessageHolder) v.getTag();
		}

		message = holder.getTextView();
		
		try {
			
			json = new JSONObject(ConnectionMgr.getMessages().get(position));
			data_streams = json.getJSONObject("data");
			data_points = data_streams.getJSONArray("data_streams");
			data = data_points.getJSONObject(0);
			jvalue = data.getJSONArray("data_points");
			jvalue2 = jvalue.getJSONObject(0);
			text = jvalue2.get("v").toString();
			datetime = jvalue2.get("t").toString();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		message.setText(text);		
		time  = holder.getTextViewTime();
		time.setText(datetime);		
		return v;
	}



	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int size =ConnectionMgr.getMessages().size();
		Log.i("", "size : "+size);
		return size;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return ConnectionMgr.getMessages().get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}