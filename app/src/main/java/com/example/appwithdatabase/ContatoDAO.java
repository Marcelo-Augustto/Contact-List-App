package com.example.appwithdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContatoDAO {

    @Query("SELECT * FROM contato")
    List<Contato> getAll();

    @Query("SELECT * FROM contato WHERE id = :id")
    List<Contato> getUser(int id);

    @Query("UPDATE contato SET nome = :nome, telefone = :telefone, email = :email WHERE id = :id")
    void updateContato (int id, String nome, String telefone, String email);

    @Insert
    void insert(Contato... contatos);

    @Delete
    void delete(Contato contato);
}
