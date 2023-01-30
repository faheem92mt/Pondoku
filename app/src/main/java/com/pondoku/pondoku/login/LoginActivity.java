package com.pondoku.pondoku.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pondoku.pondoku.HomeYeahh;
import com.pondoku.pondoku.MainActivity;
import com.pondoku.pondoku.R;
import com.pondoku.pondoku.profile.ResetPasswordActivity;
import com.pondoku.pondoku.signup.SignupActivity;
import com.pondoku.pondoku.solat.SolatActivity;

/**
 * This is the activity used for login
 * @author: alzafara
 */
public class LoginActivity extends AppCompatActivity {

    EditText emailText;
    EditText passwordText;
    TextView signUpRedirect;
    Button loginButton;

    public static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public static FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // forces the app to run in light mode and disables dark mode
        // since this is the launcher activity
        // we put the instructions here


        setContentView(R.layout.login_screen);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize class variables
        emailText = findViewById(R.id.emailAddressEditText);
        passwordText = findViewById(R.id.passwordEditText);
        signUpRedirect = findViewById(R.id.signUpRedirect);
        loginButton = findViewById(R.id.loginButton);

        // signUpRedirect listener
        signUpRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent); //switch activity
            }
        });

        // click on login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (emailText == null || emailText.getText().toString().equals("")) {
                    Log.w(TAG, "LoginFailure");
                    Toast.makeText(LoginActivity.this, "Please enter your email!",
                            Toast.LENGTH_SHORT).show();
                } else if (passwordText == null || passwordText.getText().toString().equals("")) {
                    Log.w(TAG, "LoginFailure");
                    Toast.makeText(LoginActivity.this, "Please enter your password!",
                            Toast.LENGTH_SHORT).show();
                } else {

                    String email = emailText.getText().toString();
                    String password = passwordText.getText().toString();

                    Toast.makeText(LoginActivity.this, "Checking user...",
                            Toast.LENGTH_SHORT).show();
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    /**
     * This method sends the user that logged in to the MainActivity
     *
     * @param user The user that just logged in
     */
    public void updateUI(FirebaseUser user) {
        Intent intent = new Intent(LoginActivity.this, HomeYeahh.class);
        startActivity(intent); //switch activity
//        LoginActivity.this.finish(); // close Login activity
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();

        if (fbUser!=null) {
            startActivity(new Intent(LoginActivity.this, HomeYeahh.class));
            finish();
        }
    }

    public void tvResetPasswordClick(View view) {
        startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
    }

    public void onBackPressed() {
        //do nothing
    }

}
