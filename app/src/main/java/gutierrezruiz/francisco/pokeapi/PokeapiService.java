package gutierrezruiz.francisco.pokeapi;


import gutierrezruiz.francisco.datos.PokemonRespuesta;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeapiService {
    @GET("pokemon?offset=0&limit=150")
    Call<PokemonRespuesta> obtenerPokemon();
}
