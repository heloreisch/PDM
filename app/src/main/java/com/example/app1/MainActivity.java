package com.example.app1;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    String nomes[] = new String[]{"heloisa", "duda", "julia", "joana", "kiara", "romulo"};
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

         ArrayAdapter<String> usb = new ArrayAdapter<>(getApplicationContext(),R.layout.itens, R.id.textView, nomes);
         lista.setAdapter(usb);
         lista.setOnItemClickListener((parent, view, position, id) -> {
             Toast.makeText(getApplicationContext(), nomes[position],Toast.LENGTH_LONG).show();
         });
    }
}