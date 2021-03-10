package stud11418041.develops.applikasipemantauancovid_19.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import stud11418041.develops.applikasipemantauancovid_19.database.DBHelper;
import stud11418041.develops.applikasipemantauancovid_19.model.Berita;

public class BeritaController {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public static final String TABLE_NAME = "berita";
    public static final String ID = "id";
    public static final String JUDUL = "judul";
    public static final String WAKTU = "waktu";
    public static final String GAMBAR = "gambar";
    public static final String ISI = "isi";

    public static final String CREATE_BERITA = "CREATE TABLE " + TABLE_NAME + " " + "(" + ID + " integer primary key, " + JUDUL + " TEXT, " + WAKTU+ " TEXT, " + GAMBAR + " TEXT , " + ISI + " TEXT )";

    private String[] TABLE_COLUMNS = {ID,JUDUL, WAKTU, GAMBAR,ISI};

    public BeritaController(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void deleteData() {
        database.delete(TABLE_NAME, null, null);
    }

    public void insertData(int id,String judul,  String waktu, String gambar,String isi) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(JUDUL, judul);
        contentValues.put(WAKTU, waktu);
        contentValues.put(GAMBAR, gambar);
        contentValues.put(ISI, isi);

        database.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<Berita> getData() {
        ArrayList<Berita> allData = new ArrayList<Berita>();
        Cursor cursor = null;

        cursor = database.query(TABLE_NAME, TABLE_COLUMNS, null, null, null, null, ID + " ASC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            allData.add(parseData(cursor));

            cursor.moveToNext();
        }

        cursor.close();
        return allData;
    }

    private Berita parseData(Cursor cursor) {
        Berita curData = new Berita();

        curData.setId(cursor.getInt(0));
        curData.setJudul(cursor.getString(1));
        curData.setWaktu(cursor.getString(2));
        curData.setGambar(cursor.getString(3));
        curData.setIsi(cursor.getString(4));

        return curData;
    }


}