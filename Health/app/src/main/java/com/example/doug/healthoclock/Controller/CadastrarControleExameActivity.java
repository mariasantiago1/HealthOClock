package com.example.doug.healthoclock.Controller;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.doug.healthoclock.DAO.ControleExameDAO;
import com.example.doug.healthoclock.R;
import com.example.doug.healthoclock.model.ControleExame;
import com.example.doug.healthoclock.model.Paciente;

public class CadastrarControleExameActivity extends AppCompatActivity {
    private ControleExameDAO dao;
    private EditText unidade;
    private EditText endereco;
    private EditText paciente;
    private EditText idade;
    private EditText dados;
    private EditText especialidade;
    private EditText material;
    private EditText exame;
    private DatePicker data;
    private FloatingActionButton botaoCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar_controle_exame);

        this.dao = new ControleExameDAO(this);

        this.unidade = (EditText) findViewById(R.id.inputUnidade);
        this.endereco = (EditText) findViewById(R.id.inputEndereco);;
        this.paciente = (EditText) findViewById(R.id.inputNome);
        this.dados = (EditText) findViewById(R.id.inputDados);
        this.especialidade = (EditText) findViewById(R.id.inputEspecialidade);
        this.material = (EditText) findViewById(R.id.inputMaterial);
        this.exame = (EditText) findViewById(R.id.inputExame);
        this.data = (DatePicker) findViewById(R.id.inputData);
        this.data.setCalendarViewShown(true);

        this.botaoCadastro = (FloatingActionButton) findViewById(R.id.buttonCadastrar);
        this.botaoCadastro.setOnClickListener(new cadastrar());
    }

    private class cadastrar implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            String unidade = CadastrarControleExameActivity.this.unidade.getText().toString();
            String endereco = CadastrarControleExameActivity.this.endereco.getText().toString();
            Paciente paciente = new Paciente(CadastrarControleExameActivity.this.paciente.getText().toString());
            String dados = CadastrarControleExameActivity.this.dados.getText().toString();
            String especialidade = CadastrarControleExameActivity.this.especialidade.getText().toString();
            String material = CadastrarControleExameActivity.this.material.getText().toString();
            String exame = CadastrarControleExameActivity.this.exame.getText().toString();
            int dia = CadastrarControleExameActivity.this.data.getDayOfMonth();
            int mes = CadastrarControleExameActivity.this.data.getMonth();
            int ano = CadastrarControleExameActivity.this.data.getYear();

            ControleExame e = new ControleExame(unidade, endereco, paciente,  dados, especialidade,
                    material, exame, dia, mes, ano);

            dao.insert(e);

            window();
            //Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
            //Bundle b = new Bundle();
            //b.putInt("key", 1); //Your id
            //intent.putExtras(b); //Put your id to your next Intent
        }
    }

    private void window()
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        AlertDialog.Builder builder = new AlertDialog.Builder(CadastrarControleExameActivity.this);
        builder.setTitle("Cadastro realizado");
        //builder.setMessage();
        builder.setIcon(R.drawable.healthoclock_icone_azul_42x42);
        builder.setPositiveButton("Ver Exames", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                }
        );

        builder.setNegativeButton("Novo exame", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        unidade.setText("");
                        endereco.setText("");
                        paciente.setText("");
                        dados.setText("");
                        especialidade.setText("");
                        material.setText("");
                        exame.setText("");

                        unidade.requestFocus();
                    }
                }
        );

        builder.create().show();
    }
}
