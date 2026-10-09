package com.example.app1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText nome;
    ArrayList<String> nomes;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
         ListView lista =  findViewById(R.id.ListView);
         EditText nome = findViewById(R.id.editNome);
        Button salvar = findViewById(R.id.salvar);



         ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, R.layout.itens, R.id.textView, nomes);
         salvar.setOnClickListener(v -> {
             String nomes = nome.getText().toString().trim();
             if (!nomes.isEmpty()) {
                 nomes.add(nome);
                 adaptador.notifyDataSetChanged();
                 nome.setText("");

                 Toast.makeText(this, "Nome salvo!", Toast.LENGTH_SHORT).show();

             } else {
                 Toast.makeText(this, "Digite um nome!", Toast.LENGTH_SHORT).show();
             }
         });


         lista.setAdapter(adaptador);
         lista.setOnItemClickListener((parent, view, position, id) -> {
             Toast.makeText(this, nomes.get(position), Toast.LENGTH_LONG).show();
         });
    }
}