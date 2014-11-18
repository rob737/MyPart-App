package com.example.temp;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;


public class Mactivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);
		
	}	
public void selectFrag(View view)
{
Fragment fr=null;
if(view == findViewById(R.id.imageView3))
{
	Intent intent=new Intent("com.example.temp.MainActivity");
	startActivity(intent);
}
	else if(view== findViewById(R.id.imageView2))
{	
	fr=new FragmentTwo();
}
	else
	{
		fr=new FragmentOne();
	}
FragmentManager fm= getFragmentManager();
FragmentTransaction fragmentTransaction=fm.beginTransaction();
fragmentTransaction.replace(R.id.fragment_place,fr);
fragmentTransaction.commit();
}

}