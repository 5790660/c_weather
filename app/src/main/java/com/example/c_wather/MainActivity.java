package com.example.c_wather;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.info.LocationInfo;
import com.example.weatherData.DataCollection;
import com.example.weatherData.DataDeal;
import com.example.weatherData.HttpDownloader;
import com.example.weatherData.UrlSetting;



import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity ;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class MainActivity extends FragmentActivity  {

	private Fragment fragment_weather;
	private DrawerLayout mDrawerLayout;
	private FragmentActivity cxt;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cxt=this;
			
		fragment_weather = new FragmentWeather(this);  
		getSupportFragmentManager().beginTransaction().replace(R.id.main, fragment_weather).commit();
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		ImageButton button = (ImageButton) findViewById(R.id.imageButton);
        button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
					mDrawerLayout.closeDrawer(Gravity.LEFT);	
				} else {
					 mDrawerLayout.openDrawer(Gravity.LEFT);
				}
			}
        	
        });
        
        ListView listItem = (ListView)findViewById(R.id.left_drawer);
        ItemAdapter adapter = new ItemAdapter(this, getListItem());
        listItem.setAdapter(adapter);
        listItem.setOnItemClickListener(new ListListener());
	}
	
	private List<HashMap<String,String>> getListItem(){
		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
		String[] dataImg = {R.drawable.weather_item+"",R.drawable.shijing_item+""};
		String[] dataItem = {"天气","实景"};
		
		for (int i=0; i<dataImg.length; i++){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("image", dataImg[i]);
			map.put("text", dataItem[i]);
			listItem.add(map);
		}
		
		return listItem; 
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class ListListener implements OnItemClickListener{
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				if(position == 0){
					fragment_weather = new FragmentWeather(MainActivity.this);  
					getSupportFragmentManager().beginTransaction().replace(R.id.main, fragment_weather).commit();
				}
				else{
					Intent loginIntent=new Intent(MainActivity.this, LoginActivity.class);
					startActivity(loginIntent);
				}
			}
	    }
}


