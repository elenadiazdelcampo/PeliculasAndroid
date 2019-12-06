package es.ipo2.peliculas;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Principal extends AppCompatActivity implements AdaptadorListaPeliculas.OnItemSelectedListener{


    private RecyclerView lstPeliculas;

    private ArrayList<Pelicula> peliculas = new ArrayList<>();
    private AdaptadorListaPeliculas adaptadorLista;

    private int peliculaSeleccionada;

    private ConectorBD conectorBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Bundle bundle=getIntent().getExtras();
        Toast notificacion;
        notificacion = Toast.makeText(this, "Hola "+bundle.getString("usuario"),
                Toast.LENGTH_LONG);
        notificacion.show();

        lstPeliculas = findViewById(R.id.lstPeliculas);


        RecyclerView.LayoutManager mLayoutManager = new
                LinearLayoutManager(getApplicationContext());
        lstPeliculas.setLayoutManager(mLayoutManager);
        adaptadorLista = new AdaptadorListaPeliculas(peliculas, this);
        lstPeliculas.setAdapter(adaptadorLista);
        lstPeliculas.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));

        conectorBD = new ConectorBD(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.acercaDe:
                Log.d("LogCat","Pulsó la opción de menú Acerca de...");
                //Se muestra una ventana de diálogo
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Acerca de...");
                builder.setMessage("Aplicación creada por Elena Díaz y Sara Lara");
                builder.setPositiveButton("OK",null);
                builder.create();
                builder.show();
                break;
            case R.id.aniadirPeliculas:
                Log.d("LogCat","Pulsó la opción de menú Añadir Contacto");
                //Lanzar la segunda Activity
                Intent i = new Intent(this, Aniadir.class );
                startActivity(i);
            case R.id.listarPeliculas:
                Log.d("LogCat","Pulsó la opción de menú listar Peliculas");
                peliculas.removeAll(peliculas);
                conectorBD.abrir();
                Cursor c = conectorBD.listarPeliculas();
                if (c.moveToFirst())
                {
                    do {
                        Pelicula pelicula = new Pelicula(null, null, null, null, 0, null);
                        pelicula.setTitulo(c.getString(0));
                        pelicula.setAnio(c.getString(1));
                        pelicula.setDuracion(c.getString(2));
                        pelicula.setDireccion(c.getString(3));
                        pelicula.setGenero(c.getInt(4));
                        pelicula.setSinopsis(c.getString(5));
                        peliculas.add(pelicula);
                    } while (c.moveToNext());
                }
                c.close();
                conectorBD.cerrar();
                lstPeliculas.getAdapter().notifyDataSetChanged();
        }
        return true;
    }


    public void onMenuContextualPelicula(int posicion, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.verPelicula:
                Log.d("LogCat","Pulsó la opción del menú contextual Ver Detalles");
                Intent i = new Intent(this, DetallesPelicula.class);
                i.putExtra("Titulo", peliculas.get(posicion).getTitulo());
                i.putExtra("Anio", peliculas.get(posicion).getAnio());
                i.putExtra("Duracion",peliculas.get(posicion).getDuracion());
                i.putExtra("Direccion",peliculas.get(posicion).getDireccion());
                i.putExtra("Genero",peliculas.get(posicion).getGenero());
                i.putExtra("Sinopsis", peliculas.get(posicion).getSinopsis());
                peliculaSeleccionada = posicion;
                startActivityForResult(i, 1234);

             
                break;
            case R.id.borrarPelicula:
                Log.d("LogCat","Pulsó la opción de menú contextual Borrar Contacto");
                String borrar= peliculas.get(posicion).getTitulo();
                conectorBD.abrir();
                conectorBD.BorrrarPeliculas(borrar);
                conectorBD.cerrar();
                peliculas.remove(peliculas.get(posicion));
                Toast.makeText(getBaseContext(), "La pelicula "+borrar+ "  ha sido eliminada", Toast.LENGTH_SHORT).show();
                adaptadorLista.notifyDataSetChanged();
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent
            data){
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        if (requestCode == 1234){
            if (resultCode == RESULT_OK){
                Pelicula peliculaModificado = new Pelicula(b.getString("Titulo"),b.getString("Anio"),b.getString("Duracion"),b.getString("Direccion"), b.getInt("Genero"),b.getString("Sinopsis"));
                peliculas.set(peliculaSeleccionada, peliculaModificado);
                adaptadorLista.notifyDataSetChanged();
            }
        }
    }
}
