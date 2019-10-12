package net.ivanvega.mibasedatosp77a;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    private TextView txtid;
    private TextView txtnombre;
    private DAOContactos dao;
    Contacto con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    txtid=(TextView)findViewById(R.id.txtId);
    txtnombre=(TextView)findViewById(R.id.txtNombre);
         dao = new DAOContactos(this);
        /*try {
            dao.insert(new Contacto(0, "perronegro",
                    "perronegro@","445",new SimpleDateFormat("yyyy/MM/dd").parse("1998/07/20")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            dao.insert(new Contacto(0, "perroblanco",
                    "perroblanco@","544",new SimpleDateFormat("yyyy/MM/dd").parse("1998/07/20")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            for (Contacto c : dao.getAll()){
                 Toast.makeText(this,
                         c.usuario,
                         Toast.LENGTH_SHORT).show();
             }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dao.eliminar(2);

*/
        lv = findViewById(R.id.lv);
        Mostrar();

    }
    public void Mostrar(){

        SimpleCursorAdapter adp =
                new SimpleCursorAdapter(
                        this,
                        android.R.layout.simple_list_item_2,
                        dao.getAllCursor(),
                        new String[]{"_id","usuario"},
                        new int[]{android.R.id.text1, android.R.id.text2
                        },
                        SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE

                );
        lv.setAdapter(adp);
    }
    public void Siguiente(View view){
        Intent siguiente=new Intent(this,Intent2.class);
        startActivityForResult(siguiente,0);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==MainActivity.RESULT_OK){
            con=(Contacto)data.getSerializableExtra("con");
            dao.insert(con);
            Mostrar();
        }
    }
    public void Eliminar(View view){
        if(!txtid.getText().toString().equals("")){
            dao.eliminar(Integer.parseInt(txtid.getText().toString()));
        }
        Mostrar();
    }
    public void Buscar(View view){
       if(!txtnombre.getText().toString().equals("")){
           SimpleCursorAdapter adp =
                   new SimpleCursorAdapter(
                           this,
                           android.R.layout.simple_list_item_2,
                           dao.getContacto(txtnombre.getText().toString()),
                           new String[]{"_id","usuario"},
                           new int[]{android.R.id.text1, android.R.id.text2
                           },
                           SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE

                   );
           lv.setAdapter(adp);
       }
    }
}
