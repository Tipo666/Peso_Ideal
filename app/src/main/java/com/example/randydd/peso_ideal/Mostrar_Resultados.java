package com.example.randydd.peso_ideal;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Mostrar_Resultados extends AppCompatActivity implements View.OnClickListener {

    //Declaramos variable para su uso posteriro
    TextView text_Intervalo1;
    TextView text_Intervalo2;
    TextView text_Dianostico;
    TextView text_Unidad;
    TextView text_Complexion;
    TextView text_peso_actual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar__resultados);
        getSupportActionBar().setTitle(R.string.Resultado);
        //Obtenemos los datos enviados desde el MainActivity
        Bundle datos = getIntent().getExtras();

        //Declaramos variables que usaremos luego
        String intervalos;
        String diagnostico;
        String complexion;
        double peso_actual;
        int unidad;
        int inter[] = new int[2];
        String[] inter2 ;


        //Introducimos los datos obtenidos en variables
        intervalos = datos.getString("intervalos");
        diagnostico = datos.getString("diagnostico");
        complexion = datos.getString("complexion");
        unidad = datos.getInt("unidad");
        peso_actual=datos.getDouble("peso");


        //Intaciamos los componentes para su uso
        text_Intervalo1 = (TextView) findViewById(R.id.text_intervalo_menor);
       text_Intervalo2=(TextView) findViewById(R.id.text_intervalo_mayor);
        text_Unidad = (TextView) findViewById(R.id.unidad_mediad);

        text_Complexion=(TextView)findViewById(R.id.mostrar_complexion);
        text_Dianostico=(TextView)findViewById(R.id.text_dianostico);
        text_peso_actual=(TextView)findViewById(R.id.text_peso_actual);

        //Asinamos listener a la informacion  que el usuario vera cuando presiones estos ImageViews
        findViewById(R.id.info_peso_ideal).setOnClickListener(this);
        findViewById(R.id.info_complexion).setOnClickListener(this);
        findViewById(R.id.info_IMC).setOnClickListener(this);

        //Verifica,os la unidad de medida
        if (unidad == 1) {
            //Si la medida seleccionada por el usuario es libra entonces covertimos los intervalos a libras
            inter = Conversion(intervalos);

            //Establecemos los intervalos en los campos de textos
            text_Intervalo1.setText(inter[0]+"");
            text_Intervalo2.setText(inter[1]+"");

            //Redenamos el peso actual
            peso_actual=Math.round(peso_actual);

            //Comparamos si el peso actual esta en el intervalo
            if(peso_actual>=inter[0] && peso_actual<=inter[1]){
                text_peso_actual.setText(R.string.inter_recomendado);
            }else{
                text_peso_actual.setText(R.string.inter_no_recomendado);
            }

             text_Unidad.setText(R.string.lb);

        } else {
            //Si la medida seleccionada es kilogramas

            double [] rango=new double[2];

                   //Dividimos el intervalos menor y el mayor
            inter2 = intervalos.split("-");

            rango[0]=Double.parseDouble(inter2[0]);
            rango[1]=Double.parseDouble(inter2[1]);

            //Lo establecemos  en lo campo de textos
            text_Intervalo1.setText(inter2[0]);
            text_Intervalo2.setText(inter2[1]);
            text_Unidad.setText(R.string.kg);

              //Vericamos si estan en los intervalos correspondiente
            if(peso_actual>=rango[0] &&peso_actual<=rango[1]){
                text_peso_actual.setText(R.string.inter_recomendado);
            }else{

                text_peso_actual.setText(R.string.inter_no_recomendado);

            }
        }

        //Establecemos la complexion
        text_Complexion.setText(complexion);

        //Establecemos el dianostico obtenido por Indice de masa comporral
        text_Dianostico.setText(diagnostico);


    }



    public int[] Conversion(String intervalos) {
      //Convertimos los intervalos a libras
        int inter[] = new int[2];
        double intervalo1;
        double intervalo2;


        //Dividimos lo intervalos
        String[] valores = intervalos.split("-");

        //Parseamos los intervalos a Double
        intervalo1 = Double.parseDouble(valores[0]);
        intervalo2 = Double.parseDouble(valores[1]);

        //Conversion de kilogramos a libras
        intervalo1 *= 2.204623;
        intervalo2 *= 2.204623;

      //Redondeamos las libras al numero mas cercano
        inter[0] = (int) Math.round(intervalo1);
        inter[1] = (int) Math.round(intervalo2);


        return inter;

    }

    @Override
    public void onClick(View v) {

        //Mensaje de informacion y de alerta cuando se despliengan los datos
        switch (v.getId()) {

            case R.id.info_peso_ideal:
                AlertDialog alerta=new AlertDialog.Builder(this)
                        .setTitle(R.string.peso_ideal2)
                        .setMessage(R.string.peso_ideal)
                        .setPositiveButton(android.R.string.yes,new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })

                        .setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }

                        }).create();
                alerta.show();
                break;

            case R.id.info_complexion:
                AlertDialog alerta2=new AlertDialog.Builder(this)
                        .setTitle(R.string.Complexion)
                        .setMessage(R.string.info_complexion)
                        .setPositiveButton(android.R.string.yes,new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })

                        .setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }

                        }).create();
                alerta2.show();

                break;

            case R.id.info_IMC:
                AlertDialog alerta3=new AlertDialog.Builder(this)
                        .setTitle(R.string.IMC)
                        .setMessage(R.string.info_imc)
                        .setPositiveButton(android.R.string.yes,new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })

                        .setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }

                        }).create();
                alerta3.show();

                break;

            default:
                break;
        }

    }
}
