package com.example.weatherData;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.example.c_wather.R;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.util.Xml;
import android.widget.ImageView;
import android.widget.TextView;

public class DataDeal {
	public static DataCollection[] data=new DataCollection[5];
	private static FragmentActivity cxt;
	public static boolean dataSet(FragmentActivity cxt,String xml){
		
		DataDeal.cxt=cxt;
		
		for (int i = 0; i<5; i++){
			data[i]=new DataCollection();
		}
		
		int n=-1;
		
		XmlPullParser parser = Xml.newPullParser();  
		try {
			parser.setInput(new StringReader(xml));
		
			int event = parser.getEventType();  
			while (event != XmlPullParser.END_DOCUMENT) {  
				switch (event) {  
				case XmlPullParser.START_DOCUMENT:  
		        break;  
				case XmlPullParser.START_TAG:  
					if ("date".equals(parser.getName())){
						if (n==-1){
							parser.next();
							String[] s=parser.getText().split("-");
							data[4].date=s[1]+"/"+s[2];
						}
						n++;
					}
					
					if (n>0){
						dataDeal(n-1,parser);
					}
		        break;  
				case XmlPullParser.END_TAG:  
		        break;  
				}  
				event = parser.next();  
			} 
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		if (n==0) return false; else return true;	
	}
	
	public static void dataGet(FragmentActivity cxt){
		TextView temp=(TextView) cxt.findViewById(R.id.firstPartTextView1);
		TextView date=(TextView) cxt.findViewById(R.id.firstPartTextView2);
		
		ImageView w=(ImageView) cxt.findViewById(R.id.firstPartTextImg);
		ImageView w1=(ImageView) cxt.findViewById(R.id.secendPartOneImg);
		ImageView w2=(ImageView) cxt.findViewById(R.id.secendPartTwoImg);
		ImageView w3=(ImageView) cxt.findViewById(R.id.secendPartThreeImg);
		ImageView w4=(ImageView) cxt.findViewById(R.id.secendPartFourImg);
		
		TextView temp1=(TextView) cxt.findViewById(R.id.secendPartOneTextView1);
		TextView temp2=(TextView) cxt.findViewById(R.id.secendPartTwoTextView1);
		TextView temp3=(TextView) cxt.findViewById(R.id.secendPartThreeTextView1);
		TextView temp4=(TextView) cxt.findViewById(R.id.secendPartFourTextView1);
		
		TextView date1=(TextView) cxt.findViewById(R.id.secendPartOneTextView2);
		TextView date2=(TextView) cxt.findViewById(R.id.secendPartTwoTextView2);
		TextView date3=(TextView) cxt.findViewById(R.id.secendPartThreeTextView2);
		TextView date4=(TextView) cxt.findViewById(R.id.secendPartFourTextView2);
		
		temp1.setText(data[0].temperature);
		temp2.setText(data[1].temperature);
		temp3.setText(data[2].temperature);
		temp4.setText(data[3].temperature);
		temp.setText(data[4].temperature);
		
		date1.setText(data[0].date);
		date2.setText(data[1].date);
		date3.setText(data[2].date);
		date4.setText(data[3].date);
		date.setText(data[4].date);
		
		w.setImageResource(data[0].weatherImg);
		w1.setImageResource(data[0].weatherImg);
		w2.setImageResource(data[1].weatherImg);
		w3.setImageResource(data[2].weatherImg);
		w4.setImageResource(data[3].weatherImg);
	}
	
	private static void dataDeal(int index, XmlPullParser parser) throws XmlPullParserException, IOException{

		HashMap<String, Integer> m = new HashMap<String, Integer>();
		m.put("date", 1);
		m.put("weather", 2);
		m.put("wind", 3);
		m.put("temperature", 4);
		m.put("dayPictureUrl", 5);
			
		if ("dayPictureUrl".equals(parser.getName()) || "date".equals(parser.getName()) || "temperature".equals(parser.getName()) || "weather".equals(parser.getName()) || "wind".equals(parser.getName()))
		switch (m.get(parser.getName())){
		case 1:
			parser.next();
			if (index==0){
				String s=parser.getText();
				data[index].date=s.substring(0,2);
				data[4].temperature=s.substring(10, s.length()-1);
			} else {
				data[index].date=parser.getText().toString();
			}
			
			break;
		case 2:
			parser.next();
			data[index].weatherName=parser.getText().toString();
			break;
		case 3:
			parser.next();
			data[index].wind=parser.getText().toString();
			break;
		case 4:
			parser.next();
			data[index].temperature=parser.getText().toString();
			break;
		case 5:
			parser.next();		
			data[index].weatherImg=getWeatherImg(parser.getText());
			break;
		}
	}
	
	private static int getWeatherImg(String img){
		String[] s=img.split("/");
		String image=s[s.length-1].substring(0,s[s.length-1].length()-4);
		int id = cxt.getResources().getIdentifier(     
                 image,      
                "drawable", cxt.getPackageName()); 
		
		if (id==0){
			id=R.drawable.other;
		}
		
		return id;
	}
	
	public static String[] getPlaceName(String xml) {
		int flag=0;
		String[] p = new String[2];
		XmlPullParser parser = Xml.newPullParser();  
		try {
			parser.setInput(new StringReader(xml));
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int event;
		try {
			event = parser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT && flag<=1) {
			switch (event) {  
			case XmlPullParser.START_DOCUMENT:  
	        break;  
			case XmlPullParser.START_TAG:  
			if ("district".equals(parser.getName()) || "city".equals(parser.getName())){
				parser.next();
				p[flag]=parser.getText();
				flag++;		
			}
	        break;  
			case XmlPullParser.END_TAG:  
	        break;  
			}  
			event = parser.next();  
			
		 }
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return p;
	}
}
