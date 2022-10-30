package com.example.appwithdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    EditText etNome;
    EditText etTelefone;
    EditText etEmail;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        etEmail = findViewById(R.id.etEmail);

        Intent it = getIntent();
        id = it.getIntExtra("id", 0);

        getContato(id);
    }

    protected void getContato (int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Contato> contatos = MainActivity2.getInstance().getDb().getUser(id);
                for (Contato i : contatos) {
                    etNome.setText(i.nome);
                    etTelefone.setText(i.telefone);
                    etEmail.setText(i.email);
                }
            }
        }).start();
    }

    public void goToViewActivity(View view) {
        Intent it = new Intent(MainActivity3.this, MainActivity2.class);
        startActivity(it);
    }

    public void updateContato(View view) {
        String nome = etNome.getText().toString();
        String telefone = etTelefone.getText().toString();
        String email = etEmail.getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                MainActivity2.getInstance().getDb().updateContato(id, nome, telefone, email);
            }
        }).start();

        Toast toast = Toast.makeText(getApplicationContext(),"Contato salvo", Toast.LENGTH_SHORT);
        toast.show();

        goToViewActivity(view);
    }
}