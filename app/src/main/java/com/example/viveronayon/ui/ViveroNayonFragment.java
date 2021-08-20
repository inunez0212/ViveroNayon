package com.example.viveronayon.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.viveronayon.viewmodel.NuevaPlantaDialogViewModel;
import com.example.viveronayon.R;
import com.example.viveronayon.common.Constantes;
import com.example.viveronayon.entity.PlantaEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ViveroNayonFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private NuevaPlantaDialogViewModel nuevaPlantaDialogViewModel;
    private List<PlantaEntity> plantaList= new ArrayList<>();
    private MyViveroNayonRecyclerViewAdapter adapterPlanta;
    FirebaseFirestore db;
    RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ViveroNayonFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ViveroNayonFragment newInstance(int columnCount) {
        ViveroNayonFragment fragment = new ViveroNayonFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=  FirebaseFirestore.getInstance();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_vivero_nayon_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(mColumnCount,StaggeredGridLayoutManager.VERTICAL));
            }



            db.collection("planta").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            plantaList=new ArrayList<>();
                            for(DocumentSnapshot document: task.getResult()){
                                //PlantaEntity plantaItem = document.toObject(PlantaEntity.class);

                                PlantaEntity plantaEntity=new PlantaEntity();
                                plantaEntity.setNombreCientifico(document.getString(Constantes.CAMPO_NOMBRE_CIENTIFICO));
                                plantaEntity.setNombre(document.getString(Constantes.CAMPO_NOMBRE_COMUN));
                                plantaEntity.setDescripcion(document.getString(Constantes.CAMPO_DESCRIPCION));
                                plantaEntity.setCuidados(document.getString(Constantes.CAMPO_CUIDADOS));
                                //plantaEntity.setImagen(document.getString(Constantes.URL_FOTO+Constantes.CAMPO_IMAGEN));
                                plantaList.add(plantaEntity);

                            }
                            adapterPlanta= new MyViveroNayonRecyclerViewAdapter(plantaList,getActivity());
                            recyclerView.setAdapter(adapterPlanta);

                        }
                    });

            lanzarViewModel();
        }
        return view;
    }

    private void lanzarViewModel() {

        nuevaPlantaDialogViewModel = new ViewModelProvider(requireActivity()).get(NuevaPlantaDialogViewModel.class);
        plantaList= nuevaPlantaDialogViewModel.getAllPlanta();
        if(adapterPlanta!= null) {
            adapterPlanta.setNuevasPlantas(plantaList);
        }
    }
}