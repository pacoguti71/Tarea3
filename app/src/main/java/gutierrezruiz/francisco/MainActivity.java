package gutierrezruiz.francisco;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import gutierrezruiz.francisco.datos.Pokemon;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private NavController navController; // Declaración de la variable de NavController
private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtiene el TabLayout desde el layout
        tabLayout = findViewById(R.id.tabLayout);

        // Obtiene el NavController desde el NavHostFragment
        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = NavHostFragment.findNavController(navHostFragment);
        }

        // Listener para el TabLayout
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0: // Primera pestaña
                        navController.navigate(R.id.capturadosFragment);
                        break;
                    case 1: // Segunda pestaña
                        navController.navigate(R.id.pokedexFragment);
                        break;
                    case 2: // Tercera pestaña
                        navController.navigate(R.id.ajustesFragment);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // No es necesario implementar esto
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Opcional: volver a cargar el fragmento
            }
        });

        // Inicializa la base de datos Firebase
         db = FirebaseFirestore.getInstance();

    }






} // Fin class