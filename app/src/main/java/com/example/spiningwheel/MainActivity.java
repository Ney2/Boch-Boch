package com.example.spiningwheel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password, rePassword;
    Button signup,signIn;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        rePassword = (EditText) findViewById(R.id.rePassword);
        signup = (Button) findViewById(R.id.signup);
        signIn = (Button) findViewById(R.id.signIn);
        DB = new DBHelper(this);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
               startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String user = username.getText().toString();
              String pass = password.getText().toString();
              String rePass = rePassword.getText().toString();

              if(user.equals("")|| pass.equals("")|| rePass.equals("")) {
                  Toast.makeText(MainActivity.this, "Please Enter All the Required Fields", Toast.LENGTH_SHORT).show();
              }
              else {
                  if(pass.equals(rePass)) {
                      Boolean checkUsername = DB.checkUsername(user);
                      if(checkUsername==false) {
                          Boolean insert = DB.insertData(user, pass);
                          if(insert==true){
                              Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                              Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                              startActivity(intent);
                          }
                          else {
                              Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                          }
                      }

                      else {
                          Toast.makeText(MainActivity.this, "User already exist", Toast.LENGTH_SHORT).show();
                      }
                  }

                  else {
                      Toast.makeText(MainActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                  }
              }
            }
        });
    }
}