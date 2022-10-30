package com.example.appwithdatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contato {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nome;

    public String telefone;

    public String email;

}
