package com.integrate.prominent;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.app.Fragment;
import android.app.FragmentTransaction;

public class Prefs extends PreferenceActivity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
	}
}
