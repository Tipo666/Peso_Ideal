package com.example.randydd.peso_ideal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class acerca_de extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
        getSupportActionBar().setTitle(R.string.acerca_de);
    }
}
