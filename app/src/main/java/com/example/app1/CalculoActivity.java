package com.example.app1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculoActivity extends AppCompatActivity {


    EditText pesousu;
    EditText alturausu;
    Button btncalcular;
    ImageView imageView;

    double imc = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                });

        // array para ir para o proximo desenho
    Integer[] imagens = new Integer[]{
            R.drawable.perfil,
            R.drawable.abaixopeso,
            R.drawable.normal,
            R.drawable.sobrepeso,
            R.drawable.obesidade1,
            R.drawable.obesidade2,
            R.drawable.obesidade3
    };




    }
}


