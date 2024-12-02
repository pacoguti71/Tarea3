package gutierrezruiz.francisco;

import android.app.AlertDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

import gutierrezruiz.francisco.databinding.FragmentAjustesBinding;

public class AjustesFragment extends Fragment {

    private FragmentAjustesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAjustesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.textViewAcercade.setOnClickListener(v -> {
// Inicializamos las variables
            String autor = "Francisco Gutiérrez Ruiz";// Autor de la aplicación
            String versionName; // Versión de la aplicación

            // Intentamos obtener la información del paquete y la versión
            try {
                PackageInfo packageInfo = requireActivity().getPackageManager().getPackageInfo(requireActivity().getPackageName(), 0);
                versionName = packageInfo.versionName;
            } catch (PackageManager.NameNotFoundException e) {
                versionName = "0"; // En caso de error
            }

            // Creamos el objeto AlertDialog.Builder
            AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
            String titulo = "Acerca de...";
            String opcionAceptar = "Aceptar";
            String mensaje = "App realizada por " + autor + " versión " + versionName;

            // Estabecemos el título y el mensaje del diálogo
            // Cuando pulsamos el botón cerramos el diálogo
            builder.setTitle(titulo).setMessage(mensaje).setPositiveButton(opcionAceptar, (dialog, id) -> dialog.cancel());
            // Devolvemos el diálogo creado
            builder.create().show();

        });

        binding.switchEliminarPokemon.setOnCheckedChangeListener((buttonView, isChecked) -> {

        });

        binding.textViewCerrarSesion.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            requireActivity().finish();
        });


    }
}