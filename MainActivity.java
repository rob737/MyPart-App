package com.example.temp;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity {
	
	GoogleMap googleMap;
	MarkerOptions markerOptions;
	LatLng latLng;
	
	private TextView check;
	Spinner spinner2;
	Button btnClosePopup,cont;

	private PopupWindow pwindo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		cont=(Button)findViewById(R.id.conti);
		check=(TextView)findViewById(R.id.tv);
		addItemsOnSpinner2();
		SupportMapFragment supportMapFragment = (SupportMapFragment) 
				getSupportFragmentManager().findFragmentById(R.id.map);

		// Getting a reference to the map
		googleMap = supportMapFragment.getMap();
		
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		googleMap.setMyLocationEnabled(true);
		
		// Getting reference to btn_find of the layout activity_main
        Button btn_find = (Button) findViewById(R.id.btn_find);
        
        // Defining button click event listener for the find button
        OnClickListener findClickListener = new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// Getting reference to EditText to get the user input location
				EditText etLocation = (EditText) findViewById(R.id.et_location);
				
				// Getting user input location
				String location = etLocation.getText().toString();
				
				if(location!=null && !location.equals("")){
					new GeocoderTask().execute(location);
				}
				
				/*try {
					// We need to get the instance of the LayoutInflater
					LayoutInflater inflater = (LayoutInflater) MainActivity.this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					View layout = inflater.inflate(R.layout.popup,
					(ViewGroup) findViewById(R.id.pop_element));
					pwindo = new PopupWindow(layout, 1000, 1000, true);
					pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
					btnClosePopup = (Button) layout.findViewById(R.id.btn_close_popup);
					btnClosePopup.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							pwindo.dismiss();
						}
					});
					} catch (Exception e) {
					e.printStackTrace();
					}*/
			}
		};
		
		// Setting button click event listener for the find button
		btn_find.setOnClickListener(findClickListener);		
		
		
			}
	public void addItemsOnSpinner2() {
	    spinner2 = (Spinner) findViewById(R.id.spinnr2);
	    List list = new ArrayList();
	    list.add("COne");
	    list.add("CTwo");
	    list.add("CThree");
	    list.add("CFour");
	    ArrayAdapter dataAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, list);
	    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner2.setAdapter(dataAdapter);
	  }
	public void addListenerOnSpinnerItemSelection() {
	    spinner2 = (Spinner) findViewById(R.id.spinnr2);
	    spinner2.setOnItemSelectedListener(new MyOnItemSelectedListener());
	  }
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}*/
	public void verify(View view){
	      new Check(this,check).execute("sachin");

	   }

	
	// An AsyncTask class for accessing the GeoCoding Web Service
		private class GeocoderTask extends AsyncTask<String, Void, List<Address>>{

			@Override
			protected List<Address> doInBackground(String... locationName) {
				// Creating an instance of Geocoder class
				Geocoder geocoder = new Geocoder(getBaseContext());
				List<Address> addresses = null;
				
				try {
					// Getting a maximum of 3 Address that matches the input text
					addresses = geocoder.getFromLocationName(locationName[0], 3);
				} catch (IOException e) {
					e.printStackTrace();
				}			
				return addresses;
			}
			
			
			@Override
			protected void onPostExecute(List<Address> addresses) {			
		        
		        if(addresses==null || addresses.size()==0){
					Toast.makeText(getBaseContext(), "No Location found", Toast.LENGTH_SHORT).show();
				}
		        
		        // Clears all the existing markers on the map
		        googleMap.clear();
				
		        // Adding Markers on Google Map for each matching address
				for(int i=0;i<addresses.size();i++){				
					
					Address address = (Address) addresses.get(i);
					
			        // Creating an instance of GeoPoint, to display in Google Map
			        latLng = new LatLng(address.getLatitude(), address.getLongitude());
			        
			        String addressText = String.format("%s, %s",
	                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
	                        address.getCountryName());

			        markerOptions = new MarkerOptions();
			        markerOptions.position(latLng);
			        markerOptions.title(addressText);

			        googleMap.addMarker(markerOptions);
			        
			        // Locate the first location
			        if(i==0)			        	
						googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng)); 	
				}			
			}		
		}
}