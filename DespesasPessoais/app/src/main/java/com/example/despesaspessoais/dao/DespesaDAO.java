package com.example.despesaspessoais.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.despesaspessoais.entidade.Despesa;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DespesaDAO {

    public static void inserir(Context context, Despesa despesa){
        BancoDadosHelper bancoDadosHelper = new BancoDadosHelper(context);
        SQLiteDatabase db = bancoDadosHelper.getWritableDatabase();

        try{
            db.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("nome", despesa.getNome());
            contentValues.put("valor", despesa.getValor());
            DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            contentValues.put("data", formatador.format(despesa.getData()));
            db.insertOrThrow("despesa",null, contentValues);
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
            if(db != null && db.isOpen()){
                db.close();
            }
        }
    }

    public static List<Despesa> getDespesas(Context context){
        BancoDadosHelper bancoDadosHelper = new BancoDadosHelper(context);
        SQLiteDatabase db = bancoDadosHelper.getReadableDatabase();

        List<Despesa> listaDespesas = new ArrayList<>();
        try {
            String[] colunas = {
                    "nome",
                    "valor",
                    "data"
            };

            Cursor cursor = db.query(
                    "despesa",
                    colunas,
                    null,
                    null,
                    null,
                    null,
                    null );

//            cursor.moveToFirst();
            while(cursor.moveToNext()){
                Despesa despesa = new Despesa();
                despesa.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
                despesa.setValor(cursor.getFloat(cursor.getColumnIndexOrThrow("valor")));

                String data = cursor.getString(cursor.getColumnIndexOrThrow("data"));
                DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                despesa.setData(formatador.parse(data));

                listaDespesas.add(despesa);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null && db.isOpen()){
                db.close();
            }
        }
        return listaDespesas;
    }
}
