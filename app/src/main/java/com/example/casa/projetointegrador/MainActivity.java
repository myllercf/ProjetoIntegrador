package com.example.casa.projetointegrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Float alt;
    Float massa;
    Float imc;
    int tipoSexo;

    EditText altura;
    EditText peso;

    private String[] sexos = new String[]{"masculino", "feminino"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        altura = (EditText) findViewById(R.id.altura);
        peso = (EditText) findViewById(R.id.peso);

        Button botao = (Button) findViewById(R.id.button);

        Spinner combobox = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sexos);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item);
        combobox.setAdapter(adaptador);

        combobox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                tipoSexo = position;
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        } );

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alt = Float.parseFloat(altura.getText().toString());
                massa = Float.parseFloat(peso.getText().toString());

                if( alt >= 0 && massa >= 0 ){

                    imc = massa / (alt*alt);

                    Intent tela2 = new Intent(MainActivity.this, Resultado.class);

                    Bundle resultado = new Bundle();
                    resultado.putFloat("imc", imc);

                    resultado.putInt("sexo", tipoSexo);

                    tela2.putExtras(resultado);
                    startActivity(tela2);

                } else{
                    Toast.makeText(MainActivity.this, "É necessario escolher o sexo", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
