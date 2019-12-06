package es.ipo2.peliculas;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorListaPeliculas extends RecyclerView.Adapter<AdaptadorListaPeliculas.PeliculaViewHolder> {

    private ArrayList<Pelicula> peliculas;
    private OnItemSelectedListener listener;

    public interface OnItemSelectedListener {
       // void onPeliculaSeleccionada(int posicion);
        void onMenuContextualPelicula(int posicion, MenuItem menu);
    }

    public class PeliculaViewHolder extends RecyclerView.ViewHolder implements  View.OnCreateContextMenuListener, PopupMenu.OnMenuItemClickListener{  //View.OnClickListener

        private TextView lblTitulo;
        private TextView lblAnio;
        private ImageView imagPelicula;

        public PeliculaViewHolder(View view) {
            super(view);
            lblTitulo = view.findViewById(R.id.lblTitulo);
            lblAnio = view.findViewById(R.id.lblAnio);
            imagPelicula = view.findViewById(R.id.imagPelicula);
           // view.setOnClickListener(this);
            view.setOnCreateContextMenuListener(this);
        }
/*
        public void onClick(View v) {
            int posicion = getAdapterPosition();
            if (listener != null) {
                listener.onPeliculaSeleccionada(posicion);
            }

        }
*/
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            PopupMenu popup = new PopupMenu(v.getContext(), v);
            popup.getMenuInflater().inflate(R.menu.menu_contextual, popup.getMenu());
            popup.setOnMenuItemClickListener(this);
            popup.show();
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            if (listener != null) {
                listener.onMenuContextualPelicula(getAdapterPosition(), menuItem);
            }
            return true;
        }


    }
    public AdaptadorListaPeliculas(ArrayList<Pelicula> peliculas, OnItemSelectedListener listener) {
        this.listener = listener;
        this.peliculas = peliculas;
    }
    @Override
    public PeliculaViewHolder onCreateViewHolder(ViewGroup parent, int
            viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_lista,
                        parent, false);
        return new PeliculaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PeliculaViewHolder holder, int position) {
        Pelicula pelicula = peliculas.get(position);
        holder.lblTitulo.setText(peliculas.get(position).getTitulo());
        holder.lblAnio.setText(peliculas.get(position).getAnio());

        switch (peliculas.get(position).getGenero())
        {
            case 0: //Cargar imagen de peliculas tipo "amor"
                holder.imagPelicula.setImageResource(R.drawable.heart);
                break;
            case 1: //Cargar imagen de los peliculas tipo "accion"
                holder.imagPelicula.setImageResource(R.drawable.accion);
                break;
            case 2: //Cargar imagen de los peliculas tipo "Ciencia ficcion"
                holder.imagPelicula.setImageResource(R.drawable.alien);
                break;
            case 3: //Cargar imagen de los peliculas tipo "Comedia"
                holder.imagPelicula.setImageResource(R.drawable.happy);
                break;
            case 4: //Cargar imagen de los peliculas tipo "Drama"
                holder.imagPelicula.setImageResource(R.drawable.unhappy);
                break;
            case 5: //Cargar imagen de los peliculas tipo "Miedo"
                holder.imagPelicula.setImageResource(R.drawable.miedo);
                break;
            case 6: //Cargar imagen de los peliculas tipo "Musical"
                holder.imagPelicula.setImageResource(R.drawable.note);
                break;
            case 7: //Cargar imagen de los peliculas tipo "Misterio"
                holder.imagPelicula.setImageResource(R.drawable.misterio);
                break;
        }
    }
    @Override
    public int getItemCount() {
        return peliculas.size();
    }
}
