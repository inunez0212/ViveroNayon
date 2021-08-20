package com.example.viveronayon.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viveronayon.R;
import com.example.viveronayon.common.Constantes;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    public static String passwordEmpresa;
    Button btnLogin;
    EditText editTextTextPassword;
    TextView textViewNombreVivero;
    ImageView imageQr;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = FirebaseFirestore.getInstance();

        this.iniciarComponentes();
        this.instanciarInformacionEmpresa();


        //Eventos
        btnLogin.setOnClickListener(view -> {
            clickLogin();
        });

        //Eventos
        imageQr.setOnClickListener(view -> {
            clickPassword();
        });

    }

    private void clickPassword() {
        Intent i = new Intent(LoginActivity.this, CamaraActivity.class);
        startActivity(i);
    }

    /**
     * Obtiene el nombre de la empresa y lo inserta en el text view de login
     */
    private void instanciarInformacionEmpresa() {
        //Abre la coleccion empresa
        db.collection(Constantes.COLECCION_EMPRESA).get().addOnCompleteListener(task -> {
            //valida si se ejecuto correctamente
            if(task.isSuccessful()) {
                //Obtiene el resultado
                List<DocumentSnapshot> empresaResult = task.getResult().getDocuments();

                //Como solo hay un resultado de empresa obtenemos iterando directamente al primero
                textViewNombreVivero.setText(empresaResult.iterator().next().
                        getString(Constantes.CAMPO_NOMBRE));
                // Obtenemos el password
                passwordEmpresa= empresaResult.iterator().next().
                        getString(Constantes.CAMPO_PASSWORD);

            }else{
                //Abre un dialogo con el mensaje de error
                Toast.makeText(getApplicationContext(), "Error al obtener datos de empresa",
                        Toast.LENGTH_LONG).show();
            }
        });
    }




    private void iniciarComponentes() {
        btnLogin = findViewById(R.id.buttonLogin);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        textViewNombreVivero = findViewById(R.id.textViewNombreVivero);
        imageQr= findViewById(R.id.imageQr);
    }

    private void clickLogin() {
        //Comparamos el password ingresado en la app con el password almacenado en la base de datos
        if(editTextTextPassword.getText().toString().equals(passwordEmpresa)){
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(), "Contraseña incorrecta",
                    Toast.LENGTH_LONG).show();
        }
    }
}