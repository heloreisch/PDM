package com.example.app1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int contador =0;



    //repara a tela básica do sistema, e faz o aplicativo ocupar o espaço inteiro do visor
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);
        //classe R = referencia / id = identificador unico do botao
        Button b = findViewById(R.id.button);
        //classe textView  variavel tv = identificador textview (alterado no layout)
        TextView tv = findViewById(R.id.textView);
      // b variavel botao chama a funçao de clica acontece tal coisa

        EditText edmin = findViewById(R.id.edmin);
        EditText edmax = findViewById(R.id.edmax);



        // v {} função anonima
        b.setOnClickListener(view -> {

            int min = Integer.parseInt( edmin.getText().toString());
            int max = Integer.parseInt( edmax.getText().toString());


            Random random = new Random();
            //a b  10  50    (50-10)
            int valor =  random.nextInt( max-min)+min;

            tv.setText(Integer.toString(valor));
            // int p string
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}