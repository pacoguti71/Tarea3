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
import java.util.Arrays;
import java.util.List;

import gutierrezruiz.francisco.adaptador.PokedexAdapter;

public class PokedexFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pokedex, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPokedex);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<String> pokemonNames = Arrays.asList("Bulbasaur", "Charmander", "Squirtle", "Pikachu", "Eevee");
        PokedexAdapter adapter = new PokedexAdapter(pokemonNames);
        recyclerView.setAdapter(adapter);


    }
}
