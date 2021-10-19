package com.example.viveronayon.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.viveronayon.R;
import com.example.viveronayon.common.Constantes;
import com.example.viveronayon.entity.PlantaEntity;

import java.util.List;


public class MyViveroNayonRecyclerViewAdapter extends RecyclerView.Adapter<MyViveroNayonRecyclerViewAdapter.ViewHolder> {

    private List<PlantaEntity> mValues;
    private Context ctx;

    public MyViveroNayonRecyclerViewAdapter(List<PlantaEntity> items, Context ctx) {
        mValues = items;
        this.ctx=ctx;

    }


    public void setNuevasNotas(List<PlantaEntity> nuevasPlanta){
        this.mValues=nuevasPlanta;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_vivero_nayon, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        // ingresar el id de planta
        holder.mItem = mValues.get(position);
        holder.textViewNombreCientifico.setText(holder.mItem.getNombreCientifico());
        holder.textViewNombre.setText(holder.mItem.getNombre());
        holder.textViewDescripcion.setText(holder.mItem.getDescripcion());
        holder.textViewCuidados.setText(holder.mItem.getCuidados());
        Glide.with(ctx)
                .load(Constantes.URL_FOTO+holder.mItem.getImagen())
                .into(holder.imageViewFoto);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setNuevasPlantas(List<PlantaEntity> nuevasPlantas){
        this.mValues=nuevasPlantas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewNombreCientifico;
        public final TextView textViewNombre;
        public final TextView textViewDescripcion;
        public final TextView textViewCuidados;
        public final ImageView imageViewFoto;
        public PlantaEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView=view;
            textViewNombreCientifico = view.findViewById(R.id.textViewNombreCientifico);
            textViewNombre = view.findViewById(R.id.textViewNombre);
            textViewDescripcion=view.findViewById(R.id.textViewDescripcion);
            textViewCuidados = view.findViewById(R.id.textViewCuidados);
            imageViewFoto = view.findViewById(R.id.imageViewFoto);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewNombreCientifico.getText() + "'";
        }
    }
}