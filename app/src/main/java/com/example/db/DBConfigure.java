package com.example.db;

import android.os.Environment;

public class DBConfigure {
	//数据库名
	public final static String DATABASE_NAME = "weather.db";
	
	//数据库路径
	public final static String DATABASE_PATH = Environment.getExternalStorageDirectory()+"/cube_weather";
	
	//数据库版本号
	public final static int DATABASE_VERSION=1;
	
	
}
