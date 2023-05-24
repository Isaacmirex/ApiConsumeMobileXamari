package ec.com.empresa.appcontactos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Contactos {

    private DBHellper dbHellper;

    private SQLiteDatabase db;

    public Contactos(Context contexto, String dbName, int version) {
        dbHellper =new DBHellper(contexto, dbName, null, version);
    }

    public Contacto Create(Integer id, String nombre, String telefono, String direccion, String email)
    {
        db = dbHellper.getWritableDatabase();
        ContentValues row =new ContentValues();
        row.put("id", id);
        row.put("nombre", nombre);
        row.put("telefono", telefono);
        row.put("direccion", direccion);
        row.put("email", email);

        long qty =db.insert("contactos", null, row);
        if(qty > 0) {
            Contacto data = new Contacto();
            data.Id = id;
            data.Nombre = nombre;
            data.Telefono = telefono;
            data.Direccion = direccion;
            data.Email = email;
            return data;
        } else {
            return null;
        }
    }

    public Contacto Read_One(Integer id)
    {
        db = dbHellper.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT * FROM contactos WHERE id ='" +id+"'", null);
        if (cr.getCount()>0) {
            Contacto cont = new Contacto();

            cr.moveToNext();
                cont.Id = cr.getInt(0);
                cont.Nombre = cr.getString(1);
                cont.Telefono = cr.getString(2);
                cont.Direccion = cr.getString(3);
                cont.Email = cr.getString(4);

            return cont;
        } else {
            return null;
        }
    }

    public Contacto[] Read_All()
    {
        db = dbHellper.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT * FROM contactos ORDER BY nombre", null);
        if (cr.getCount()>0) {
            Contacto[] datos = new Contacto[cr.getCount()];
            Contacto cont;
            int i = 0;

            while (cr.moveToNext()) {
                cont = new Contacto();
                cont.Id = cr.getInt(0);
                cont.Nombre = cr.getString(1);
                cont.Telefono = cr.getString(2);
                cont.Direccion = cr.getString(3);
                cont.Email = cr.getString(4);
                datos[i++] = cont;
            }
            return datos;
        } else {
            return null;
        }
    }

    public Contacto[] Read_ByNombre(String find)
    {
        db = dbHellper.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT * FROM contactos WHERE nombre LIKE '%' || '" + find + "' || '%' ORDER BY nombre", null);
        if (cr.getCount()>0) {
            Contacto[] datos = new Contacto[cr.getCount()];
            Contacto cont;
            int i = 0;

            while (cr.moveToNext()) {
                cont = new Contacto();
                cont.Id = cr.getInt(0);
                cont.Nombre = cr.getString(1);
                cont.Telefono = cr.getString(2);
                cont.Direccion = cr.getString(3);
                cont.Email = cr.getString(4);
                datos[i++] = cont;
            }
            return datos;
        } else {
            return null;
        }
    }

    public boolean Update(Integer id, String nombre, String telefono, String direccion, String email)
    {
        db = dbHellper.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put("id", id);
        row.put("nombre", nombre);
        row.put("telefono", telefono);
        row.put("direccion", direccion);
        row.put("email", email);

        int qty = db.update("contactos", row, "id='"+id+"'",null);
        return qty>0;
    }

    public boolean Delete(Integer id) {

        db = dbHellper.getWritableDatabase();
        int qty = db.delete("contactos", "id='"+ id +"'", null);
        return qty>0;
    }

}
