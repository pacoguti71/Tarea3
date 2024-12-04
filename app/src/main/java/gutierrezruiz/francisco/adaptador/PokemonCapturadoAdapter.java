package gutierrezruiz.francisco.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import gutierrezruiz.francisco.R;
import gutierrezruiz.francisco.datos.Pokemon;

public class PokemonCapturadoAdapter extends RecyclerView.Adapter<PokemonCapturadoAdapter.PokemonViewHolder> {

    private final List<Pokemon> pokemonList; // Cambiar a List<Pokemon>

    public PokemonCapturadoAdapter(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon_capturado, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);

        // Configurar el texto del nombre
        holder.textViewPokemonCapturado.setText(pokemon.getNombre());

        // Cargar la imagen con Glide
        Glide.with(holder.itemView.getContext())
                .load(pokemon.getFoto().getFrontDefault()) // URL de la imagen
                .placeholder(R.drawable.ic_launcher_background) // Imagen de carga
                .error(R.drawable.ic_launcher_foreground) // Imagen de error
                .into(holder.imageViewPokemonCapturado); // ImageView objetivo
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    static class PokemonViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPokemonCapturado;
        ImageView imageViewPokemonCapturado;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPokemonCapturado = itemView.findViewById(R.id.textViewPokemonCapturado);
            imageViewPokemonCapturado = itemView.findViewById(R.id.imageViewPokemonCapturado);
        }
    }
}
