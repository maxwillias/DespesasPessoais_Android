package com.example.despesaspessoais.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.despesaspessoais.R;
import com.example.despesaspessoais.dao.DespesaDAO;
import com.example.despesaspessoais.entidade.Despesa;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TelaAdicionarDespesa extends AppCompatActivity implements View.OnClickListener {

    private Button btnSalvar;
    private Button btnVoltar;
    private Button btnSelecionarData;
    private TextView txtData;
    private TextView txtNome;
    private TextView txtValor;
    private Despesa despesa = new Despesa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adicionar_despesa);
        this.setTitle("Adicionar Despesa");
        instanciarComponentes();
        configurarComponentes();
    }

    private void instanciarComponentes(){
        btnSalvar = findViewById(R.id.btnSalvar);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnSelecionarData = findViewById(R.id.btnSelecionarData);
        txtData = findViewById(R.id.txtData);
        txtNome = findViewById(R.id.txtNome);
        txtValor = findViewById(R.id.txtValor);
    }

    private void configurarComponentes(){
        btnSalvar.setOnClickListener(this);
        btnVoltar.setOnClickListener(this);
        btnSelecionarData.setOnClickListener(this);
    }

    private void configurarDatePicker(){
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Selecione uma data");
        MaterialDatePicker materialDatePicker = builder.build();
        materialDatePicker.show(getSupportFragmentManager(), "teste");
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
                txtData.setText("Data Selecionada: " + formatador.format(selection));
                Date data = new Date((long) selection);
                despesa.setData(data);
            }
        });
    }

    private void salvarDados(){
        despesa.setNome(txtNome.getText().toString());
        despesa.setValor(Float.parseFloat(txtValor.getText().toString()));
        try {
            DespesaDAO.inserir(getApplicationContext(), despesa);
            Toast.makeText(this, "Despesa Salva com Sucesso",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean dadosValidos(){
        boolean dadosValidos = true;

        if(TextUtils.isEmpty(txtNome.getText().toString())){
            txtNome.setError("Insira o nome da Despesa!");
            txtNome.requestFocus();
            dadosValidos = false;
        }
        if(TextUtils.isEmpty(txtValor.getText().toString())){
            txtValor.setError("Insira o valor da Despesa!");
            txtValor.requestFocus();
            dadosValidos = false;
        }
//        if(TextUtils.isEmpty(txtData.getText().toString())){
//            txtData.setError("Insira uma data valida!");
//            dadosValidos = false;
//        }
        return dadosValidos;
    }

    @Override
    public void onClick(View view) {
        if(view.equals(btnSalvar)){
            if(dadosValidos()) {
                salvarDados();
                this.finish();
            }else {
                Toast.makeText(getApplicationContext(), "Preencha os Dados Corretamente!", Toast.LENGTH_SHORT).show();
            }
        }else if(view.equals(btnVoltar)) {
            this.finish();
        }else if(view.equals(btnSelecionarData)){
            configurarDatePicker();
        }
    }
}