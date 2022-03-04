package com.example.despesaspessoais.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.despesaspessoais.R;
import com.example.despesaspessoais.dao.DespesaDAO;
import com.example.despesaspessoais.entidade.Despesa;
import com.example.despesaspessoais.view.util.AdapterDespesa;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton btnAdicionar;
    private RecyclerView recyclerView;
    private List<Despesa> listaDespesas;
    private TextView txtMaiorDespesa;
    private TextView txtMenorDespesa;
    private TextView txtMediaDespesa;
    private TextView txtTotalDespesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Tela de Despesas");
        instanciarComponentes();
        carregarDados();
        configurarComponentes();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarDados();
        configurarComponentes();
    }

    public void instanciarComponentes(){
        btnAdicionar = findViewById(R.id.btnAdicionar);
        recyclerView = findViewById(R.id.recyclerView);
        txtMaiorDespesa = findViewById(R.id.maiorDespesa);
        txtMenorDespesa = findViewById(R.id.menorDespesa);
        txtMediaDespesa = findViewById(R.id.mediaDespesa);
        txtTotalDespesa = findViewById(R.id.totalDespesa);
    }

    public void configurarComponentes(){
        configurarBtnAdicionar();
        configurarRecyclerView();
        configurarCardView();
    }

    public void configurarCardView(){
        float maiorValor = 0;
        float menorValor = 0 ;
        float valorTotal = 0;
        float mediaTotal = 0 ;

        if(listaDespesas.size() != 0) {

            menorValor = listaDespesas.get(0).getValor();

            for (int i = 0; i < listaDespesas.size(); i++) {
                if (listaDespesas.get(i).getValor() >= maiorValor) {
                    maiorValor = listaDespesas.get(i).getValor();
                    txtMaiorDespesa.setText("A maior despesa foi em " + listaDespesas.get(i).getNome() + " no valor de R$ " + maiorValor);
                }
                if (listaDespesas.get(i).getValor() <= menorValor) {
                    menorValor = listaDespesas.get(i).getValor();
                    txtMenorDespesa.setText("A menor despesa foi em " + listaDespesas.get(i).getNome() + " no valor de R$ " + menorValor);
                }
                valorTotal += listaDespesas.get(i).getValor();
            }
            mediaTotal = valorTotal / listaDespesas.size();

            txtMediaDespesa.setText("A média de despesas é R$ " + mediaTotal);
            txtTotalDespesa.setText("O valor total de despesas é R$ " + valorTotal);
        }
    }

    public void configurarRecyclerView(){
        AdapterDespesa adapterDespesa = new AdapterDespesa(listaDespesas);
        RecyclerView.LayoutManager recyclerLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerLayout);
        recyclerView.setAdapter(adapterDespesa);
        recyclerView.setHasFixedSize(true);

    }

    public void configurarBtnAdicionar(){
        btnAdicionar.setOnClickListener(this);
    }

    public void carregarDados(){
        listaDespesas = DespesaDAO.getDespesas(this);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(btnAdicionar)){
            Intent intent = new Intent(this, TelaAdicionarDespesa.class);
            startActivity(intent);
        }
    }
}