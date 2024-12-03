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

    private Retrofit retrofit; // Declaración de la variable Retrofit
    private ArrayList<Pokemon> listaPokemon = new ArrayList<>(); // Declaración de la variable listaPokemon
    private PokedexAdapter adapter; // Declaración de la variable adapter

    @Nullable
    @Override
    // Infla el layout del fragmento
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pokedex, container, false);
    }

    @Override
    // Cuando se crea el fragmento
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Crea una instancia de Retrofit que se utilizará para hacer las llamadas a la API
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Configura el RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPokedex);
        // Configura el LayoutManager y el Adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Crea un nuevo adapter con la lista de Pokémon
        adapter = new PokedexAdapter(listaPokemon);
        // Asigna el adapter al RecyclerView
        recyclerView.setAdapter(adapter);
        // Llama al método para obtener los Pokémon de la API
        obtenerPokemons();
    }

    // Método para obtener los Pokémon de la API
    private void obtenerPokemons() {
        // Crea una instancia del servicio de la API
        PokeapiService service = retrofit.create(PokeapiService.class);
        // Realiza la llamada a la API para obtener los Pokémon
        Call<PokemonRespuesta> pokemonRespuestaCall = service.obtenerPokemon();
        // Realiza la llamada de manera asíncrona
        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            // Cuando se recibe la respuesta de la API
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                // Comprueba si la respuesta es exitosa
                if (response.isSuccessful()) {
                    // Obtiene el cuerpo de la respuesta
                    PokemonRespuesta pokemonRespuesta = response.body();
                    // Comprueba si el cuerpo de la respuesta no es nulo y si contiene resultados
                    if (pokemonRespuesta != null && pokemonRespuesta.getResultados() != null) {
                        // Limpia la lista de Pokémon y añade los nuevos resultados
                        listaPokemon.clear();
                        listaPokemon.addAll(pokemonRespuesta.getResultados());
                        // Notifica al adapter que los datos han cambiado
                        adapter.notifyDataSetChanged();
                    } else {
                        // Muestra un mensaje de error si no se encontraron resultados
                        Toast.makeText(getContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Muestra un mensaje de error si la respuesta no es exitosa
                    Toast.makeText(getContext(), "Error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            // Cuando ocurre un error en la llamada
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                // Muestra un mensaje de error con el detalle del error
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

} // Fin class
