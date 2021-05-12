package com.example.cookievery.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
        ContentValues values = mapCliente(cliente);
        return db.insert(CookieveryContract.FeedCliente.TABLE_NAME, null, values);
    }

    private ContentValues mapCliente(Cliente cliente) {
        ContentValues values = new ContentValues();
        values.put(CookieveryContract.FeedCliente.COLUMN_NAME_IDENTIFICACION, cliente.getIdentificacion());
        values.put(CookieveryContract.FeedCliente.COLUMN_NAME_CORREO, cliente.getCorreo());
        values.put(CookieveryContract.FeedCliente.COLUMN_NAME_DIRECCION, cliente.getDireccion());
        values.put(CookieveryContract.FeedCliente.COLUMN_NAME_NOMBRE, cliente.getNombre());
        values.put(CookieveryContract.FeedCliente.COLUMN_NAME_TELEFONO, cliente.getTelefono());
        values.put(CookieveryContract.FeedCliente.COLUMN_NAME_PASSWORD, cliente.getPassword());
        return values;
    }

    public boolean existClient(Cliente cliente) {
        CookieveryDbHelper dbHelper = CookieveryDbHelper.single(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                CookieveryContract.FeedCliente.COLUMN_NAME_IDENTIFICACION
        };
        String[] selectionArgs = {cliente.getIdentificacion()};
        String selection = CookieveryContract.FeedCliente.COLUMN_NAME_IDENTIFICACION + " = ?";
        @SuppressLint("Recycle")
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
        @SuppressLint("Recycle")
        Cursor cursor = db.query(
                CookieveryContract.FeedCliente.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        return mapFromCursor(cursor);
    }

    public Optional<Cliente> findByIdentificacion(String identificacion) {
        CookieveryDbHelper dbHelper = CookieveryDbHelper.single(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {"*"};
        String[] selectionArgs = {identificacion};
        String selection = CookieveryContract.FeedCliente.COLUMN_NAME_IDENTIFICACION + " = ?";
        @SuppressLint("Recycle")
        Cursor cursor = db.query(
                CookieveryContract.FeedCliente.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        return mapFromCursor(cursor);
    }

    private Optional<Cliente> mapFromCursor(Cursor cursor) {
        if (cursor.getCount() == 0) return Optional.empty();

        cursor.moveToNext();
        return  Optional.of(new Cliente(
                cursor.getString(cursor.getColumnIndex(CookieveryContract.FeedCliente.COLUMN_NAME_IDENTIFICACION)),
                cursor.getString(cursor.getColumnIndex(CookieveryContract.FeedCliente.COLUMN_NAME_NOMBRE)),
                cursor.getString(cursor.getColumnIndex(CookieveryContract.FeedCliente.COLUMN_NAME_CORREO)),
                cursor.getString(cursor.getColumnIndex(CookieveryContract.FeedCliente.COLUMN_NAME_TELEFONO)),
                cursor.getString(cursor.getColumnIndex(CookieveryContract.FeedCliente.COLUMN_NAME_DIRECCION)),
                cursor.getString(cursor.getColumnIndex(CookieveryContract.FeedCliente.COLUMN_NAME_PASSWORD))
        ));
    }

    public Optional<Integer> deleteCliente(String identification) {
        CookieveryDbHelper dbHelper = CookieveryDbHelper.single(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        final String selection = CookieveryContract.FeedCliente.COLUMN_NAME_IDENTIFICACION + " = ?";
        final String[] selectionArgs = {identification};
        return Optional.of(db.delete(CookieveryContract.FeedCliente.TABLE_NAME, selection, selectionArgs));
    }

    public Optional<Integer> updateCliente(Cliente cliente) {
        CookieveryDbHelper dbHelper = CookieveryDbHelper.single(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        final ContentValues values = mapCliente(cliente);

        final String selection = CookieveryContract.FeedCliente.COLUMN_NAME_IDENTIFICACION + " = ?";
        final String[] selectionArgs = {cliente.getIdentificacion()};
        return Optional.of(db.update(
                CookieveryContract.FeedCliente.TABLE_NAME,
                values,
                selection,
                selectionArgs
        ));
    }

}
