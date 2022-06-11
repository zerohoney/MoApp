package com.example.moapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moapp.R;
import com.example.moapp.model.UserAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String email, password;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private EditText inputEmail, inputPassword, birth, name;
    private Button singUpBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        inputEmail = findViewById(R.id.enter_id);
        inputPassword = findViewById(R.id.enter_pass);
        birth = findViewById(R.id.enter_bt_day);
        name = findViewById(R.id.enter_name);
        singUpBtn=findViewById(R.id.signupBtn);

        mAuth = FirebaseAuth.getInstance();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("UserAccount");
        singUpBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                email=inputEmail.getText().toString().trim();
                password=inputPassword.getText().toString().trim();


                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            FirebaseUser currentUser=mAuth.getCurrentUser();
                            UserAccount userAccount=new UserAccount();
                            userAccount.setEmail(email);
                            userAccount.setName(name.getText().toString().trim());
                            userAccount.setBirth(birth.getText().toString().trim());
                            userAccount.setUID(currentUser.getUid());
                            Toast.makeText(getApplicationContext(), "회원가입이 성공하였습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(SignUpActivity.this,MainCalendarActinty.class);

                           mDatabaseRef.child(currentUser.getUid()).setValue(userAccount);

                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "회원가입이 실패하였습니다..", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });




    }
}
