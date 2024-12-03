package gutierrezruiz.francisco.pokeapi;
import gutierrezruiz.francisco.datos.Pokemon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("pokemon/{name}")
    Call<Pokemon> getPokemon(@Path("name") String name);
}
