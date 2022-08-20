package com.example.examen_3p_movil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityLista extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private List<Medicamentos> medicamentosList = new ArrayList<Medicamentos>();
    ArrayAdapter<Medicamentos> medicamentosArrayAdapter;
    ListView DatosM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        DatosM =(ListView) findViewById(R.id.lv_medicamentos);
        iniciarFirebase();
        listaDatos();
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