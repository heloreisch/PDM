package com.example.app1;

import android.os.Bundle;
import android.widget.Button;
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

    private EditText editTitulo;
    private EditText editTexto;
    private ListView listView;
    private Button btnSalvar;

    private DatabaseHelper dbHelper;
    private ArrayList<Nota> listaNotas;
    private NotaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );
                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );
                    return insets;
                }
        );

        editTitulo = findViewById(R.id.editTitulo);
        editTexto = findViewById(R.id.editTexto);
        btnSalvar = findViewById(R.id.salvar);
        listView = findViewById(R.id.ListView);

        dbHelper = new DatabaseHelper(this);

        carregarNotas();

        btnSalvar.setOnClickListener(v -> {
            String titulo = editTitulo.getText().toString().trim();
            String texto = editTexto.getText().toString().trim();

            if (!titulo.isEmpty() || !texto.isEmpty()) {
                long result = dbHelper.inserirNota(titulo, texto);
                if (result != -1) {
                    Toast.makeText(this, "Nota salva com sucesso!", Toast.LENGTH_SHORT).show();
                    editTitulo.setText("");
                    editTexto.setText("");
                    carregarNotas();
                } else {
                    Toast.makeText(this, "Erro ao salvar nota!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Digite um título ou texto!", Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Nota nota = listaNotas.get(position);
            Toast.makeText(this, "Nota: " + nota.getTitulo(), Toast.LENGTH_SHORT).show();
        });
    }

    private void carregarNotas() {
        listaNotas = dbHelper.listarNotas();
        adapter = new NotaAdapter(this, listaNotas);
        listView.setAdapter(adapter);
    }
}
