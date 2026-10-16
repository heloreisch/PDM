package com.example.app1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notas.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NOTAS = "notas";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITULO = "titulo";
    public static final String COLUMN_TEXTO = "texto";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTAS_TABLE = "CREATE TABLE " + TABLE_NOTAS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITULO + " TEXT, "
                + COLUMN_TEXTO + " TEXT" + ")";
        db.execSQL(CREATE_NOTAS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTAS);
        onCreate(db);
    }

    public long inserirNota(String titulo, String texto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITULO, titulo);
        values.put(COLUMN_TEXTO, texto);
        long id = db.insert(TABLE_NOTAS, null, values);
        db.close();
        return id;
    }

    public ArrayList<Nota> listarNotas() {
        ArrayList<Nota> listaNotas = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NOTAS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String titulo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITULO));
                String texto = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEXTO));

                Nota nota = new Nota(id, titulo, texto);
                listaNotas.add(nota);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return listaNotas;
    }

    public void deletarNota(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTAS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
