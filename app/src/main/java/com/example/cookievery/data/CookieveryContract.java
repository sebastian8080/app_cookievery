package com.example.cookievery.data;

import android.provider.BaseColumns;

public final class CookieveryContract {

    private CookieveryContract() {}

    public static class FeedCliente implements BaseColumns {
        public static final String TABLE_NAME = "cliente";
        public static final String COLUMN_NAME_IDENTIFICACION = "identificacion";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_CORREO = "correo";
        public static final String COLUMN_NAME_TELEFONO = "telefono";
        public static final String COLUMN_NAME_DIRECCION = "direccion";
        public static final String COLUMN_NAME_PASSWORD = "password";

        public static final String SQL_CREATE_CLIENTE =
                "CREATE TABLE " + FeedCliente.TABLE_NAME + " (" +
                FeedCliente.COLUMN_NAME_IDENTIFICACION + " TEXT PRIMARY KEY," +
                FeedCliente.COLUMN_NAME_NOMBRE + " TEXT," +
                FeedCliente.COLUMN_NAME_CORREO + " TEXT," +
                FeedCliente.COLUMN_NAME_TELEFONO + " TEXT," +
                FeedCliente.COLUMN_NAME_PASSWORD + " TEXT," +
                FeedCliente.COLUMN_NAME_DIRECCION + " TEXT)";

        public static final String SQL_DELETE_CLIENTE = "DROP TABLE IF EXISTS " + FeedCliente.TABLE_NAME;

    }

    public static class FeedProducto implements BaseColumns{
        public static final String TABLE_NAME = "producto";
        public static final String COLUMN_PRODUCTO_ID = "producto_id";
        public static final String COLUMN_NOMBRE_PRODUCTO = "nombre_producto";
        public static final String COLUMN_DESCRIPCION_PRODUCTO = "descripcion_producto";
        public static final String COLUMN_PRECIO_PRODUCTO = "precio_producto";

        public static final String SQL_CREATE_PRODUCTO =
                "CREATE TABLE " + FeedProducto.TABLE_NAME + " (" +
                        FeedProducto.COLUMN_PRODUCTO_ID + " INTEGER PRIMARY KEY, " +
                        FeedProducto.COLUMN_NOMBRE_PRODUCTO + " TEXT, " +
                        FeedProducto.COLUMN_DESCRIPCION_PRODUCTO + " TEXT, " +
                        FeedProducto.COLUMN_PRECIO_PRODUCTO + " NUMERIC)";

        public static final String SQL_DELETE_PRODUCTO = "DROP TABLE IF EXISTS " + FeedProducto.TABLE_NAME;
    }

}
