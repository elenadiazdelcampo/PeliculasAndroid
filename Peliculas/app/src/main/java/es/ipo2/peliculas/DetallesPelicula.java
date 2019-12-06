package es.ipo2.peliculas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class DetallesPelicula extends AppCompatActivity {

    private EditText txtTitulo;
    private EditText txtAnio;
    private EditText txtDuracion;
    private EditText txtDireccion;
    private Spinner spinnerGenero;
    private EditText txtSinopsis;
    private Button btnGuardar;
    private ConectorBD conectorBD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_pelicula);



        txtTitulo=findViewById(R.id.txtTitulo);
        txtAnio=findViewById(R.id.txtAnio);
        txtDuracion=findViewById(R.id.txtDuracion);
        txtSinopsis=findViewById(R.id.txtSinopsis);
        txtDireccion=findViewById(R.id.txtDireccion);
        txtSinopsis=findViewById(R.id.txtSinopsis);
        spinnerGenero=findViewById(R.id.spinnerGenero);
        btnGuardar = findViewById(R.id.btnGuardar);

        //Llenar de contenido el Spinner
        String []opciones={"Amor","Accion","Ciencia Ficcion","Comedia", "Drama", "Miedo","Musical", "Misterio"};
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
        spinnerGenero.setAdapter(adapter);

        conectorBD = new ConectorBD(this);

        Bundle bundle=getIntent().getExtras();
        txtTitulo.setText(bundle.getString("Titulo"));
        txtAnio.setText(bundle.getString("Anio"));
        txtDuracion.setText(bundle.getString("Duracion"));
        txtDireccion.setText(bundle.getString("Direccion"));
        spinnerGenero.setSelection(bundle.getInt("Genero"));
        txtSinopsis.setText(bundle.getString("Sinopsis"));


        btnGuardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent nuevaPelicula = new Intent();
                nuevaPelicula.putExtra("Titulo", txtTitulo.getText().toString());
                nuevaPelicula.putExtra("Anio", txtAnio.getText().toString());
                nuevaPelicula.putExtra("Duracion", txtDuracion.getText().toString());
                nuevaPelicula.putExtra("Direccion", txtDireccion.getText().toString());
                nuevaPelicula.putExtra("Genero", spinnerGenero.getSelectedItemPosition());
                nuevaPelicula.putExtra("Sinopsis", txtSinopsis.getText().toString()); //Integer.parseInt(txt..)
                setResult(RESULT_OK, nuevaPelicula);
                finish();

                conectorBD.abrir();
                conectorBD.ActualizarPeliculas(txtTitulo.getText().toString(),txtAnio.getText().toString(),txtDuracion.getText().toString(),txtDireccion.getText().toString()
                        ,spinnerGenero.getSelectedItemPosition(),txtSinopsis.getText().toString());
                conectorBD.cerrar();

            }
        });
    }
}
