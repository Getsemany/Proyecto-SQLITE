package net.ivanvega.mibasedatosp77a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DAOContactos {
    SQLiteDatabase _sqLiteDatabase;
    Context ctx;

    public DAOContactos(Context ctx) {
        this.ctx = ctx;
        _sqLiteDatabase =
                new MiDB(ctx).getWritableDatabase();
    }

    public long insert(Contacto contacto){
        ContentValues contentValues
                = new ContentValues();

        contentValues.put(MiDB.COLUMNS_NAME_CONTACTO[1],
                contacto.getUsuario());
        contentValues.put(MiDB.COLUMNS_NAME_CONTACTO[2],
                contacto.getEmail());
        contentValues.put(MiDB.COLUMNS_NAME_CONTACTO[3],
                contacto.getTel());
        contentValues.put(MiDB.COLUMNS_NAME_CONTACTO[4],
                contacto.getFecNac()+"");

        return  _sqLiteDatabase.insert(MiDB.TABLE_NAME_CONTACTOS,
                null, contentValues);

    }

    public List<Contacto> getAll () throws ParseException {
        List<Contacto> lst=null;

        Cursor c = _sqLiteDatabase.query(MiDB.TABLE_NAME_CONTACTOS,
                MiDB.COLUMNS_NAME_CONTACTO,
                null,
                null,
                null,
                null,
                null,
                null);

           if (c.moveToFirst() ){
               lst = new ArrayList<Contacto>();
               do {
                  Contacto contacto =
                          new Contacto(c.getInt(0), c.getString(1),
                                  c.getString(2), c.getString(3),new SimpleDateFormat("yyyy/MM/dd").parse(c.getString(4)));
                   lst.add(contacto);

               }while(c.moveToNext());
           }
           return  lst;

    }

    public Cursor getAllCursor (){


        Cursor c=_sqLiteDatabase.rawQuery("select * from contactos ",null);


        return  c;

    }
    public Cursor getAllByUsuario(String criterio){

        Cursor c = _sqLiteDatabase.query(
                MiDB.TABLE_NAME_CONTACTOS,
                MiDB.COLUMNS_NAME_CONTACTO,
                "usuario like ",
                new String[]{criterio},
                null,
                null,null

        );

        return c;
    }
    public Cursor getContacto(String nombre){
        Cursor c=_sqLiteDatabase.rawQuery("select * from contactos where usuario like '%"+nombre+"%'",null);
        return c;
    }
    public void eliminar(int id){
        _sqLiteDatabase.execSQL("delete from contactos where _id="+id);
    }

}
