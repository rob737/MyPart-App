package com.example.temp;

import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
public class MyOnItemSelectedListener implements OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView parent, View view, int pos, long id) {
  Toast toast=  Toast.makeText(parent.getContext(), "Category: " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT);
  toast.setGravity(Gravity.TOP, 0, 0);
  toast.show();
  
    }
    @Override
    public void onNothingSelected(AdapterView parent) {
    }
}
