package com.example.cookievery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cookievery.data.ClienteRepository;
import com.example.cookievery.models.Cliente;

public class RegisterActivity extends AppCompatActivity {

    private boolean editar = false;
    ClienteRepository clienteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        clienteRepository = new ClienteRepository(this);

        Intent intent = getIntent();
        final String identificacion = intent.getStringExtra(MainActivity.CLIENTE_LOGIN);

        if (identificacion != null) {
            clienteRepository.findByIdentificacion(identificacion).ifPresent((cliente) -> {
                editar = true;
                setTextValue(R.id.identificacionInput, cliente.getIdentificacion());
                setTextValue(R.id.nombreInput, cliente.getNombre());
                setTextValue(R.id.correoInput, cliente.getCorreo());
                setTextValue(R.id.telefonoInput, cliente.getTelefono());
                setTextValue(R.id.direccionInput, cliente.getDireccion());
                setTextValue(R.id.passwordInput, cliente.getPassword());
            });
        }
    }

    public void back(View view) {
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

//            If the client not exist permitimos registrarse
        if (clienteRepository.existClient(cliente) && !editar) {
            Toast.makeText(this, "Ya registramos un cliente con esta identificacion: " + cliente.getIdentificacion(), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!editar) {
            long id = clienteRepository.saveClient(cliente);
            Toast.makeText(this, "Guardamos el cliente " + cliente.getNombre() + ". ID: " + id, Toast.LENGTH_LONG).show();
        } else {
            clienteRepository.updateCliente(cliente);
            Toast.makeText(this, "Editamos tu perfil correctamente", Toast.LENGTH_LONG).show();
        }
        back(view);
        return true;
    }

    private String getTextValue(int idText) {
        EditText editText = findViewById(idText);
        return editText.getText().toString().trim();
    }

    private void setTextValue(int idText, String value) {
        EditText editText = findViewById(idText);
        editText.setText(value);
    }
}