package com.example.cookievery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.cookievery.data.ClienteRepository;
import com.example.cookievery.helpers.DataHelper;

public class MainActivity extends AppCompatActivity {

    public static final String CLIENTE_LOGIN = "com.example.cookievery.cliente_identificacion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void navigateToRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        ClienteRepository clienteRepository = new ClienteRepository(this);
        clienteRepository.login(
                getTextValue(R.id.userLogin),
                getTextValue(R.id.passwordLogin)
        ).ifPresent((cliente) -> {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(CLIENTE_LOGIN, cliente.getIdentificacion());
            startActivity(intent);
        });
    }

    private String getTextValue(int idText) {
        EditText editText = (EditText) findViewById(idText);
        return editText.getText().toString().trim();
    }

}
