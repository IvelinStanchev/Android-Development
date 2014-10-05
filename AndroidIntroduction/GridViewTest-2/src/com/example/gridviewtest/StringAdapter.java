package com.example.gridviewtest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StringAdapter extends BaseAdapter {
	private Context mContext;

    public StringAdapter(Context c) {
        mContext = c;
    }
	
    public int getCount() {
        return info.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
        	textView = new TextView(mContext);
        } else {
        	textView = (TextView) convertView;
        }

        textView.setText(info[position]);
        return textView;
    }

    private String[] info = { 
    	"Pesho1", "Pesho2", "Pesho3", "Pesho4", "Pesho5",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10",
    	"Pesho6", "Pesho7", "Pesho8", "Pesho9", "Pesho10"
    };
}
