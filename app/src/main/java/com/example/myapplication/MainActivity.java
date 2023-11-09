package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText edEmail, edSenha;
    Button bt, btLogar, btRecupera;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edEmail = findViewById(R.id.editTextEmail);
        edSenha = findViewById(R.id.editTextSenha);
        btLogar = findViewById(R.id.buttonLogar);
        btRecupera = findViewById(R.id.buttonRec);

        bt = findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();

        btRecupera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edEmail.getText().toString();

                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            Toast.makeText(MainActivity.this, "Email de recuperação de senha enviado.", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        btLogar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = edEmail.getText().toString();
                String senha = edSenha.getText().toString();
                mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Usuário logado", Toast.LENGTH_SHORT).show();

                            //verificar usuário
                            FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();

                            if(usuario.isEmailVerified()){

                            }
                            else{
                                Toast.makeText(MainActivity.this, "Usuário não verificado.", Toast.LENGTH_SHORT).show();
                                usuario.sendEmailVerification();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Erro ao logar.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });



        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edEmail.getText().toString();
                String senha = edSenha.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           Toast.makeText(MainActivity.this, "Usuário criado", Toast.LENGTH_SHORT).show();
                       }
                       else{
                           Toast.makeText(MainActivity.this, "Usuário NÃO foi criado.", Toast.LENGTH_SHORT).show();

                       }
                    }
                });

            }
        });
    }
}