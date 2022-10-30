package com.example.appwithdatabase;



import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etNome;
    EditText etTelefone;
    EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        etEmail = findViewById(R.id.etEmail);
    }

    public void gravarContato(View view) {
        Contato contato = new Contato();
        contato.nome = etNome.getText().toString();
        contato.telefone = etTelefone.getText().toString();
        contato.email = etEmail.getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                MainActivity2.getInstance().getDb().insert(contato);
            }
        }).start();

        etNome.getText().clear();
        etEmail.getText().clear();
        etTelefone.getText().clear();

        Toast toast = Toast.makeText(getApplicationContext(),"The contact has been added to the list", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void goToViewActivity(View view) {
            Intent it = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(it);
    }
}