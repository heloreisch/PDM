package com.example.app1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    int  posicao = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Integer[] imagens = new Integer[]{
                R.drawable.perfil,
                R.drawable.abaixopeso};
        //colocar todos
        Button botaoVoltar, botaoProximo;
        botaoVoltar = findViewById(R.id.btnVoltar);
        botaoProximo = findViewById(R.id.btnProximo);
        ImageView imageView = findViewById(R.id.imageView);

        botaoProximo.setOnClickListener( View -> {
            posicao ++;
            imageView.setImageResource(Imagens[posicao]);
        });

    }
}