package com.example.examen_3p_movil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityLista extends AppCompatActivity {
    ImageView Foto;
    Button btnactualizar, btneliminar;
    TextView Ide, Descripcion, Cantidad, Tiempo, Periosidad;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private List<Medicamentos> medicamentosList = new ArrayList<Medicamentos>();
    ArrayAdapter<Medicamentos> medicamentosArrayAdapter;
    ListView DatosM;

    Medicamentos mediSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        btnactualizar = (Button) findViewById(R.id.btnActualizar);
        btneliminar = (Button) findViewById(R.id.btnEliminar);
        Tiempo = (TextView) findViewById(R.id.txtEdiTiempo);
        Descripcion = (TextView) findViewById(R.id.txtEdiDescripcion);
        Cantidad = (TextView) findViewById(R.id.txtEdiCantidad);
        Periosidad = (TextView) findViewById(R.id.txtEdiPeriocidad);

        DatosM =(ListView) findViewById(R.id.lv_medicamentos);
        iniciarFirebase();
        listaDatos();

        DatosM.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mediSelected = (Medicamentos) parent.getItemAtPosition(position);
                Descripcion.setText(mediSelected.getDescripcion());
                Cantidad.setText(mediSelected.getCantidad());
                Tiempo.setText(mediSelected.getTiempo());
                Periosidad.setText(mediSelected.getPeriocidad());

            }
        });

        btnactualizar.setOnClickListener(this::onClickActualizar);
        btneliminar.setOnClickListener(this::onClickEliminar);

    }

    private void onClickEliminar(View view) {
        Medicamentos m = new Medicamentos();
        m.setUid(mediSelected.getUid());
        databaseReference.child("Medicamentos").child(m.getUid()).removeValue();
        Toast.makeText(this, "Medicamento Eliminado", Toast.LENGTH_SHORT).show();
        LimpiarCajas();
    }

    private void LimpiarCajas() {
        Descripcion.setText("");
        Cantidad.setText("");
        Tiempo.setText("");
        Periosidad.setText("");
    }

    private void onClickActualizar(View view) {
        Medicamentos m = new Medicamentos();
        m.setUid(mediSelected.getUid());
        m.setDescripcion(Descripcion.getText().toString().trim());
        m.setCantidad(Cantidad.getText().toString().trim());
        m.setTiempo(Tiempo.getText().toString().trim());
        m.setPeriocidad(Periosidad.getText().toString().trim());
        databaseReference.child("Medicamentos").child(m.getUid()).setValue(m);
        Toast.makeText(this, "Medicamento Actualizado", Toast.LENGTH_SHORT).show();
        LimpiarCajas();
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void listaDatos() {
        databaseReference.child("Medicamentos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                medicamentosList.clear();

                for (DataSnapshot objSnaptshot : snapshot.getChildren()){
                    Medicamentos medicamentos = objSnaptshot.getValue(Medicamentos.class);
                    medicamentosList.add(medicamentos);

                    medicamentosArrayAdapter = new ArrayAdapter<Medicamentos>(ActivityLista.this, android.R.layout.simple_list_item_1, medicamentosList);
                    DatosM.setAdapter(medicamentosArrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




}