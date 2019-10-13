package net.ivanvega.mibasedatosp77a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Intent2 extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtEmail;
    private EditText Twitter;
    private EditText Tel;
    private EditText Fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent2);

        txtUsuario=(EditText)findViewById(R.id.txtUsuario);
        txtEmail=(EditText)findViewById(R.id.txtEmail);
        Twitter=(EditText)findViewById(R.id.txtTwitter);
        Tel=(EditText)findViewById(R.id.txtTelefono);
        Fecha=(EditText)findViewById(R.id.txtFecha);
    }
    public void Guardar(View view){

        Contacto obj= null;
        try {
            obj = new Contacto(0,txtUsuario.getText().toString(),txtEmail.getText().toString(),
                    Tel.getText().toString(),new SimpleDateFormat("yyyy/MM/dd").parse(Fecha.getText().toString())
                    );
        } catch (ParseException e) {
            Cancelar(view);
        }
        Intent Guardar =new Intent(this,MainActivity.class);
        Guardar.putExtra("con", obj);
        setResult(MainActivity.RESULT_OK, Guardar);
        finish();
    }
    public void Cancelar(View view){
        Intent Cancelar =new Intent(this,MainActivity.class);
        setResult(MainActivity.RESULT_CANCELED,Cancelar);
        finish();
    }
}
