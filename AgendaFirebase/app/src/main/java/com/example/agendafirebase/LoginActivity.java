package com.example.agendafirebase;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private Button btnLog;
    private Button btnRegistrar;
    private EditText EditContra;
    private EditText EditEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLog = (Button)findViewById(R.id.btnSesion);
        EditContra = (EditText)findViewById(R.id.EditContra);
        EditEmail = (EditText)findViewById(R.id.EditEmail);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(EditContra.getText().toString().matches("") || EditEmail.getText().toString().matches("")){
                    Toast.makeText(LoginActivity.this, "Ingrese todo los datos", Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(EditEmail.getText().toString(),EditContra.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                EditContra.setText("");
                                EditEmail.setText("");
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(LoginActivity.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }


}