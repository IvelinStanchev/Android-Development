package com.example.usingfragments;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("NewApi")
public class ProductFragment extends Fragment {
	
	String name;
	String category;
	int id;
	int quantity;
	double price;
	
	TextView nameTextView;
	TextView categoryTextView;
	TextView idTextView;
	TextView quantityTextView;
	TextView priceTextView;
	
	public ProductFragment(String name, String category, int id, int quantity, double price) {
		this.name = name;
		this.category = category;
		this.id = id;
		this.quantity = quantity;
		this.price = price;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_layout, container, false);
		nameTextView = (TextView) view.findViewById(R.id.tv_name);
		categoryTextView = (TextView) view.findViewById(R.id.tv_category);
		idTextView = (TextView) view.findViewById(R.id.tv_id);
		quantityTextView = (TextView) view.findViewById(R.id.tv_quantity);
		priceTextView = (TextView) view.findViewById(R.id.tv_price);
		
		nameTextView.setText(name);
		categoryTextView.setText(category);
		idTextView.setText(String.valueOf(id));
		quantityTextView.setText(String.valueOf(quantity));
		priceTextView.setText(String.valueOf(price));
		
		return view;
	}
}
