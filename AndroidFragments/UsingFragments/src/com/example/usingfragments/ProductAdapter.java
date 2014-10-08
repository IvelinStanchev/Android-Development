package com.example.usingfragments;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ProductAdapter extends ArrayAdapter<Product> {

	private Context context;
	private int layoutId;
	private List<Product> data;
	
	public ProductAdapter(Context context, int resource, List<Product> objects) {
		super(context, resource, objects);
		this.context = context;
		this.layoutId = resource;
		this.data = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = ((Activity)context).getLayoutInflater();
		View row = inflater.inflate(layoutId, parent, false);
		
		TextView tvName = (TextView) row.findViewById(R.id.tv_productName);
		TextView tvCategory = (TextView) row.findViewById(R.id.tv_productCategory);
		tvName.setText(data.get(position).getName());
		tvCategory.setText(data.get(position).getCategory());
		
		return row;
	}

}
