package com.cresprit.mqtt;

import android.content.Context;

import java.lang.String;
import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.MqttException;

public class ConnectionMgr{
	static Connection connection;
	private static String serverUri;
	private static ArrayList<String> list;
	private static ArrayList<String> date;
	public static String MSG_RECEIVE = "com.cresprit.mqtt.ConnectionMgr.receive_message";
	private static MessageAdapter adapter;
	
	
	public ConnectionMgr(String _serverUri)
	{
		serverUri = _serverUri;
		connection = new Connection(_serverUri);
		if(list == null)
			list = new ArrayList();
		if(date == null)
			date = new ArrayList();
	}
	
	public static Connection getConnectionInstance()
	{
		if(connection == null)
		{
			connection = new Connection(serverUri);
		}
		
		if(list == null)
			list = new ArrayList();
		if(date == null)
			date = new ArrayList();
		return connection;
		
	}
	
	public static Connection getConnectionInstance(String serverUri)
	{
		if(connection == null)
		{
			connection = new Connection(serverUri);
		}
		
		if(list == null)
			list = new ArrayList();
		if(date == null)
			date = new ArrayList();
		return connection;
		
	}
	
	public static ArrayList<String> getMessages()
	{
		return list;
	}
	
	public static ArrayList<String> getDates()
	{
		return date;
	}
	
	public static MessageAdapter getAdapter()
	{
		return adapter;
	}
	
	public static void setAdapter(MessageAdapter _adapter)
	{
		adapter = _adapter;
	}
}