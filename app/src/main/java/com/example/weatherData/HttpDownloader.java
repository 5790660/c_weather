package com.example.weatherData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;


public class HttpDownloader {
	private URL url = null; 

	public String download(String urlStr){ 
		HttpGet httpRequest = new HttpGet(urlStr);
		try{
		HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest); 
		if(httpResponse.getStatusLine().getStatusCode() == 200)   
        {  
          /*读*/  
          String strResult = EntityUtils.toString(httpResponse.getEntity());  
          return strResult;
          
        }  
		} catch (Exception e){
			
		}
		return "";
	}
		
}
