package com.example.examen_3p_movil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class ActivityPrincipal extends AppCompatActivity {
    ImageView ObjImagen;
    Button btnguardar, btnfoto, btnlista;
    String tiempo[] = {"Dia", "Hora"};
    Spinner spinner;
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
        spinner = (Spinner) findViewById(R.id.spTiempo);

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tiempo);
        spinner.setAdapter(adapter);
    }

    private void onClickLista(View view) {
        Intent regis = new Intent(getApplicationContext(), ActivityLista.class);
        startActivity(regis);
    }


}