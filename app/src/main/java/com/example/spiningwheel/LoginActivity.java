package com.example.spiningwheel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button signIn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.usernameL);
        password = (EditText) findViewById(R.id.passwordL);
        signIn = (Button) findViewById(R.id.signInL);
        DB = new DBHelper(this);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String user = username.getText().toString();
               String pass = password.getText().toString();

               if(user.equals("") || pass.equals("")) {
                   Toast.makeText(LoginActivity.this, "Please enter all the required fields ", Toast.LENGTH_SHORT).show();
               }
               else {
                   Boolean checkuserpass = DB.checkUsernamePassword(user, pass);
                   if(checkuserpass == true) {
                       Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                       intent.putExtra("username", user);
                       startActivity(intent);
                   }
                   else {
                       Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                   }
               }
            }
        });


    }
}