package ec.com.empresa.appcontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    Contactos contactos;
    EditText txtId, txtNombre, txtTelefono, txtDireccion, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        contactos = new Contactos(this, "empresa.db", 1);
        txtId = findViewById(R.id.txtId);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtEmail = findViewById(R.id.txtEmail);
    }

    public void cmdCreate_onClick(View v)
    {
        Contacto c = contactos.Create(
                Integer.parseInt(txtId.getText().toString()),
                txtNombre.getText().toString(),
                txtTelefono.getText().toString(),
                txtDireccion.getText().toString(),
                txtEmail.getText().toString()
        );
        if (c != null)
            Toast.makeText(this, "CONTACTO INSERTADO ", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "CONTACTO NO INSERTADO", Toast.LENGTH_SHORT).show();
    }

    public void cmdReadOne_onClick(View v)
    {
        Contacto c = contactos.Read_One( Integer.parseInt(txtId.getText().toString()));
        if (c != null)
        {
            txtId.setText( ""+c.Id);
            txtNombre.setText( c.Nombre);
            txtTelefono.setText( c.Telefono);
            txtDireccion.setText( c.Direccion);
            txtEmail.setText( c.Email);
        }
        else
        {
            Toast.makeText(this, "CONTACTO NO ENCONTRADO ", Toast.LENGTH_SHORT).show();
        }
    }

    public void cmdUpdate_onClick(View v)
    {
        boolean resultado = contactos.Update(
                Integer.parseInt(txtId.getText().toString()),
                txtNombre.getText().toString(),
                txtTelefono.getText().toString(),
                txtDireccion.getText().toString(),
                txtEmail.getText().toString()
        );
        if (resultado == true)
            Toast.makeText(this, "CONTACTO ACTUALZIADO ", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "CONTACTO NO ACTUALIZADO", Toast.LENGTH_SHORT).show();
    }

    public void cmdDelete_onClick(View v)
    {
        boolean resultado = contactos.Delete( Integer.parseInt( txtId.getText().toString()));
        if (resultado == true)
        {
            Toast.makeText(this, "REGISTRO BORRADO", Toast.LENGTH_SHORT).show();
            txtId.setText( "");
            txtNombre.setText( "");
            txtTelefono.setText( "");
            txtDireccion.setText( "" );
            txtEmail.setText( "" );
        }
        else
            Toast.makeText(this, "CONTACTO NO ENCONTRADO", Toast.LENGTH_SHORT).show();
    }

    public void cmdVolver_onClick(View v)
    {
        finish();
    }
}