package com.example.info;

import android.content.Context;
import android.telephony.TelephonyManager;

public class PhoneInfo {

	private Context cxt;
	
	public PhoneInfo(Context cxt){
		this.cxt=cxt;
	}

	
	public String getDeviceId(){
		TelephonyManager tm = (TelephonyManager)cxt.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getDeviceId();  		
	}
}
