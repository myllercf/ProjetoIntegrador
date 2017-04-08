package com.example.casa.projetointegrador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by casa on 01/04/2017.
 */

public class Resultado extends Activity {

    private int[] imagens = {R.drawable.imc_masculino, R.drawable.imc_feminino};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);

        Intent it = getIntent();

        if(it != null){
            Bundle informacoes = it.getExtras();

            if(informacoes != null){
                Float imc = informacoes.getFloat("imc");
                int sexo = informacoes.getInt("sexo");

                NumberFormat formatarFloat = new DecimalFormat("#.##");
                imc = Float.parseFloat(formatarFloat.format(imc).replace(",", "."));

                TextView tela2 = (TextView) findViewById(R.id.calculo);
                tela2.setText(imc.toString());

                final ImageView imgTabela = (ImageView) findViewById(R.id.tabela);
                imgTabela.setImageResource(imagens[sexo-1]);
            }
        }
    }

}
