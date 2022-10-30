package com.example.appwithdatabase;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contato.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContatoDAO contatoDAO();
}
