package com.example.casa.projetointegrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    Float alt;
    Float massa;
    Float imc;
    boolean masculino;
    boolean feminino;

    EditText altura;
    EditText peso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        altura = (EditText) findViewById(R.id.altura);
        peso = (EditText) findViewById(R.id.peso);

        Button botao = (Button) findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
                masculino = R.id.radioMasculino == radioGroup.getCheckedRadioButtonId();
                feminino = R.id.radioFeminino == radioGroup.getCheckedRadioButtonId();

                if( (masculino && !feminino) || (!masculino && feminino) ){

                    alt = Float.parseFloat(altura.getText().toString());
                    massa = Float.parseFloat(peso.getText().toString());

                    imc = massa / alt*alt;

                    Intent tela2 = new Intent(MainActivity.this, Resultado.class);

                    Bundle resultado = new Bundle();
                    resultado.putFloat("imc", imc);

                    if (masculino){
                        resultado.putString("sexo", "masculino");
                    } else{
                        resultado.putString("sexo", "feminino");
                    }

                    tela2.putExtras(resultado);
                    startActivity(tela2);

                } else{
                    //alerta para escolher o sexo
                }
            }
        });
    }
}
