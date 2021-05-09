package com.example.cookievery.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.cookievery.models.Cliente;

import java.util.Optional;

public class ClienteRepository {

    private final Context context;

    public ClienteRepository(Context context) {
        this.context = context;
    }

    public long saveClient(Cliente cliente) {
        CookieveryDbHelper dbHelper = CookieveryDbHelper.single(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
//            Create a map for the values
        ContentValues values = new ContentValues();
        values.put(CookieveryContract.FeedCliente.COLUMN_NAME_IDENTIFICACION, cliente.getIdentificacion());
        values.put(CookieveryContract.FeedCliente.COLUMN_NAME_CORREO, cliente.getCorreo());
        values.put(CookieveryContract.FeedCliente.COLUMN_NAME_DIRECCION, cliente.getDireccion());
        values.put(CookieveryContract.FeedCliente.COLUMN_NAME_NOMBRE, cliente.getNombre());
        values.put(CookieveryContract.FeedCliente.COLUMN_NAME_TELEFONO, cliente.getTelefono());
        values.put(CookieveryContract.FeedCliente.COLUMN_NAME_PASSWORD, cliente.getPassword());

        return db.insert(CookieveryContract.FeedCliente.TABLE_NAME, null, values);
    }

    public boolean existClient(Cliente cliente) {
        CookieveryDbHelper dbHelper = CookieveryDbHelper.single(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                CookieveryContract.FeedCliente.COLUMN_NAME_IDENTIFICACION
        };
        String[] selectionArgs = {cliente.getIdentificacion()};
        String selection = CookieveryContract.FeedCliente.COLUMN_NAME_IDENTIFICACION + " = ?";
        Cursor cursor = db.query(
                CookieveryContract.FeedCliente.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        return cursor.getCount() > 0;
    }

    public Optional<Cliente> login(String correo, String password) {
        CookieveryDbHelper dbHelper = CookieveryDbHelper.single(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {"*"};
        String[] selectionArgs = {correo, password};
        String selection = CookieveryContract.FeedCliente.COLUMN_NAME_CORREO + " = ? AND " +
                CookieveryContract.FeedCliente.COLUMN_NAME_PASSWORD + " = ?";
        Cursor cursor = db.query(
                CookieveryContract.FeedCliente.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (cursor.getCount() == 0) return Optional.empty();
        cursor.moveToNext();
        return Optional.of(new Cliente(
                cursor.getString(cursor.getColumnIndex(CookieveryContract.FeedCliente.COLUMN_NAME_IDENTIFICACION)),
                cursor.getString(cursor.getColumnIndex(CookieveryContract.FeedCliente.COLUMN_NAME_NOMBRE)),
                cursor.getString(cursor.getColumnIndex(CookieveryContract.FeedCliente.COLUMN_NAME_CORREO)),
                cursor.getString(cursor.getColumnIndex(CookieveryContract.FeedCliente.COLUMN_NAME_TELEFONO)),
                cursor.getString(cursor.getColumnIndex(CookieveryContract.FeedCliente.COLUMN_NAME_DIRECCION)),
                cursor.getString(cursor.getColumnIndex(CookieveryContract.FeedCliente.COLUMN_NAME_DIRECCION))
        ));
    }

}
