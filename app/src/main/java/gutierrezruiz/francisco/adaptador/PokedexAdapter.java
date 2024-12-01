package gutierrezruiz.francisco.adaptador;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import gutierrezruiz.francisco.R;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.PokemonViewHolder> {

    private List<String> pokemonList;

    public PokedexAdapter(List<String> pokemonList) {
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
        String pokemonName = pokemonList.get(position);
        holder.textViewPokemon.setText(pokemonName);

        holder.textViewPokemon.setOnClickListener(v -> {
            // añadir pokemon a capturados
            Toast.makeText(v.getContext(), "Clicked: ", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    static class PokemonViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPokemon;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPokemon = itemView.findViewById(R.id.textViewNombrePokemon);
        }
    }
}
