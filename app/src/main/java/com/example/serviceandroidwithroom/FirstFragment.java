package com.example.serviceandroidwithroom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.serviceandroidwithroom.Model.PersonaModal;
import com.example.serviceandroidwithroom.Repository.PersonaRepository;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.ExecutionException;

public class FirstFragment extends Fragment {
    private PersonaRepository repository;
    private Integer numPersonas = 0;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        repository = new PersonaRepository(getActivity().getApplication());

        try {
            numPersonas = repository.GetAllPersonasAsyncTask().size();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonaModal model = new PersonaModal(
                        "Primer Nombre De Persona " + numPersonas,
                        "Segundo Nombre De Persona " + numPersonas,
                        "Primer Apellido De Persona " + numPersonas,
                        "Segundo Apellido De Persona " + numPersonas,
                        "Descripción de persona numero" + numPersonas
                );
                numPersonas ++;
                Snackbar.make(view, "Se agrego la persona : " + model.getPrimerNombre(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                insert(model);
            }
        });
    }

    public void insert(PersonaModal model) {
        this.repository.insert(model);
    }
}