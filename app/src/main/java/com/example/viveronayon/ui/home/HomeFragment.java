package com.example.viveronayon.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.viveronayon.R;
import com.example.viveronayon.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.LocalModel;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeler;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.custom.CustomImageLabelerOptions;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Bitmap bitMap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.buttonIdentificar.setOnClickListener(v -> identificar());
        takePicture();

        return root;
    }

    private void identificar() {
        InputImage image = InputImage.fromBitmap(bitMap, 0);

        //Ejemplo modelo predeterminado
        // ImageLabeler labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS);

        LocalModel localModel =
                new LocalModel.Builder()
                        .setAssetFilePath("model_flores.tflite")
                        .build();

        CustomImageLabelerOptions customImageLabelerOptions =
                new CustomImageLabelerOptions.Builder(localModel)
                        .setConfidenceThreshold(0.5f)
                        .setMaxResultCount(5)
                        .build();
        ImageLabeler labeler = ImageLabeling.getClient(customImageLabelerOptions);

        //Procesar identificacion
        labeler.process(image)
                .addOnSuccessListener(new OnSuccessListener<List<ImageLabel>>() {
                    @Override
                    public void onSuccess(List<ImageLabel> labels) {
                        if(labels!=null && !labels.isEmpty()){
                            //Pasar los datos encontrados en labels al dashboard
                            Navigation.findNavController(binding.getRoot())
                                    .navigate(R.id.navigation_dashboard);
                        }else{
                            Toast.makeText(getContext(),"No se pudo identificar la imágen",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(),"Existe un problema con la identificación",
                                Toast.LENGTH_SHORT).show();
                    }
                });

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            bitMap = (Bitmap) extras.get("data");
            binding.foto.setImageBitmap(bitMap);
        }
    }

}