package es.ipo2.peliculas;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Inicio extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        txtUsuario=findViewById(R.id.txtUsuario);
        txtPass = findViewById(R.id.txtPass);
    }

    public void oyente_btnEntrar(View view){
        Log.d("Debug_bienvenido", "El usuario pulso Entrar");

        String usuario = txtUsuario.getText().toString();
        String pass = txtPass.getText().toString();

        if(usuario.equals("ipo2") && pass.equals("1234")){
            //Lanzar la segunda Activity
            Intent i = new Intent(this, Principal.class );
            i.putExtra("usuario",  txtUsuario.getText().toString());
            startActivity(i);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error Autenticación");
            builder.setMessage("Vuelva a intentarlo");
            builder.setPositiveButton("OK",null);
            builder.create();
            builder.show();
        }
    }
}
