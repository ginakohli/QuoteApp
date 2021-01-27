package com.example.lifequote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaCodec;
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
import com.google.firebase.database.FirebaseDatabase;
import java.util.regex.Pattern;
public class SignUp_Activity extends AppCompatActivity implements View.OnClickListener {
    TextView Alreadyhaveaccount ;
    private EditText rFullName,rPhone,rEmail,rPassword;
    private TextView rRegister;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);
        mAuth = FirebaseAuth.getInstance();
        rRegister = (Button) findViewById(R.id.Register);
        rRegister.setOnClickListener(this);
        rFullName = (EditText) findViewById(R.id.UserName);
        rPhone = (EditText) findViewById(R.id.PhoneNo);
        rEmail = (EditText) findViewById(R.id.Email);
        rPassword = (EditText) findViewById(R.id.Password);
        getSupportActionBar().setTitle(("Sign Up"));
        Alreadyhaveaccount = findViewById(R.id.AlreadyAccount);
        Alreadyhaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignUp_Activity.this, "Login your Account.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUp_Activity.this, SignIn_Activity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.Register:
                rRegister();
                break;
        }
    }
    private void rRegister() {
        String email = rEmail.getText().toString().trim();
        String password = rPassword.getText().toString().trim();
        String fullName = rFullName.getText().toString().trim();
        String phone= rPhone.getText().toString().trim();
        if(fullName.isEmpty()){
            rFullName.setError("Full Name is Required!!");
            rFullName.requestFocus();
            return;
        }
        if(phone.isEmpty()){
            rPhone.setError("Phone Number is Required!!");
            rPhone.requestFocus();
            return;
        }
        if(email.isEmpty()){
            rEmail.setError("Email is Required!!");
            rEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            rEmail.setError("Please Enter a valid Email");
            rEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            rPassword.setError("Password is Required!!");
            rPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            rPassword.setError("Password must have min 6 characters");
            rPassword.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(fullName,email,phone);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignUp_Activity.this,"User has been registered Successfully.",Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(SignUp_Activity.this, "Failed to Register!Try Again!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(SignUp_Activity.this, "Failed to Register!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}