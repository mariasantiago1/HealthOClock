package com.example.doug.healthoclock.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Doug on 07/02/2017.
 */

public class BancoHelp extends SQLiteOpenHelper{
    private static String DB_NAME = "health_oclock";
    private static String FILE_SCHEMA = "schema_health_oclock.txt";
    private static String FILE_DADOS = "dados_health_oclock.txt";
    private String script_schema;
    private String script_dados;

    public BancoHelp(Context context) throws IOException {
        super(context,DB_NAME,null,10);
        this.script_schema = lerScript(context,FILE_SCHEMA);
        this.script_dados = lerScript(context,FILE_DADOS);
    }

    @Override
    public void onCreate(SQLiteDatabase banco) {
        //banco.execSQL(script_schema);
        //banco.execSQL(script_dados);
        banco.execSQL(
            "CREATE TABLE IF NOT EXISTS ControleExame (" +
                "`id`	INTEGER PRIMARY KEY AUTOINCREMENT," +
                "`nome_unidade`	    VARCHAR(100) NOT NULL," +
                "`endereco_unidade`	VARCHAR(100) NOT NULL," +
                "`nome_paciente`	    VARCHAR(100) NOT NULL," +
                "`dados_clinicos`	VARCHAR(100) NOT NULL," +
                "`especialidade_medico`	VARCHAR(100) NOT NULL," +
                "`material_examinar`	VARCHAR(100) NOT NULL," +
                "`tipo_exame`	    VARCHAR(100) NOT NULL," +
                "`data_realizacao`	VARCHAR(16) NOT NULL" +
            ");"
        );
    }

    private String lerScript(Context context, String nome_arquivo) throws IOException {
        String script = "";
        try{
             script = getText(context.getAssets().open(nome_arquivo));

        }catch (IOException e){

        }
        return script;
    }

    private String getText(InputStream inputStream){
        StringBuilder stringBuilder = new StringBuilder();

        try{
            if (inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = null;
                while ((line = bufferedReader.readLine())!= null){
                    stringBuilder.append(line+"\n");
                }
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
