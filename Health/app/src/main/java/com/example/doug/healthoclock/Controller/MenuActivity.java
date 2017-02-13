package com.example.doug.healthoclock.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.doug.healthoclock.R;


public class MenuActivity extends AppCompatActivity {

    private Button botaoExames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        this.botaoExames = (Button) findViewById(R.id.btnExames);
        this.botaoExames.setOnClickListener(new MenuActivity.Exames());
    }

    private class Exames implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MenuActivity.this, ListaControleExameActivity.class);

            startActivity(i);
        }
    }
}
