package com.example.despesaspessoais.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDadosHelper extends SQLiteOpenHelper {

    public BancoDadosHelper(Context context) {
        super(context, "Despesa.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String sql = "CREATE TABLE despesa(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT, valor DOUBLE, data DATETIME);";
            db.execSQL(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
