package com.example.app1;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database;
    EditText editText;
    Button button;
    ListView listView;
//declara um variavael do sqlite database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=openOrCreateDatabase( "notinha", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS notas" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo VARCHAR," + "conteudo VARCHAR)");


        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.edtext);
        button=findViewById(R.id.button);
        listView=findViewById(R.id.listview);

        button.setOnClickListener( view -> {
            String titulo=editText.getText().toString();
            ContentValues values=new ContentValues();
            values.put("titulo", titulo);
            int id=(int) database.insert("notas", null, values);
            if (id>0){
                Toast.makeText(getApplicationContext(),
                        "nota sala acom sucdesso",
                        Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(getApplicationContext(),
                        "Erro ao salva nota",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void ListarNotas(){
        Cursor c=database.rawQuery( "SELECT * FROM notas LIMIT ?", new String[]{"100"});
    }
}