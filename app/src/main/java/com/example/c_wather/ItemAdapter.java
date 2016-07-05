package com.example.c_wather;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

	private List<HashMap<String, String>> list;
	private Context cxt;

	public ItemAdapter(Context cxt, List<HashMap<String, String>> list) {
		this.list = list;
		this.cxt = cxt;
	}

	public ItemAdapter() {

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater mInflater = LayoutInflater.from(cxt);
		//产生一个View
		View view = null;
		view = mInflater.inflate(R.layout.left_list_item, null);
		
		ImageView image = (ImageView)view.findViewById(R.id.imageviewList);
	    image.setImageResource(Integer.parseInt(list.get(position).get("image")));
	    
	    TextView text = (TextView)view.findViewById(R.id.textviewList);
	    text.setText(list.get(position).get("text"));
	    
		return view;
	}
}
