package gutierrezruiz.francisco.adaptador;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import gutierrezruiz.francisco.R;
import gutierrezruiz.francisco.datos.Pokemon;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.PokemonViewHolder> {

    private ArrayList<Pokemon> pokemonList;

    public PokedexAdapter(ArrayList<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokedex, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);
        holder.textViewPokemon.setText(pokemon.getNombre());

        holder.textViewPokemon.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Clicked: " + pokemon.getNombre(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return (pokemonList != null) ? pokemonList.size() : 0;
    }

    static class PokemonViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPokemon;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPokemon = itemView.findViewById(R.id.textViewNombrePokemon);
        }
    }
}
