package gutierrezruiz.francisco.registro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import gutierrezruiz.francisco.MainActivity;
import gutierrezruiz.francisco.databinding.ActivityRegistroBinding;

public class RegistroActivity extends AppCompatActivity {
    private ActivityRegistroBinding binding; // Declaración de la vista
    private FirebaseAuth firebaseAuth; // Firebase Authentication

    @Override
    // Método para crear la actividad
    protected void onCreate(Bundle savedInstanceState) {
        // Llamada al método onCreate de la clase padre
        super.onCreate(savedInstanceState);
        // Infla la vista
        binding = ActivityRegistroBinding.inflate(getLayoutInflater()); // Inflate the layout
        // Establece el layout de la actividad
        setContentView(binding.getRoot());

        // Inicializa FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        // Acción del botón de registro
        binding.botonRegistrar.setOnClickListener(view -> {
            // Obtiene los datos introducidos por el usuario
            String email = binding.editTextEmail.getText().toString().trim();
            String password = binding.editTextPassword.getText().toString().trim();

            // Comprueba que los campos no estén vacíos
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                // Comprueba que la contraseña tenga al menos 6 caracteres
            } else if (password.length() < 6) {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            } else {
                // Llama al método para registrar al usuario
                registrarUsuario(email, password);
            }
        });
    } // Fin onCreate

    // Método para registrar al usuario
    private void registrarUsuario(String email, String password) {
        // Utiliza FirebaseAuth para registrar al usuario
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    // Comprueba si la autenticación fue exitosa
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
                        // Redirige a la pantalla principal
                        Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                    } else {
                        // Si la autenticación falla, muestra un mensaje de error
                        Toast.makeText(this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
} // Fin class
