package com.example.casa.projetointegrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Float alt;
    Float massa;
    Float imc;
    int tipoSexo;

    EditText altura;
    EditText peso;

    private String[] sexos = new String[]{"selecione o sexo", "Masculino", "Feminino"};

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

                if( validaFormulario(altura, peso, tipoSexo) ){
                    
                    alt = Float.parseFloat(altura.getText().toString());
                    massa = Float.parseFloat(peso.getText().toString());

                    imc = massa / (alt*alt);

                    Intent tela2 = new Intent(MainActivity.this, Resultado.class);

                    Bundle resultado = new Bundle();
                    resultado.putFloat("imc", imc);

                    resultado.putInt("sexo", tipoSexo);

                    tela2.putExtras(resultado);
                    startActivity(tela2);

                } else{
                    Toast.makeText(MainActivity.this, "É necessario preencher o formulário", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean validaFormulario(EditText alt, EditText peso, int sexo){
        boolean validado = true;

        if ( TextUtils.isEmpty(alt.getText().toString()) ){
            validado = false;
            alt.setError("preencher altura");
        }
        else if ( TextUtils.isEmpty(peso.getText().toString()) ){
            validado = false;
            peso.setError("preencher preencher");
        }
        else if ( sexo <= 0 ){
            validado = false;
        }

        return validado;
    }
}
