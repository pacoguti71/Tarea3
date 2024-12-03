package gutierrezruiz.francisco.adaptador;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import gutierrezruiz.francisco.R;
import gutierrezruiz.francisco.datos.Pokemon;
import gutierrezruiz.francisco.pokeapi.PokeapiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.PokemonViewHolder> {

    private ArrayList<Pokemon> pokemonList;
    private OnPokemonClickListener onPokemonClickListener;

    public interface OnPokemonClickListener {
        void onPokemonClick(Pokemon pokemon);
    }

    public PokedexAdapter(ArrayList<Pokemon> pokemonList, OnPokemonClickListener onPokemonClickListener) {
        this.pokemonList = pokemonList;
        this.onPokemonClickListener = onPokemonClickListener;
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

        // Notifica al listener cuando se hace clic
        holder.textViewPokemon.setOnClickListener(v -> {
            if (onPokemonClickListener != null) {
                onPokemonClickListener.onPokemonClick(pokemon);
            }
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
