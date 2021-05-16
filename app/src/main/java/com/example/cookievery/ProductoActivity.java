package com.example.cookievery;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cookievery.data.ProductoRepository;
import com.example.cookievery.interfaces.ProductoService;
import com.example.cookievery.models.Producto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductoActivity extends AppCompatActivity {


    ListView listViewProductos;
    ArrayList<String> nombres = new ArrayList<>();
    ArrayAdapter adapter;
    ProductoRepository pr = new ProductoRepository(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        listViewProductos = (ListView) findViewById(R.id.listViewProductos);
        nombres = pr.obtenerProductos();
        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, nombres);
        listViewProductos.setAdapter(adapter);
        getProductos();
    }

    private void getProductos(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.6:8080/productos/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        ProductoService productoService = retrofit.create(ProductoService.class);

        Call<List<Producto>> call = productoService.getAll();
        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                try{
                    Producto productoGuardar = new Producto();
                    for (Producto producto: response.body()){
                        nombres.add(producto.getNombre_producto());
                        productoGuardar.setProducto_id(producto.getProducto_id());
                        productoGuardar.setNombre_producto(producto.getNombre_producto().toString());
                        productoGuardar.setDescripcion_producto(producto.getDescripcion_producto().toString());
                        productoGuardar.setPrecio_producto(producto.getPrecio_producto());
                        pr.saveProducto(productoGuardar);
                    }
                    //long productoGuardado = pr.saveProducto(productoGuardar);
                    adapter.notifyDataSetChanged();

//                    if(productoGuardado > 0){
//                        Toast.makeText(ProductoActivity.this, "Se guardo el producto", Toast.LENGTH_SHORT).show();
//                    }
                }catch(Exception e){
                    Toast.makeText(ProductoActivity.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(ProductoActivity.this, "Error de conexion - Datos de la base", Toast.LENGTH_SHORT).show();

            }
        });
    }

}