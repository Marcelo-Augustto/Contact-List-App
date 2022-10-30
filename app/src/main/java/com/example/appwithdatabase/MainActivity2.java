package com.example.appwithdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    List<Contato> contatos;
    String DATABASE_NAME = "my-db";
    protected static MainActivity2 instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        instance = this;

        buscaContatos();
    }

    public static MainActivity2 getInstance() {
        return instance;
    }

    private void buscaContatos() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Contato> contatos = getDb().getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RecyclerView rvContatos = findViewById(R.id.rvContatos);
                        MeuAdaptador adaptador = new MeuAdaptador(contatos);
                        RecyclerView.LayoutManager layout =
                                new LinearLayoutManager(MainActivity2.this, LinearLayoutManager.VERTICAL, false);
                        rvContatos.setLayoutManager(layout);
                        rvContatos.setAdapter(adaptador);
                    }
                });
            }
        }).start();
    }

    protected void deletarContato (Contato contato) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getDb().delete(contato);
            }
        }).start();
    }

    public ContatoDAO getDb() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, DATABASE_NAME).build();
        ContatoDAO contatoDAO = db.contatoDAO();
        return contatoDAO;
    }

    public void goToAddActivity(View view) {
        Intent it = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(it);
    }

    public void goToEditActivity(View view, int id) {
        Intent it = new Intent(MainActivity2.this, MainActivity3.class);
        it.putExtra("id", id);
        startActivity(it);
    }
}