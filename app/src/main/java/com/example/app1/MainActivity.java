package com.example.app1;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
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
    SQLiteDatabase database;
    EditText editText;
    Button button;
    ListView listView;
//declara um variavael do sqlite database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // public void setup()
        //crio banco
        database=openOrCreateDatabase( "notinha", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS notas" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo VARCHAR," + "conteudo VARCHAR)");

        // chama pela id
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.edtext);
        button=findViewById(R.id.button);
        listView=findViewById(R.id.listview);


        button.setOnClickListener( view -> {
            String titulo=editText.getText().toString();
            // pra ir add valor por linha a linha
            ContentValues values=new ContentValues();
            // esse dado vai preencher a colulas titlo da tabela
            values.put("titulo", titulo);
            // inserir na tabela
            int id=(int) database.insert("notas", null, values);
            //
            if (id>0){
                Toast.makeText(getApplicationContext(),
                        "nota salva acom sucesso",
                        Toast.LENGTH_SHORT).show();
                editText.setText("");
                editText.requestFocus();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Erro ao salva nota",
                        Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();
            ListarNotas();
        });

    }
    //
    public void ListarNotas(){
        // select da tabela notas que tem limite de 100 notas
        //raw query pra rodar o mysql
        Cursor c= database.rawQuery( "SELECT * FROM notas LIMIT ?", new String[]{"100"});
        c.moveToFirst();
        ArrayList <String> notas = new ArrayList<>();
        while (c.isAfterLast()){
            c.getString((int)c.getColumnIndex( "titulo"));
            // dando erro  pq nao ta achando o s do titulo a string
            notas.add(s);
            c.moveToNext();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                // se coloca andoird.r pq ele nao esta so no meju pacite
                getApplicationContext(), android.R.layout.simple_list_item_1,
                android.R.id.text1,
                notas

        );
        listView.setAdapter(adapter);
    }
}