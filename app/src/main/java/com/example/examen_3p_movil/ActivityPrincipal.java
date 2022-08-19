package com.example.examen_3p_movil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ActivityPrincipal extends AppCompatActivity {
    ImageView ObjImagen;
    Button btnguardar, btnfoto, btnlista;
    String Tiempo[] = {"Dia", "Hora"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Init();

        btnlista.setOnClickListener(this::onClickLista);
    }

    private void Init() {
        btnguardar = (Button) findViewById(R.id.btnGuardar);
        btnfoto = (Button) findViewById(R.id.btnFoto);
        btnlista = (Button) findViewById(R.id.btnLista);
        ObjImagen = (ImageView) findViewById(R.id.ObjImagen);
    }

    private void onClickLista(View view) {
        Intent regis = new Intent(getApplicationContext(), ActivityLista.class);
        startActivity(regis);
    }
}