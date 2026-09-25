package com.example.app1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int  posicao = 0;

    EditText edpeso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edpeso=findViewById(R.id.pesousu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

            // array para ir para o proximo desenho
        });
        Integer[] imagens = new Integer[]{
                R.drawable.perfil,
                R.drawable.abaixopeso,
                R.drawable.normal,
                R.drawable.sobrepeso,
                R.drawable.obesidade1,
                R.drawable.obesidade2,
                R.drawable.obesidade3
        };
        //colocar todos
        Button botaoVoltar, botaoProximo;
        botaoVoltar = findViewById(R.id.btnVoltar);
        botaoProximo = findViewById(R.id.btnProximo);
        ImageView imageView = findViewById(R.id.imageView);

        botaoProximo.setOnClickListener( View -> {
            posicao ++;
            imageView.setImageResource(imagens[posicao]);
        });

        botaoVoltar.setOnClickListener( View ->  {

                imageView.setImageResource(imagens[posicao]);
        posicao--;
        if(posicao < 0) posicao = imagens.length -1;
    });
    }
}