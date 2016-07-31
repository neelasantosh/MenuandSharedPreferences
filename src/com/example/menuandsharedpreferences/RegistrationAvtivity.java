package com.example.menuandsharedpreferences;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationAvtivity extends Activity {
	EditText editName, editEmail, editMobile, editPassword;
	Button buttonSubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);
		editName = (EditText) findViewById(R.id.editText1);
		editEmail = (EditText) findViewById(R.id.editText2);
		editMobile = (EditText) findViewById(R.id.editText3);
		editPassword = (EditText) findViewById(R.id.editText4);
		buttonSubmit = (Button) findViewById(R.id.button1);

		buttonSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = editName.getText().toString();
				if (name.equals("")) {
					editName.setError("Name cannot be Blank");
					return;

				}
				String email = editEmail.getText().toString();
				String emailPattern = "[a-zA-Z0-9@.]{2,}";
				Pattern p1 = Pattern.compile(emailPattern);
				Matcher matcher = p1.matcher(email);
				if (!matcher.matches()) {
					editEmail.setError("Invalid Email");
					return;
				}
				// validate mobile number
				String number = editMobile.getText().toString();
				String mobilePattern = "\\d{10}";
				Pattern p2 = Pattern.compile(mobilePattern);
				Matcher matcher2 = p2.matcher(number);
				if (!matcher2.matches()) {
					editMobile.setError("Invalid Mobile Number");
					return;
				}
				String password = editPassword.getText().toString();
				if (password.length() < 6) {
					editPassword.setError("Invalid Password");
					return;
				}

				// save data in Shared Preferences
				// 1.open preference file
				SharedPreferences sp = getSharedPreferences("user data",
						MODE_PRIVATE);
				//2.Get Editor Object Preference
				SharedPreferences.Editor editor = sp.edit();
				//3.add Data to file using editor
				editor.putString("name", name);
				editor.putString("email", email);
				editor.putString("mobile", number);
				editor.putString("password", password);
				editor.commit();
				//clear all edit box
				editEmail.setText("");
				editName.setText("");
				editMobile.setText("");
				editPassword.setText("");
				
			}
		});
	}
}