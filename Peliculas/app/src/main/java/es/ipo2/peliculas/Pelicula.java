package es.ipo2.peliculas;

public class Pelicula {
    private String Titulo;
    private String Anio;
    private String Duracion;
    private String Direccion;
    private int Genero;
    private String Sinopsis;


    public Pelicula(String titulo, String anio, String duracion, String direccion, int genero, String sinopsis ) {
        Titulo = titulo;
        Anio = anio;
        Duracion = duracion;
        Direccion = direccion;
        Genero = genero;
        Sinopsis = sinopsis;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getAnio() {
        return Anio;
    }

    public void setAnio(String anio) {
        Anio = anio;
    }

    public String getDuracion() {
        return Duracion;
    }

    public void setDuracion(String duracion) {
        Duracion = duracion;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public int getGenero() {
        return Genero;
    }

    public void setGenero(int genero) {
        Genero = genero;
    }

    public String getSinopsis() {
        return Sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        Sinopsis = sinopsis;
    }
}
