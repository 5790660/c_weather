package com.example.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.example.c_wather.AppRes;

import android.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBService{
	
	private SQLiteDatabase dbase=null;
	
	public DBService(Context cxt) throws IOException{
		String databaseFilename=DBConfigure.DATABASE_PATH+"/"+DBConfigure.DATABASE_NAME;
		
		//sd卡中创建文件夹
		if (!new File(databaseFilename).exists()){
	        File path1 = new File(DBConfigure.DATABASE_PATH);
	        if (!path1.exists()) {
	            path1.mkdirs();
	        }
		
	    //将数据库移到sd卡中
			InputStream is = cxt.getResources().openRawResource(AppRes.weatherdb);
			FileOutputStream fos = new FileOutputStream(databaseFilename);
			byte[] buffer = new byte[8192];
			int count=0;
			while ((count = is.read(buffer))>0){
				fos.write(buffer,0,count);
			}
			
			fos.close();
			is.close();
		}
		
		this.dbase=SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);
	}
	
	/**
	 * 插入数据
	 * @param TableName 表名
	 * @param cv	KV值
	 */
	public void insertData(String TableName,ContentValues cv){
		 this.dbase.insert(TableName, null, cv);
	}
	
	/**
	 * 更新数据
	 * @param TableName	表名
	 * @param cv		KV值
	 * @param whereKey		where子句  example:"ID=?"
	 * @param whereValue	where子句值  example:new String[]{"1"}
	 */
	public void updateData(String TableName,ContentValues cv,String whereKey,String[] whereValue){
		this.dbase.update(TableName, cv, whereKey, whereValue);
	}
	
	/**
	 * 删除数据
	 * @param TableName		表名
	 * @param whereKey		where子句  example:"ID=?"
	 * @param whereValue	where子句值 example:new String[]{"1"}
	 */
	public void deleteData(String TableName,String whereKey,String[] whereValue){
		this.dbase.delete(TableName, whereKey, whereValue);
	}
	
	/**
	 * 获取数据
	 * @param selectStr	获取的SQL句  example:select * from user where ID=?
	 * @param selectValue	获取SQL句的值  example:new String[]{"1"}
	 * @return	游标
	 */
	public Cursor getData(String selectStr,String[] selectValue){
		Cursor cursor=this.dbase.rawQuery(selectStr, selectValue); 
		return cursor;
	}
	
	
}
