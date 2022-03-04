package com.example.despesaspessoais.view.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.despesaspessoais.R;
import com.example.despesaspessoais.entidade.Despesa;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterDespesa extends RecyclerView.Adapter<AdapterDespesa.MyViewHolder> {

    private List<Despesa> listaDespesas;

    public AdapterDespesa(List<Despesa> listaDespesas){
        this.listaDespesas = listaDespesas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View minhaLista = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.despesa_item, parent, false);

        return new MyViewHolder(minhaLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DateFormat formatador = new SimpleDateFormat("dd/MM/yy");
        Despesa despesa = listaDespesas.get(position);
        holder.txtNome.setText(despesa.getNome());
        holder.txtValor.setText(String.valueOf(despesa.getValor()));
        holder.txtData.setText(formatador.format(despesa.getData()));
    }

    @Override
    public int getItemCount() {
        return listaDespesas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtNome;
        TextView txtValor;
        TextView txtData;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            txtNome = itemView.findViewById(R.id.txtNomeDespesa);
            txtValor = itemView.findViewById(R.id.txtValorDespesa);
            txtData = itemView.findViewById(R.id.txtDataDespesa);
        }
    }

}
