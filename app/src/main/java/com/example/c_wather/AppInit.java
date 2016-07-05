package com.example.c_wather;

import java.io.IOException;

import android.content.Context;

import com.example.db.DBService;
import com.example.info.LocationInfo;

public class AppInit {
	
	private LocationInfo location;
	
	public AppInit(Context cxt){
		try {
			AppRes.db=new DBService(cxt);
			location=new LocationInfo(cxt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		init();
		
	}
	
	private void init(){
		//setLocation(29.455963,106.428303);
		setLocation(location.getLocation()[0],location.getLocation()[1]);
	}
		
	private void setLocation(double x, double y){
		AppRes.PX=x;
		AppRes.PY=y;
	}
	
	public void setPlaceName(String[] placeName){
		AppRes.placeName=placeName[0];
		AppRes.city=placeName[1];
	}
	
}	
