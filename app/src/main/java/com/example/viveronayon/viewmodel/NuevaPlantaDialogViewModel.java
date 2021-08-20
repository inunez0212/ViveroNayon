package com.example.viveronayon.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.viveronayon.entity.PlantaEntity;
import com.example.viveronayon.repository.Repositorio;

import java.util.List;

public class NuevaPlantaDialogViewModel extends AndroidViewModel {
    private List<PlantaEntity> allPlanta;
    private Repositorio repositorio;

    public NuevaPlantaDialogViewModel(Application application) {
        super(application);
        repositorio = new Repositorio();
        allPlanta=repositorio.obtenerDatos();

    }

    // Freagmento que necesita recibir la lista
    public List<PlantaEntity> getAllPlanta(){

        return allPlanta;
    }


}