package com.example.hp.login.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hp.login.R;
import com.example.hp.login.RequestHandler;
import com.example.hp.login.SharedPrefManager;
import com.example.hp.login.URLs;
import com.example.hp.login.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    EditText editTextfirst_name, editTextsir_name, editTextemail_address, editTextPassword, editTextPhone, editTextbirth_date, edittextedit_token;
//    RadioGroup radioGroupGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if the user is already logged in we will directly start the profile activity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MoviesActivity.class));
            return;
        }

        editTextfirst_name = (EditText) findViewById(R.id.editTextfirst_name);
        editTextsir_name = (EditText) findViewById(R.id.editTextsir_name);
        editTextemail_address = (EditText) findViewById(R.id.editTextemail_address);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextbirth_date = (EditText) findViewById(R.id.editTextbirth_date);
        edittextedit_token = (EditText) findViewById(R.id.edittextedit_token);
//        radioGroupGender = (RadioGroup) findViewById(R.id.radioGender);


        findViewById(R.id.buttonRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on button register
                //here we will register the user to server
                registerUser();


            }
        });

        findViewById(R.id.textViewLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on login
                //we will open the login screen
                finish();
                startActivity(new Intent(MainActivity.this, MoviesActivity.class));
            }
        });

    }

    private void registerUser() {
        final String first_name = editTextfirst_name.getText().toString().trim();
        final String sir_name = editTextsir_name.getText().toString().trim();
        final String email_address = editTextemail_address.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String birth_date = editTextbirth_date.getText().toString().trim();
        final String phone_number = editTextPhone.getText().toString().trim();
        final String _token = edittextedit_token.getText().toString().trim();

//        final String gender = ((RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();

        //first we will do the validations

        if (TextUtils.isEmpty(first_name)) {
            editTextfirst_name.setError("Please enter first_name");
            editTextfirst_name.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email_address)) {
            editTextemail_address.setError("Please enter your email");
            editTextemail_address.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email_address).matches()) {
            editTextemail_address.setError("Enter a valid email");
            editTextemail_address.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Enter a password");
            editTextPassword.requestFocus();
            return;
        }

        //if it passes all the validations

        class RegisterUser extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("first_name", first_name);
                params.put("sir_name", sir_name);
                params.put("email_address", email_address);
                params.put("password", password);
                params.put("phone_number", phone_number);
                params.put("birth_date", birth_date);
                params.put("_token", _token);
//                params.put("gender", gender);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_REGISTER, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                progressBar.setVisibility(View.GONE);

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    obj.getString("response"); {
                        Toast.makeText(getApplicationContext(), obj.getString("msg"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("User");

                        //creating a new user object
                        User user = new User(
                                userJson.getInt("id"),
                                userJson.getString("sir_name"),
                                userJson.getString("first_name"),
                                userJson.getString("email_address"),
                                userJson.getString("phone_number"),
                                userJson.getString("birth_date"),
                                userJson.getString("gender")
                        );

                        //storing the user in shared preferences
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), MoviesActivity.class));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task
        RegisterUser ru = new RegisterUser();
        ru.execute();
    }

}