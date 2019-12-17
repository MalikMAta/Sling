package com.example.bayareasling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userEmail;
    private EditText userPassword;
    private Button userSignup;
    private TextView userLogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        userEmail = findViewById(R.id.sUser);
        userPassword = findViewById(R.id.sPassword);
        userSignup = findViewById(R.id.btnNewUser);
        userLogin = findViewById(R.id.btnSignIn);


        userSignup.setOnClickListener(this);
        userLogin.setOnClickListener(this);


    }



    private void registerUser() {

        String email = userEmail.getText().toString();
        String passsword = userPassword.getText().toString();


        //create toase to display error message to screen if fields are empty
        if (TextUtils.isEmpty(email)){

            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT) . show();
            return;
        }

        if (TextUtils.isEmpty(passsword)){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT) . show();
            return;

        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, passsword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                    Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                        }else{

                            Toast.makeText(SignUpActivity.this, "Registered Incomplete", Toast.LENGTH_SHORT).show();


                        }
                    }
                });
        }

    @Override
    public void onClick(View view) {

        if (view == userSignup){

            registerUser();
        }

        if (view == userLogin){
            //Take user to the login screen
        }
    }
}
