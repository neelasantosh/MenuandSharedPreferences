package com.example.menuandsharedpreferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

public class HomeActivity extends Activity {
	TextView textName, textEmail;
	Button buttonCall;
	ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		textName = (TextView) findViewById(R.id.textView2);
		textEmail = (TextView) findViewById(R.id.textView3);
		buttonCall = (Button) findViewById(R.id.button1);
		img=(ImageView) findViewById(R.id.imageView1);
		
		final SharedPreferences sp= getSharedPreferences("user data",MODE_PRIVATE);
		textName.setText(sp.getString("name", null));
		textEmail.setText(sp.getString("email", null));
		
		final String number = sp.getString("mobile", null);
		
		buttonCall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intentcall=new Intent();
				intentcall.setAction("android.intent.action.CALL");
				
				Uri u= Uri.parse("tel:"+number);
				intentcall.setData(u);
				
				startActivity(intentcall);
			}
		});
		
		img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Intent intentimg=new Intent(Intent.ACTION_SENDTO);
				Uri u= Uri.parse("mailto:"+sp.getString("mail", null));
				intentimg.setData(u);
				intentimg.putExtra("subject", "my subject");
				intentimg.putExtra("body", "my message");
				startActivity(intentimg);
				
			}
		});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.home_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		SharedPreferences sp= getSharedPreferences("user data", MODE_PRIVATE);
		sp.edit().clear().commit();
		finish();
		
		return super.onOptionsItemSelected(item);
	}
}
