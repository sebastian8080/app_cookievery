package com.example.cookievery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cookievery.data.ClienteRepository;
import com.example.cookievery.models.Cliente;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void backToLogin(View view) {
        finish();
    }

    public boolean saveForm(View view) {
        Cliente cliente = new Cliente(
                getTextValue(R.id.identificacionInput),
                getTextValue(R.id.nombreInput),
                getTextValue(R.id.correoInput),
                getTextValue(R.id.telefonoInput),
                getTextValue(R.id.direccionInput),
                getTextValue(R.id.passwordInput)
        );
//        Ig is valid save the client into SQL lite
        if (!cliente.isValid()) {
            Toast.makeText(this, "Existen errores en su formulario", Toast.LENGTH_SHORT).show();
            return false;
        }

        ClienteRepository clienteRepository = new ClienteRepository(this);
//            If the client not exist permitimos registrarse
        if (clienteRepository.existClient(cliente)) {
            Toast.makeText(this, "Ya registramos un cliente con esta identificacion: " + cliente.getIdentificacion(), Toast.LENGTH_SHORT).show();
            return false;
        }
        long id = clienteRepository.saveClient(cliente);
        Toast.makeText(this, "Guardamos el cliente " + cliente.getNombre() + ". ID: " + id, Toast.LENGTH_LONG).show();
        backToLogin(view);
        return true;
    }

    private String getTextValue(int idText) {
        EditText editText = (EditText) findViewById(idText);
        return editText.getText().toString().trim();
    }
}