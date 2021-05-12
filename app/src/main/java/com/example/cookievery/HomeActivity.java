package com.example.cookievery;

import android.content.Intent;
import android.os.Bundle;

import com.example.cookievery.app.ClienteLoged;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ClienteLoged cliente = ClienteLoged.getInstance();
        Intent intent = getIntent();
        String identificacion = intent.getStringExtra(MainActivity.CLIENTE_LOGIN);
        if (identificacion != null) {
            cliente.setIdentificacion(identificacion);
        }

        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Editar perfil...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                intentRegister.putExtra(MainActivity.CLIENTE_LOGIN, identificacion);
                startActivity(intentRegister);
            }
        });

        TextView textView = findViewById(R.id.identificacionHome);
        if (textView != null) {
            final String userText = "Usuario: " + cliente.getIdentificacion();
            textView.setText(userText);
        }
    }
}