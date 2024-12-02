package gutierrezruiz.francisco.datos;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class PokemonRespuesta {

    @SerializedName("results") // Mapea el campo "results" del JSON
    private ArrayList<Pokemon> resultados;

    public ArrayList<Pokemon> getResultados() {
        return resultados;
    }

    public void setResultados(ArrayList<Pokemon> resultados) {
        this.resultados = resultados;
    }
}
