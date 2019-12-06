package es.ipo2.peliculas;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ConectorBD {

    static final String NOMBRE_BD = "PeliculasLocal";
    private PeliculasSQLiteHelper dbHelper;
    private SQLiteDatabase db;
    /*Constructor*/
    public ConectorBD (Context ctx)
    {
        dbHelper = new PeliculasSQLiteHelper(ctx, NOMBRE_BD, null, 1);
    }
    /*Abre la conexión con la base de datos*/
    public ConectorBD abrir() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    /*Cierra la conexión con la base de datos*/
    public void cerrar()
    {
        if (db != null) db.close();
    }

    /*inserta una pelicula en la BD*/
    public void insertarPelicula(String Titulo, String Anio, String Duracion, String Direccion, int Genero, String Sinopsis)
    {
        String consultaSQL = "INSERT INTO Peliculas VALUES('"+Titulo+"','"+Anio+"','"+Duracion+"','"+Direccion+"','"+Genero+"','"+Sinopsis+"')";
        db.execSQL(consultaSQL);
    }
    /*devuelve todos las peliculas*/
    public Cursor listarPeliculas()
    {

        return db.rawQuery("SELECT * FROM Peliculas", null);
    }

    public void BorrrarPeliculas(String titulo)
    {
        String consultaSQL = "DELETE FROM Peliculas WHERE Titulo='"+titulo+"'";
        db.execSQL(consultaSQL);
    }

    public void ActualizarPeliculas(String titulo, String Anio, String Duracion, String Direccion, int Genero, String Sinopsis)
    {
        String consultaSQL = "UPDATE Peliculas SET Titulo='"+titulo+"', Anio='"+Anio+"', Duracion='"+Duracion+"',Direccion='"+Direccion+"',Genero='"+Genero+"',Sinopsis='"+Sinopsis+"'";
        db.execSQL(consultaSQL);
    }

}
