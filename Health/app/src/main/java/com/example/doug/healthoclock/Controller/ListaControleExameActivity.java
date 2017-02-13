package com.example.doug.healthoclock.Controller;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.doug.healthoclock.Controller.ExameAdapter;
import com.example.doug.healthoclock.DAO.ControleExameDAO;
import com.example.doug.healthoclock.R;
import com.example.doug.healthoclock.model.ControleExame;

import java.util.List;

public class ListaControleExameActivity extends AppCompatActivity {
    private List<ControleExame> exames;
    private ListView listView;
    private ControleExameDAO dao;
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_controle_exame);

        dao = new ControleExameDAO(this);
        exames = dao.getControleExames();

        this.listView = (ListView) findViewById(R.id.listView);
        ExameAdapter adapter = new ExameAdapter(this, exames);
        this.listView.setAdapter(adapter);

        btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new ListaControleExameActivity.AddExame());
    }

    @Override
    public void onResume() {
        super.onResume();

        exames = dao.getControleExames();
        ExameAdapter adapter = new ExameAdapter(this, exames);
        listView.setAdapter(adapter);
    }

    private class AddExame implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ListaControleExameActivity.this, CadastrarControleExameActivity.class);
            startActivity(i);
        }
    }
}
