    package com.example.hp.login.ui;

    import android.content.Intent;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.TextView;

    import com.example.hp.login.R;
    import com.example.hp.login.SharedPrefManager;
    import com.example.hp.login.model.User;

    public class ProfileActivity extends AppCompatActivity {

        TextView textViewId, textViewUsername, textViewEmail, textViewGender;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile);

            //if the user is not logged in
            //starting the login activity
            if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
                finish();
                startActivity(new Intent(this, LoginActivity.class));
            }


            textViewId = (TextView) findViewById(R.id.textViewId);
            textViewUsername = (TextView) findViewById(R.id.textViewUsername);
            textViewEmail = (TextView) findViewById(R.id.textViewEmail);
            textViewGender = (TextView) findViewById(R.id.textViewGender);


            //getting the current user
            User user = SharedPrefManager.getInstance(this).getUser();

            //setting the values to the textviews

            textViewUsername.setText(user.getFirst_name());
            textViewEmail.setText(user.getEmail_address());
            textViewGender.setText(user.getGender());

            //when the user presses logout button
            //calling the logout method
            findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    SharedPrefManager.getInstance(getApplicationContext()).logout();
                }
            });
        }
    }