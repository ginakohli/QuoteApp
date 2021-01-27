package com.example.lifequote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class SignIn_Activity extends AppCompatActivity implements View.OnClickListener{
    private TextView lRegister,forgotPassword;
    private EditText lEmail,lPassword;
    private Button lLogin;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_);
        lRegister = (TextView)findViewById(R.id.register2);
        lRegister.setOnClickListener(this);
        lLogin = (Button)findViewById(R.id.reset);
        lLogin.setOnClickListener(this);
        lEmail = (EditText)findViewById(R.id.email);
        lPassword = (EditText)findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        forgotPassword = (TextView)findViewById(R.id.forgotpwd);
        forgotPassword.setOnClickListener(this);
        getSupportActionBar().setTitle(("Sign In"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.register2:
                startActivity(new Intent(this,SignUp_Activity.class));
                break;
            case R.id.reset:
                userLogin();
                break;
            case R.id.forgotpwd:
                startActivity(new Intent(this,ForgotPassword.class));
                break;
        }
    }
    private void userLogin() {
        String email = lEmail.getText().toString().trim();
        String password = lPassword.getText().toString().trim();
        if(email.isEmpty()){
            lEmail.setError("Email is Required");
            lEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            lEmail.setError("Please Enter a valid Email");
            lEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            lPassword.setError("Password is Required");
            lPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            lPassword.setError("Password should have minimum of 6 characters");
            lPassword.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(SignIn_Activity.this, quote1_Activity.class));
                }else {
                    Toast.makeText(SignIn_Activity.this,"Failed to login! Please check your credentials",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}