package com.example.c_wather;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.weatherData.DataDeal;
import com.example.weatherData.HttpDownloader;
import com.example.weatherData.UrlSetting;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentWeather extends Fragment {
	private AppInit AppBaseInfoOP;		//获取||改变应用信息操作类
	private FragmentActivity cxt;
	private Handler handler;
	public static final String TAG = "FragmentWeather";  
	
	public FragmentWeather(FragmentActivity cxt){
		this.cxt=cxt;
	}
	
	public FragmentWeather(){
		
	}
	
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
	        Log.d(TAG, "onCreateView");  
	        return inflater.inflate(R.layout.fragment_weather, container, false);  
	    }  
	       
	    @Override
	    public void onAttach(Activity activity) {  
	        super.onAttach(activity);  
	        Log.d(TAG, "onAttach");  
	    }  
	       
	    @Override
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        AppBaseInfoOP=new AppInit(cxt);
			uiDataSet();
	        Log.d(TAG, "onCreate");  
	    }  
	       
	    @Override
	    public void onActivityCreated(Bundle savedInstanceState) {  
	        super.onActivityCreated(savedInstanceState);  
	        Log.d(TAG, "onActivityCreated");  
	    }  
	       
	    @Override
	    public void onStart() {  
	        super.onStart();  
	        Log.d(TAG, "onStart");  
	    }  
	       
	    @Override
	    public void onResume() {  
	        super.onResume();  
	        Log.d(TAG, "onResume");  
	    }  
	       
	    @Override
	    public void onPause() {  
	        super.onPause();  
	        Log.d(TAG, "onPause");  
	    }  
	       
	    @Override
	    public void onStop() {  
	        super.onStop();  
	        Log.d(TAG, "onStop");  
	    }  
	       
	    @Override
	    public void onDestroyView() {  
	        super.onDestroyView();  
	        Log.d(TAG, "onDestroyView");  
	    }  
	       
	    @Override
	    public void onDestroy() {  
	        super.onDestroy();  
	        Log.d(TAG, "onDestroy");  
	    }  
	       
	    @Override
	    public void onDetach() {  
	        super.onDetach();  
	        Log.d(TAG, "onDetach");  
	    }  
	    

		/**
		 * 页面数据初始化
		 */
		private void uiDataSet(){
			handler=new Handler(){
		        public void handleMessage(android.os.Message msg)
		        {
		        	if (msg.what==1){
		        		DataDeal.dataGet(cxt);
		        		TextView title=(TextView)cxt.findViewById(R.id.placeName);
		        		title.setText(AppRes.placeName);
		        	}
		        };
		    };
		
			class Deal implements Runnable{
				@Override
				public void run() {
					
					// TODO Auto-generated method stub
					HttpDownloader H=new HttpDownloader();
					String ljson=H.download(new UrlSetting(AppRes.PX, AppRes.PY, "xml", 0).getLocationUrl());
					AppBaseInfoOP.setPlaceName(DataDeal.getPlaceName(ljson));
					String wjson=H.download(new UrlSetting(AppRes.placeName, "xml").getWeatherUrl());
					 Message msg = new Message();
					 if (DataDeal.dataSet(cxt,wjson)){
						 msg.what=1;
						 msg.obj=wjson;
					 } else {
						 wjson=H.download(new UrlSetting(AppRes.city, "xml").getWeatherUrl());
						 DataDeal.dataSet(cxt,wjson);
						 msg.what=1;
						 msg.obj=wjson;
					 }
					 handler.sendMessage(msg);
				}
				
			}
			Thread task=new Thread(new Deal());
			task.start();
		}
		
		
	    
}
