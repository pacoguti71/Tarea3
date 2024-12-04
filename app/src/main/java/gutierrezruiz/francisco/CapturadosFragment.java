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
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import gutierrezruiz.francisco.adaptador.PokemonCapturadoAdapter;
import gutierrezruiz.francisco.datos.Pokemon;

public class CapturadosFragment extends Fragment {

    private RecyclerView recyclerView;
    private PokemonCapturadoAdapter adapter;
    private final List<Pokemon> pokemonNames = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_capturados, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewPokemonCapturado);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializa el adaptador con la lista vacía
        adapter = new PokemonCapturadoAdapter(pokemonNames);
        recyclerView.setAdapter(adapter);

        // Cargar los Pokémon capturados desde Firebase
        cargarPokemonCapturados();
    }

    private void cargarPokemonCapturados() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("capturados")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Pokemon> pokemonCapturados = new ArrayList<>();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Pokemon pokemon = document.toObject(Pokemon.class);
                        pokemonCapturados.add(pokemon);
                    }

                    // Configurar el RecyclerView con los datos
                    PokemonCapturadoAdapter adapter = new PokemonCapturadoAdapter(pokemonCapturados);
                    recyclerView.setAdapter(adapter);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Error al cargar Pokémon capturados: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }
}

