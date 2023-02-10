package com.pondoku.pondoku.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pondoku.pondoku.HomeYeahh;
import com.pondoku.pondoku.MainActivity;
import com.pondoku.pondoku.R;
import com.pondoku.pondoku.login.LoginActivity;

public class ChangePasswordActivity extends AppCompatActivity {

    private TextInputEditText etPassword, etConfirmPassword;

    LoginActivity loginActivity = new LoginActivity();

//    FirebaseUser firebaseUser = loginActivity.firebaseUser;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        System.out.println("this is user:" + firebaseUser);
    }

    public void btnChangePasswordClick(View view) {
        System.out.println("Hello World ssss");
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (password.equals("")) {
            etPassword.setError(getString(R.string.enter_password));
        }
        else if(confirmPassword.equals("")) {
            etConfirmPassword.setError(getString(R.string.confirm_password));
        }
        else if(!password.equals(confirmPassword)){
            etConfirmPassword.setError(getString(R.string.password_mismatch));
        }
        else {


            System.out.println("entered");

            if (firebaseUser!=null) {
                System.out.println("interesting");
                firebaseUser.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ChangePasswordActivity.this, R.string.password_changed_successfully, Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            Toast.makeText(ChangePasswordActivity.this, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else {
                System.out.println("lol empty");
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(ChangePasswordActivity.this, MainActivity.class));

    }

    public void btnLogoutClick(View view) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        startActivity(new Intent(ChangePasswordActivity.this, LoginActivity.class));
        firebaseAuth.signOut();
//        finishAndRemoveTask();
//        finish();

    }

}