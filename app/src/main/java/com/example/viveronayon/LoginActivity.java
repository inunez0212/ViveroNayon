package com.example.viveronayon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viveronayon.common.Constantes;
import com.example.viveronayon.entity.EmpresaEntity;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;
import java.util.logging.Logger;

public class LoginActivity extends AppCompatActivity {

    String passwordEmpresa;
    Button btnLogin;
    EditText editTextTextPassword;
    TextView textViewNombreVivero;
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
    }

    private void clickLogin() {
        if(editTextTextPassword.getText().toString().equals(passwordEmpresa)){
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(), "Contraseña incorrecta",
                    Toast.LENGTH_LONG).show();
            editTextTextPassword.getText();
        }
    }
}