package com.example.cookievery.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cookievery.models.Producto;

import java.util.ArrayList;

public class ProductoRepository {

    private Context context;

    public ProductoRepository(Context context) {
        this.context = context;
    }

    public long saveProducto(Producto producto){
        CookieveryDbHelper dbHelper = CookieveryDbHelper.single(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CookieveryContract.FeedProducto.COLUMN_PRODUCTO_ID, producto.getProducto_id());
        cv.put(CookieveryContract.FeedProducto.COLUMN_NOMBRE_PRODUCTO, producto.getNombre_producto());
        cv.put(CookieveryContract.FeedProducto.COLUMN_DESCRIPCION_PRODUCTO, producto.getDescripcion_producto());
        cv.put(CookieveryContract.FeedProducto.COLUMN_PRECIO_PRODUCTO, producto.getPrecio_producto());
        return db.insert(CookieveryContract.FeedProducto.TABLE_NAME, null, cv);
    }

    public ArrayList obtenerProductos(){

        Producto producto = new Producto();
        CookieveryDbHelper cookieveryDbHelper = CookieveryDbHelper.single(context);
        SQLiteDatabase db = cookieveryDbHelper.getReadableDatabase();

        String [] projection = {
                CookieveryContract.FeedProducto.COLUMN_NOMBRE_PRODUCTO,
                CookieveryContract.FeedProducto.COLUMN_DESCRIPCION_PRODUCTO
        };

        Cursor cursor = db.query(
                CookieveryContract.FeedProducto.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );


        ArrayList<String> productos = new ArrayList<>();

        if(cursor.moveToFirst()){
            do {
                productos.add(cursor.getString(0) + " - " + cursor.getString(1));
            } while (cursor.moveToNext());
        }

        cursor.close();

        return productos;
    }
}
