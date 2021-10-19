package com.example.viveronayon.repository;


import com.example.viveronayon.common.Constantes;
import com.example.viveronayon.entity.PlantaEntity;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Repositorio {
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public List<PlantaEntity> obtenerDatos(){
        List<PlantaEntity> lista=new ArrayList<>();
        db.collection(Constantes.COLECCION_PLANTA).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                List<DocumentSnapshot> plantaResult = task.getResult().getDocuments();
                for (DocumentSnapshot dato: plantaResult){
                    PlantaEntity plantaEntity=new PlantaEntity();
                    plantaEntity.setNombreCientifico(dato.getString(Constantes.CAMPO_NOMBRE_CIENTIFICO));
                    plantaEntity.setNombre(dato.getString(Constantes.CAMPO_NOMBRE_COMUN));
                    plantaEntity.setDescripcion(dato.getString(Constantes.CAMPO_DESCRIPCION));
                    plantaEntity.setCuidados(dato.getString(Constantes.CAMPO_CUIDADOS));
                    plantaEntity.setImagen(dato.getString(Constantes.CAMPO_IMAGEN));
                    lista.add(plantaEntity);
                }
            }
        });

        return lista;
    }
}
