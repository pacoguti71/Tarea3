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
import java.util.List;

import gutierrezruiz.francisco.R;
import gutierrezruiz.francisco.datos.Pokemon;
import gutierrezruiz.francisco.pokeapi.ApiService;
import gutierrezruiz.francisco.pokeapi.PokeapiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.PokemonViewHolder> {

    private ArrayList<Pokemon> pokemonList;
    private Retrofit retrofit;

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

        // Manejar el clic en el Pokémon
        holder.textViewPokemon.setOnClickListener(v -> {
            // Mostrar un mensaje de confirmación
            Toast.makeText(v.getContext(), "Has elegido a: " + pokemon.getNombre(), Toast.LENGTH_SHORT).show();

            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl("https://pokeapi.co/api/v2/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            // Crea una instancia del servicio de la API
            ApiService service = retrofit.create(ApiService.class);
            // Realiza la llamada a la API para obtener los detalles del Pokémon
            Call<Pokemon> pokemonCall = service.getPokemon(pokemon.getNombre().toLowerCase());
            // Realiza la llamada de manera asíncrona
            pokemonCall.enqueue(new Callback<Pokemon>() {

                public void onResponse(@NonNull Call<Pokemon> call, @NonNull Response<Pokemon> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        // Obtener datos del Pokémon
                        Pokemon pokemonCapturado = response.body();

                        // Guardar en Firebase
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        db.collection("capturados")
                                .document(pokemonCapturado.getNombre().toLowerCase())
                                .set(pokemonCapturado)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(v.getContext(), "Capturaste a: " + pokemonCapturado.getNombre(), Toast.LENGTH_SHORT).show();
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(v.getContext(), "Error al guardar en Firestore: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                });

                    } else {
                        Toast.makeText(v.getContext(), "Error al obtener datos del Pokémon", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Pokemon> call, @NonNull Throwable t) {
                    Toast.makeText(v.getContext(), "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }


            });

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
