package com.example.logininstaui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logininstaui.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    TextView signUp;
    EditText editTextUsername, editTextPassword;
    Button loginBtn;
    ActivityLoginBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        signUp = findViewById(R.id.sign_up);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(signUpIntent);
                finish();
            }
        });

//        loginBtn = findViewById(R.id.loginBtn);
//        editTextUsername = findViewById(R.id.username);
//        editTextPassword = findViewById(R.id.password);
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, password;
                username = String.valueOf(binding.username.getText());
                password = String.valueOf(binding.password.getText());

                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "All field are mandatory", Toast.LENGTH_SHORT).show();
                }else{
                    boolean checkCredentials = databaseHelper.checkUsernamePassword(username, password);
                    System.out.println(username);
                    System.out.println(password);
                    if(checkCredentials){
                        Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                        Intent successIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(successIntent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}