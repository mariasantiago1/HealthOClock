package com.example.doug.healthoclock.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doug.healthoclock.model.ControleExame;
import com.example.doug.healthoclock.model.Exame;
import com.example.doug.healthoclock.model.Paciente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cida on 08/02/2017.
 */

public class ControleExameDAO {
    private SQLiteDatabase banco;

    public ControleExameDAO(Context context)
    {
        try {
        this.banco = new BancoHelp(context).getWritableDatabase();
        } catch (IOException e) {

        }
    }

    public void insert (ControleExame e)
    {
        ContentValues cv = new ContentValues();
        cv.put("nome_unidade", e.getNomeUnidadeMedica());
        cv.put("endereco_unidade", e.getEnderecoDaUnidade());
        cv.put("nome_paciente", e.getPaciente().getNome());
        cv.put("dados_clinicos", e.getDadosClinicos());
        cv.put("especialidade_medico", e.getEspecialidadeMedicoRequisitante());
        cv.put("material_examinar", e.getMaterialExaminar());
        cv.put("tipo_exame", e.getTipoExame());
        cv.put("data_realizacao", e.getDataRealizacaoString());

        banco.insert("ControleExame", null, cv);
    }

    public List<ControleExame> getControleExames()
    {
        List<ControleExame> exames = new ArrayList<ControleExame>();

        String colunas[] = {"id", "nome_unidade", "endereco_unidade", "nome_paciente",
                "dados_clinicos", "especialidade_medico", "material_examinar","tipo_exame", "data_realizacao"};

        Cursor c = banco.query("ControleExame", colunas, null, null, null, null, "nome_unidade");


        if(c.getCount() > 0)
        {
            c.moveToFirst();
            do{
                int id = c.getInt(c.getColumnIndex("id"));
                String nome_unidade = c.getString(c.getColumnIndex("nome_unidade"));
                String endereco_unidade = c.getString(c.getColumnIndex("endereco_unidade"));
                Paciente paciente = new Paciente(c.getString(c.getColumnIndex("nome_paciente")));
                String dados_clinicos = c.getString(c.getColumnIndex("dados_clinicos"));
                String especialidade = c.getString(c.getColumnIndex("especialidade_medico"));
                String material_examinar = c.getString(c.getColumnIndex("material_examinar"));
                String tipo_exame = c.getString(c.getColumnIndex("tipo_exame"));
                long data_realizacao = c.getLong(c.getColumnIndex("data_realizacao"));

                exames.add(new ControleExame(nome_unidade, endereco_unidade, paciente,
                        dados_clinicos, especialidade, material_examinar, tipo_exame, data_realizacao));
            } while(c.moveToNext());
        }

        return exames;
    }

    public ControleExame getByid(int id)
    {
        Cursor c = banco.rawQuery("SELECT * from ControleExame where id=" + id, null);

        if(c.getCount() <= 0)
            return null;

        String nome_unidade = c.getString(c.getColumnIndex("nome_unidade"));
        String endereco_unidade = c.getString(c.getColumnIndex("endereco_unidade"));
        Paciente paciente = new Paciente(c.getString(c.getColumnIndex("nome_paciente")));
        String dados_clinicos = c.getString(c.getColumnIndex("dados_clinicos"));
        String especialidade = c.getString(c.getColumnIndex("especialidade_medico"));
        String material_examinar = c.getString(c.getColumnIndex("material_examinar"));
        String tipo_exame = c.getString(c.getColumnIndex("tipo_exame"));
        long data_realizacao = c.getLong(c.getColumnIndex("data_realizacao"));

        ControleExame exame = new ControleExame(nome_unidade, endereco_unidade, paciente,
                dados_clinicos, especialidade, material_examinar, tipo_exame, data_realizacao);

        return exame;
    }
}
