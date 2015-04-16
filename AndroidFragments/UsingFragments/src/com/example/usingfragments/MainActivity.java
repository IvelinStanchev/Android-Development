package com.example.usingfragments;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

	private ArrayList<Product> products;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		products = new ArrayList<Product>();

		for (int i = 0; i < 10; i++) {
			Product product = new Product("Name " + i, "Category " + i, i, i, i);
			products.add(product);
		}

		ListView productListView = (ListView) findViewById(R.id.lv_products);
		ProductAdapter adapter = new ProductAdapter(this,
				R.layout.list_products, products);
		productListView.setAdapter(adapter);

		productListView.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> listView, View v, int position,
			long id) {
		Product clickedProduct = products.get(position);

		Fragment fragment = new ProductFragment(String.valueOf(clickedProduct.getName()), 
				String.valueOf(clickedProduct.getCategory()), clickedProduct.getId(), 
				 clickedProduct.getQuantity(), clickedProduct.getPrice());
		
		FragmentManager fm = getFragmentManager();
		FragmentTransaction fragmentTransaction = fm.beginTransaction();
		fragmentTransaction.replace(R.id.maincontainer, fragment);
		
		fragmentTransaction.commit();
	}
}
