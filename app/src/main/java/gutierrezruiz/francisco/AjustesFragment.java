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

    private FragmentAjustesBinding binding; // Declaramos el binding

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAjustesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Cuando pulsamos el botón aparece el dialogo de acerca de
        binding.textViewAcercade.setOnClickListener(v -> {
            // Inicializamos las variables
            String autor = getString(R.string.autor);// Autor de la aplicación
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
            String titulo = getString(R.string.acerca_de);
            String opcionAceptar = getString(R.string.aceptar);
            String mensaje = getString(R.string.app_realizada_por) + autor + getString(R.string.versi_n) + versionName;

            // Estabecemos el título y el mensaje del diálogo
            builder.setTitle(titulo).setMessage(mensaje).setPositiveButton(opcionAceptar, (dialog, id) -> dialog.cancel());
            // Devolvemos el diálogo creado
            builder.create().show();
        });

        // Cuando pulsamos el switch
        binding.switchEliminarPokemon.setOnCheckedChangeListener((buttonView, isChecked) -> {

        });

        // Cuando pulsamos el botón cerrar sesión
        binding.textViewCerrarSesion.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            requireActivity().finish();
        });

    }

} // Fin class