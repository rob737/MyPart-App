package com.example.temp;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentOne extends Fragment {
	TextView tv1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_one,container,false);
		tv1=(TextView)view.findViewById(R.id.textView1);
		String text = "<font color=#cc0029>Robin</font> <font color=#ffcc00>Srivastava</font>";
		tv1.setText(Html.fromHtml(text));
		return view;
	}

	
	

}
