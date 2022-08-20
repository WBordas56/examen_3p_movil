package com.example.examen_3p_movil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ActivityPrincipal extends AppCompatActivity {
    ImageView Foto;
    Button btnguardar, btnfoto, btnlista;
    TextView Descripcion, Cantidad, Tiempo, Periosidad;
    Medicamentos medicamentos = new Medicamentos();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Init();

        btnguardar.setOnClickListener(this::onClickRegister);
        btnlista.setOnClickListener(this::onClickLista);
        btnfoto.setOnClickListener(this::onClickFoto);

        iniciarFirebase();

    }

    private void onClickFoto(View view) {
        Toast.makeText(this, "¡No Disponible!", Toast.LENGTH_SHORT).show();
    }


    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void onClickRegister(View view) {
        String desc = Descripcion.getText().toString();
        String cant = Cantidad.getText().toString();
        String tiemp = Tiempo.getText().toString();
        String periosid = Periosidad.getText().toString();

        if(desc.equals("")||cant.equals("")||tiemp.equals("")||periosid.equals(""))
        {
            Validar();
        }
        else
        {
            medicamentos.setUid(UUID.randomUUID().toString());
            medicamentos.setDescripcion(desc);
            medicamentos.setCantidad(cant);
            medicamentos.setTiempo(tiemp);
            medicamentos.setPeriocidad(periosid);
            databaseReference.child("Medicamentos").child(medicamentos.getUid()).setValue(medicamentos);
            Toast.makeText(this, "Medicamento Guardado", Toast.LENGTH_SHORT).show();
            LimpiarCajas();
        }
    }

    private void LimpiarCajas() {
        Descripcion.setText("");
        Cantidad.setText("");
        Tiempo.setText("");
        Periosidad.setText("");
    }

    private void Validar() {
        String desc = Descripcion.getText().toString();
        String cant = Cantidad.getText().toString();
        String tiemp = Tiempo.getText().toString();
        String periosid = Periosidad.getText().toString();
        if (desc.equals(""))
        {
            Descripcion.setError("*Obligatorio");
        }
        else if (cant.equals(""))
        {
            Cantidad.setError("*Obligatorio");
        }
        else if (tiemp.equals(""))
        {
            Tiempo.setError("*Obligatorio");
        }
        else if (periosid.equals(""))
        {
            Periosidad.setError("*Obligatorio");
        }

    }

    private void onClickLista(View view) {
        Intent regis = new Intent(getApplicationContext(), ActivityLista.class);
        startActivity(regis);
    }

    private void Init() {
        btnguardar = (Button) findViewById(R.id.btnGuardar);
        btnfoto = (Button) findViewById(R.id.btnFoto);
        btnlista = (Button) findViewById(R.id.btnLista);
        Foto = (ImageView) findViewById(R.id.ObjImagen);
        Tiempo = (TextView) findViewById(R.id.txtTiempo);
        Descripcion = (TextView) findViewById(R.id.txtDescripcion);
        Cantidad = (TextView) findViewById(R.id.txtCantidad);
        Periosidad = (TextView) findViewById(R.id.txtPeriocidad);
    }
}