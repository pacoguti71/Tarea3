package gutierrezruiz.francisco.pokeapi;


import gutierrezruiz.francisco.datos.Pokemon;
import gutierrezruiz.francisco.datos.PokemonRespuesta;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeapiService {

    // Obtiene una lista de Pokémon con un límite y un offset
    @GET("pokemon?offset=0&limit=150")
    Call<PokemonRespuesta> obtenerPokemon();

    // Obtiene información de un Pokémon específico por su nombre
    @GET("pokemon/{name}")
    Call<Pokemon> getPokemon(@Path("name") String name);
}
