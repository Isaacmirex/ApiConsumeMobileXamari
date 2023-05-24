package ec.com.empresa.appcontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Contactos contactos;
    EditText txtBuscar;
    TableLayout tblContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tblContactos  = findViewById(R.id.tblContactos);

        contactos = new Contactos(this, "empresa.db", 1);
    }

    public void cmdView_onClick(View v) {

        Contacto[] contactoList = contactos.Read_All();

        TableLayout tblContactos = findViewById(R.id.tblContactos);
        tblContactos.removeAllViews(); // Eliminatodo

        // Genera un encabezado
        TableRow encabezadoFila = new TableRow(this);
        TextView idEncabezado = new TextView(this);
        idEncabezado.setText("IDs ");
        TextView nombreEncabezado = new TextView(this);
        nombreEncabezado.setText("Nombres");
        TextView telefonoEncabezado = new TextView(this);
        telefonoEncabezado.setText("Teléfonos");
        TextView direccionEncabezado = new TextView(this);
        direccionEncabezado.setText("Direcciónes");
        TextView emailEncabezado = new TextView(this);
        emailEncabezado.setText("Emails");
        encabezadoFila.addView(idEncabezado);
        encabezadoFila.addView(nombreEncabezado);
        encabezadoFila.addView(telefonoEncabezado);
        encabezadoFila.addView(direccionEncabezado);
        encabezadoFila.addView(emailEncabezado);
        tblContactos.addView(encabezadoFila);

        // Crear filas
        for (Contacto contacto : contactoList) {
            TableRow fila = new TableRow(this);
            TextView idTextView = new TextView(this);
            idTextView.setText(String.valueOf(contacto.Id));
            TextView nombreTextView = new TextView(this);
            nombreTextView.setText(contacto.Nombre);
            TextView telefonoTextView = new TextView(this);
            telefonoTextView.setText(contacto.Telefono);
            TextView direccionTextView = new TextView(this);
            direccionTextView.setText(contacto.Direccion);
            TextView emailTextView = new TextView(this);
            emailTextView.setText(contacto.Email);
            fila.addView(idTextView);
            fila.addView(nombreTextView);
            fila.addView(telefonoTextView);
            fila.addView(direccionTextView);
            fila.addView(emailTextView);
            tblContactos.addView(fila);
        }
    }

    public void cmdFind_onClick(View v) {

        txtBuscar = findViewById(R.id.txtBuscar);
        String busqueda = txtBuscar.getText().toString().trim();

        Contacto[] contactoList = contactos.Read_ByNombre(busqueda);

        if(contactoList == null) {
            Toast.makeText(this, "NOMBRE CONTACTO NO ENCONTRADO", Toast.LENGTH_SHORT).show();
            return;
        }

        TableLayout tblContactos = findViewById(R.id.tblContactos);
        tblContactos.removeAllViews();

        // Crear la fila de encabezado
        TableRow encabezadoFila = new TableRow(this);
        TextView idEncabezado = new TextView(this);
        idEncabezado.setText("IDs");
        TextView nombreEncabezado = new TextView(this);
        nombreEncabezado.setText("Nombres");
        TextView telefonoEncabezado = new TextView(this);
        telefonoEncabezado.setText("Teléfonos");
        TextView direccionEncabezado = new TextView(this);
        direccionEncabezado.setText("Direcciónes");
        TextView emailEncabezado = new TextView(this);
        emailEncabezado.setText("Emails");
        encabezadoFila.addView(idEncabezado);
        encabezadoFila.addView(nombreEncabezado);
        encabezadoFila.addView(telefonoEncabezado);
        encabezadoFila.addView(direccionEncabezado);
        encabezadoFila.addView(emailEncabezado);
        tblContactos.addView(encabezadoFila);

        // Crear filas p
        for (Contacto contacto : contactoList) {
            TableRow fila = new TableRow(this);
            TextView idTextView = new TextView(this);
            idTextView.setText(String.valueOf(contacto.Id));
            TextView nombreTextView = new TextView(this);
            nombreTextView.setText(contacto.Nombre);
            TextView telefonoTextView = new TextView(this);
            telefonoTextView.setText(contacto.Telefono);
            TextView direccionTextView = new TextView(this);
            direccionTextView.setText(contacto.Direccion);
            TextView emailTextView = new TextView(this);
            emailTextView.setText(contacto.Email);
            fila.addView(idTextView);
            fila.addView(nombreTextView);
            fila.addView(telefonoTextView);
            fila.addView(direccionTextView);
            fila.addView(emailTextView);
            tblContactos.addView(fila);
        }
    }

    public void cmdAdd_onClick(View v)
    {
        Intent i = new Intent(this, Activity2.class);

        startActivity(i);
    }








}