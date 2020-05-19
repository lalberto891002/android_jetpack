package com.example.studyingapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyingapp.Model.Persona;
import com.example.studyingapp.PersonaAdapter;
import com.example.studyingapp.R;
import com.example.studyingapp.ViewModels.PersonaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class personasActivity extends AppCompatActivity implements PersonaAdapter.onItemClickListener{
    private PersonaViewModel personaViewModel;
    private boolean primera_vez;
    private RecyclerView reciRecyclerView;
    public static int NOVA_PERSONA_REQUEST = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personas);
        Button buttonVOltar = findViewById(R.id.lista_personas_btn_voltar);
        reciRecyclerView = findViewById(R.id.listaPersonasReciclerView);
        reciRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reciRecyclerView.setHasFixedSize(true);
        primera_vez = true;
        final PersonaAdapter adapter = new PersonaAdapter();
        adapter.setOnClickListener(this);

        personaViewModel = ViewModelProviders.of(this).get(PersonaViewModel.class);
        personaViewModel.getAllPersons().observe(this, new Observer<List<Persona>>() {
            @Override
            public void onChanged(List<Persona> personas) {
                //Update reciclerview
                if(primera_vez) {
                    reciRecyclerView.setAdapter(adapter);
                    primera_vez = false;
                }

                //Toast.makeText(getApplicationContext(),"Observe",Toast.LENGTH_LONG).show();
                adapter.submitList(personas);
            }
        });

        buttonVOltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(personasActivity.this,novaPersonaActivty.class);
                startActivityForResult(intent,NOVA_PERSONA_REQUEST);
            }
        });

       ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                personaViewModel.delete(adapter.getPersonAt(viewHolder.getAdapterPosition()));
            }
        });
       itemTouchHelper.attachToRecyclerView(reciRecyclerView);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NOVA_PERSONA_REQUEST && resultCode == RESULT_OK){
            if(data!= null){
                String nombre = data.getStringExtra(novaPersonaActivty.NOVA_PERSONA_NOMBRE);
                String edad = data.getStringExtra(novaPersonaActivty.NOVA_PERSONA_EDAD);
                String ciudad = data.getStringExtra(novaPersonaActivty.NOVA_PERSONA_CIUDAD);
                String pais = data.getStringExtra(novaPersonaActivty.NOVA_PERSONA_PAIS);
                int edad_entero;
                try {
                     edad_entero = Integer.parseInt(edad);
                }catch (Exception ex){
                    edad_entero = 0;
                }
                Persona persona = new Persona(nombre,"Nuevo Apellido",pais,ciudad,"urloFoto",edad_entero);
                personaViewModel.insert(persona);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.lista_persona_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_action_delete_all_persons:
                personaViewModel.deleteAll();
                Toast.makeText(getApplicationContext(),"all perople deleted",Toast.LENGTH_LONG).show();
                break;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClicked(Persona persona) {
        //todo startactivity
        Intent intent = new Intent(this,detallesPersonaActivity.class);
        intent.putExtra("nombre",persona.getNombre());
        intent.putExtra("edad",persona.getEdad());
        intent.putExtra("apellidos",persona.getApellidos());
        intent.putExtra("ciudad",persona.getCiudad());
        intent.putExtra("pais",persona.getPais());
        intent.putExtra("urlFoto",persona.getUrlFoto());
        startActivity(intent);


    }
}
