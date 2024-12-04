package gutierrezruiz.francisco;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import gutierrezruiz.francisco.adaptador.PokedexAdapter;
import gutierrezruiz.francisco.datos.Pokemon;
import gutierrezruiz.francisco.datos.PokemonRespuesta;
import gutierrezruiz.francisco.pokeapi.PokeapiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokedexFragment extends Fragment {

    private Retrofit retrofit;
    private final ArrayList<Pokemon> listaPokemon = new ArrayList<>();
    private PokedexAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pokedex, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPokedex);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PokedexAdapter(listaPokemon, this::onPokemonClick);
        recyclerView.setAdapter(adapter);

        obtenerPokemons();
    }

    private void onPokemonClick(Pokemon pokemon) {
        Toast.makeText(getContext(), "Has elegido a: " + pokemon.getNombre(), Toast.LENGTH_SHORT).show();

        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<Pokemon> pokemonCall = service.getPokemon(pokemon.getNombre().toLowerCase());

        pokemonCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(@NonNull Call<Pokemon> call, @NonNull Response<Pokemon> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Pokemon pokemonCapturado = response.body();

                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("capturados")
                            .document(pokemonCapturado.getNombre().toLowerCase())
                            .set(pokemonCapturado)
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(getContext(), "Capturaste a: " + pokemonCapturado.getNombre(), Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(getContext(), "Error al guardar en Firestore: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });

                } else {
                    Toast.makeText(getContext(), "Error al obtener datos del Pokémon", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Pokemon> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void obtenerPokemons() {
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokemonRespuesta> pokemonRespuestaCall = service.obtenerPokemon();

        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                if (response.isSuccessful()) {
                    PokemonRespuesta pokemonRespuesta = response.body();
                    if (pokemonRespuesta != null && pokemonRespuesta.getResultados() != null) {
                        listaPokemon.clear();
                        listaPokemon.addAll(pokemonRespuesta.getResultados());
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

