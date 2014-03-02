package com.example.testthedb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.example.testthedb.POI;
import com.example.testthedb.MySQLiteHelper;


import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {

	
	// For displaying list items
	ArrayList<String> values;
	TextView tView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);

	    values = new ArrayList<String>();
		
		MySQLiteHelper db = new MySQLiteHelper(this);
	    
		values = db.getEntryFromNameContains("name", "Calgary");

	    // Create the text view
	    TextView textView = new TextView(this);
	    textView.setTextSize(40);
	    textView.setText(db.getPOICount() + "");
	    
	    // Set the text view as the activity layout
	    setContentView(textView);
		
//	    // Set ListView
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//		android.R.layout.simple_list_item_1, values);
//		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
