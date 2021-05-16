package com.example.cookievery.interfaces;

import com.example.cookievery.models.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductoService {

    @GET("all")
    public Call<List<Producto>> getAll();
}
