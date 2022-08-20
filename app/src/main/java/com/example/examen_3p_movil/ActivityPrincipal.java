package com.example.examen_3p_movil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.graphics.ImageBitmap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;

public class ActivityPrincipal extends AppCompatActivity {
    ImageView ObjImagen;
    Bitmap foto = null;
    CollectionReference collectionReference;
    Button btnguardar, btnfoto, btnlista;
    TextView Descripcion, Cantidad, Tiempo, Periosidad;
    Medicamentos medicamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnguardar = (Button) findViewById(R.id.btnGuardar);
        btnfoto = (Button) findViewById(R.id.btnFoto);
        btnlista = (Button) findViewById(R.id.btnLista);
        ObjImagen = (ImageView) findViewById(R.id.ObjImagen);
        Tiempo = (TextView) findViewById(R.id.txtTiempo);
        Descripcion = (TextView) findViewById(R.id.txtDescripcion);
        Cantidad = (TextView) findViewById(R.id.txtCantidad);
        Periosidad = (TextView) findViewById(R.id.txtPeriocidad);



        btnguardar.setOnClickListener(this::onClickGuardar);

    }

    private void onClickGuardar(View view) {
        try {
            CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("medicamentos");
            DocumentReference documentReference = collectionReference.document();
            String id = documentReference.getId();
            medicamentos = new Medicamentos();
            medicamentos.setKey(id);
            //medicamentos.setId_medicamentos(giveFiveDigitsNumber());
            medicamentos.setDescripcion(Descripcion.getText().toString());
            medicamentos.setCantidad(Cantidad.getText().toString());
            medicamentos.setTiempo(Tiempo.getText().toString());
            medicamentos.setPeriocidad(Periosidad.getText().toString());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            //foto.compress(Bitmap.CompressFormat.JPEG, 100,outputStream);
            byte[] byteArray = outputStream.toByteArray();
            medicamentos.setFoto(Base64.encodeToString(byteArray, Base64.DEFAULT));
            collectionReference.document(id).set(medicamentos).addOnSuccessListener(unused -> {
                Toast.makeText(this, "Datos Guardados", Toast.LENGTH_SHORT).show();
               //LimpiarCampos();
            });

        }catch (Exception ex){
            Toast.makeText(this, "Error al Guardar", Toast.LENGTH_LONG).show();
        }
        
    }


    private void onClickLista(View view) {
        Intent regis = new Intent(getApplicationContext(), ActivityLista.class);
        startActivity(regis);
    }


}