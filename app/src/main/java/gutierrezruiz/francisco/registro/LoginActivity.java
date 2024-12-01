package gutierrezruiz.francisco.registro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import gutierrezruiz.francisco.MainActivity;
import gutierrezruiz.francisco.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding; // Declaración de la vista
    private FirebaseAuth firebaseAuth; // Firebase Authentication

    @Override
    // Método para crear la actividad
    protected void onCreate(Bundle savedInstanceState) {
        // Llamada al método onCreate de la clase padre
        super.onCreate(savedInstanceState);
        // Infla la vista
        binding = ActivityLoginBinding.inflate(getLayoutInflater()); // Inflate the layout
        // Establece el layout de la actividad
        setContentView(binding.getRoot());

        // Inicializa FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        // Inicia sesión. Si pulsas el botón, llama al método autenticarUsuario
        binding.botonLogin.setOnClickListener(view -> {
            // Obtiene los datos introducidos por el usuario
            String email = binding.editTextEmail.getText().toString().trim();
            String password = binding.editTextPassword.getText().toString().trim();

            // Comprueba que los campos no estén vacíos
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                // Llama al método para autenticar al usuario
                autenticarUsuario(email, password);
            }
        });

        // Redirige al registro si pulsas el TextView
        binding.TextViewRegistrar.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
            startActivity(intent);
        });
    } // Fin onCreate

    // Método para autenticar al usuario
    private void autenticarUsuario(String email, String password) {
        // Utiliza FirebaseAuth para iniciar sesión
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    // Comprueba si la autenticación fue exitosa
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                        // Redirige a la pantalla principal
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        // Si la autenticación falla, muestra un mensaje de error
                        Toast.makeText(this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
} // Fin class