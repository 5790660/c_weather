package com.example.weatherData;

import android.util.Log;

public class UrlSetting {
	private String akM="TCqlKiKfwQRoCkk4gjfs96tS";
	private String ak="F3d7aba7470d4934fd94e91f2f04ab1e";
	private String WhttpPath="http://api.map.baidu.com/telematics/v3/weather?";
	private String DhttpPath="http://api.map.baidu.com/geocoder/v2/?";
	private String location;
	private String output;
	private int pois;
	
	public UrlSetting(String location, String output){
		this.location=location;
		this.output=output;
	}
	
	public UrlSetting(double PX, double PY, String output, int pois){
		this.location=PX+","+PY;
		this.output=output;
		this.pois=pois;
	}
	
	public String getLocationUrl(){
		return DhttpPath+"location="+location+"&output="+output+"&ak="+ak+"&pois"+pois;
	}
	
	public String getWeatherUrl(){
		return WhttpPath+"location="+location+"&output="+output+"&ak="+ak;
	}
}
