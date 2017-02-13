package com.example.doug.healthoclock.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.doug.healthoclock.DAO.ControleExameDAO;
import com.example.doug.healthoclock.R;
import com.example.doug.healthoclock.model.ControleExame;

public class ControleExameActivity extends AppCompatActivity {
    private ControleExameDAO dao;
    private TextView unidade;
    private TextView endereco;
    private TextView paciente;
    private TextView idade;
    private TextView dados;
    private TextView especialidade;
    private TextView material;
    private TextView exame;
    private TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_exame);

        dao = new ControleExameDAO(this);

        Bundle b = getIntent().getExtras();
        int id = b.getInt("exame");

        ControleExame e = dao.getByid(id);

        this.unidade = (TextView) findViewById(R.id.textUnidade);
        this.endereco = (TextView) findViewById(R.id.textEndereco);
        this.paciente = (TextView) findViewById(R.id.textNome);
        this.dados = (TextView) findViewById(R.id.textDados);
        this.especialidade = (TextView) findViewById(R.id.textEspecialidade);
        this.material = (TextView) findViewById(R.id.textMaterial);
        this.exame = (TextView) findViewById(R.id.textExame);
        this.data = (TextView) findViewById(R.id.textData);

        unidade.setText(e.getNomeUnidadeMedica());
        endereco.setText(e.getEnderecoDaUnidade());
        paciente.setText(e.getPaciente().getNome());
        dados.setText(e.getDadosClinicos());
        especialidade.setText(e.getEspecialidadeMedicoRequisitante());
        material.setText(e.getMaterialExaminar());
        exame.setText(e.getTipoExame());
        data.setText(e.getDataInicioString());
    }
}
