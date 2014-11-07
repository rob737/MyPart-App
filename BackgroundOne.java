package com.example.temp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BackgroundOne extends Activity implements AnimationListener {

	RelativeLayout screen;
	// Animation
	Animation animFadeOut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fadeout);
		screen=(RelativeLayout)findViewById(R.id.screen);
		
		// load the animation
		animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.fade_out);

		// set animation listener
		animFadeOut.setAnimationListener(this);
		screen.startAnimation(animFadeOut);

		
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// Take any action after completing the animation
		
		// check for fade out animation
		if (animation == animFadeOut) {
			/*Toast.makeText(getApplicationContext(), "Animation Stopped",
					Toast.LENGTH_SHORT).show();*/
			Intent intent =new Intent("com.example.temp.Mactivity");
		startActivity(intent);
		
		}

	}

	

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

}