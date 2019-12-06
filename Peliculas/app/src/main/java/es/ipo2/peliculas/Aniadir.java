package es.ipo2.peliculas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Aniadir extends AppCompatActivity {

    private EditText txtTitulo;
    private EditText txtAnio;
    private EditText txtDuracion;
    private EditText txtDireccion;
    private Spinner spinnerGenero;
    private EditText txtSinopsis;
    private ImageButton btnAniadirPelicula;
    private ConectorBD conectorBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir);

        txtTitulo = findViewById(R.id.txtTitulo);
        txtAnio = findViewById(R.id.txtAnio);
        txtDuracion = findViewById(R.id.txtDuracion);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtSinopsis = findViewById(R.id.txtSinopsis);
        btnAniadirPelicula = findViewById(R.id.btnAniadirPelicula);
        spinnerGenero=findViewById(R.id.spinnerGenero);

        //Llenar de contenido el Spinner
        String []opciones={"Amor","Accion", "Ciencia Ficcion", "Comedia", "Drama", "Miedo", "Musical", "Misterio"};
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
        spinnerGenero.setAdapter(adapter);

        conectorBD = new ConectorBD(this);

        btnAniadirPelicula.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                conectorBD.abrir();
                conectorBD.insertarPelicula(txtTitulo.getText().toString(),txtAnio.getText().toString() , txtDuracion.getText().toString(),  txtDireccion.getText().toString(),  spinnerGenero.getSelectedItemPosition(),  txtSinopsis.getText().toString());
                conectorBD.cerrar();
                Toast.makeText(getBaseContext(), "La pelicula "+txtTitulo.getText().toString()+" ha sido añadida", Toast.LENGTH_SHORT).show();
                txtTitulo.setText("");
                txtAnio.setText("");
                txtDuracion.setText("");
                txtDireccion.setText("");
               // spinnerGenero.setSelection();
                txtSinopsis.setText("");
            }
        });
    }
}
