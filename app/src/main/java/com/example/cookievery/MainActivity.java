package com.example.cookievery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cookievery.data.ClienteRepository;
import com.example.cookievery.models.Cliente;

import java.util.Optional;

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
        Optional<Cliente> clienteLogin = clienteRepository.login(
                getTextValue(R.id.userLogin),
                getTextValue(R.id.passwordLogin)
        );
        clienteLogin.ifPresent((cliente) -> {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(CLIENTE_LOGIN, cliente.getIdentificacion());
            startActivity(intent);
        });
        if (!clienteLogin.isPresent()) {
            Toast.makeText(this, "Usuario o contraseña incorrectos.", Toast.LENGTH_LONG).show();
        }
    }

    private String getTextValue(int idText) {
        EditText editText = findViewById(idText);
        return editText.getText().toString().trim();
    }

}
