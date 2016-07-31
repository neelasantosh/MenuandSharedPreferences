package com.example.menuandsharedpreferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	EditText editEmail, editPassword;
	Button buttonSignin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		editEmail = (EditText) findViewById(R.id.editText1);
		editPassword = (EditText) findViewById(R.id.editText2);
		buttonSignin = (Button) findViewById(R.id.button1);

		buttonSignin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// we read stored email and password
				SharedPreferences sp = getSharedPreferences("user data",
						MODE_PRIVATE);
				String oldEmail = sp.getString("email", null);// returns null
																// value if no
																// previous
																// emails and
																// passwords are
																// stored
				String oldPass = sp.getString("password", null);
				if (oldEmail == null || oldPass == null) {
					editEmail.setError("Please Register");
					return;
				}
				// validate edit text value with store value
				String email = editEmail.getText().toString();
				String password = editPassword.getText().toString();

				if (email.equals(oldEmail) && password.equals(password)) {
					Intent intentHome = new Intent(LoginActivity.this,
							HomeActivity.class);
					startActivity(intentHome);
				} else {
					Toast.makeText(LoginActivity.this,
							"InCorrect Email/password", Toast.LENGTH_LONG).show();
				}
			}
		});

	}// eof create

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.login_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// handle menu item selection

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.optionRegister: {
			Intent in = new Intent(LoginActivity.this,
					RegistrationAvtivity.class);
			startActivity(in);
		}

			break;
		case R.id.optionExit:
			finish();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
