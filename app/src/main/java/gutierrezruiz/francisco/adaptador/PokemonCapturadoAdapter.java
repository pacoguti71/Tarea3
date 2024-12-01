package gutierrezruiz.francisco.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gutierrezruiz.francisco.R;

public class PokemonCapturadoAdapter extends RecyclerView.Adapter<PokemonCapturadoAdapter.PokemonViewHolder> {

    private List<String> pokemonList;

    public PokemonCapturadoAdapter(List<String> pokemonList) {
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
        String pokemonName = pokemonList.get(position);
        holder.textViewPokemonCapturado.setText(pokemonName);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    static class PokemonViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPokemonCapturado;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPokemonCapturado = itemView.findViewById(R.id.textViewPokemonCapturado);
        }
    }
}
