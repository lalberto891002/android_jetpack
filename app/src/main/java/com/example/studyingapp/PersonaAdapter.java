package com.example.studyingapp;

import android.app.Person;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.*;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyingapp.Model.Persona;

import java.util.List;

public class PersonaAdapter extends ListAdapter<Persona,PersonaAdapter.PersonHolder> {
    private onItemClickListener listener;
    private static final DiffUtil.ItemCallback<Persona> DIFF_CALLBACK = new DiffUtil.ItemCallback<Persona>() {
        @Override
        public boolean areItemsTheSame(@NonNull Persona oldItem, @NonNull Persona newItem) {
            return oldItem.getID() == newItem.getID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Persona oldItem, @NonNull Persona newItem) {
            return oldItem.getNombre().equals(newItem.getNombre()) && oldItem.getApellidos().equals(newItem.getApellidos());
        }
    };

    public PersonaAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.persona_item_layout,parent,false);
        return new PersonHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonHolder holder, int position) {
        Persona persona = getItem(position);
        holder.nombre.setText(persona.getNombre());
        holder.edad.setText(persona.getEdad()+"");

    }


    public Persona getPersonAt(int index){
        return getItem(index);
    }


    class PersonHolder extends  RecyclerView.ViewHolder{
        private TextView nombre;
        private TextView edad;

        public PersonHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.text_view_nombre);
            edad = itemView.findViewById(R.id.text_view_edad);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.onItemClicked(getItem(position));
                }
            });
        }
    }

    public interface onItemClickListener{
        void onItemClicked(Persona persona);
    }

    public void setOnClickListener(onItemClickListener listener){
        this.listener = listener;
    }
}
