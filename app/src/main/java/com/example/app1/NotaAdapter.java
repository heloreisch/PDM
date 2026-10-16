package com.example.app1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NotaAdapter extends ArrayAdapter<Nota> {

    private final Context context;

    public NotaAdapter(Context context, List<Nota> notas) {
        super(context, 0, notas);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.itens, parent, false);
        }

        Nota nota = getItem(position);

        TextView tvTitulo = convertView.findViewById(R.id.textViewTitulo);
        TextView tvTexto = convertView.findViewById(R.id.textViewTexto);

        if (nota != null) {
            tvTitulo.setText(nota.getTitulo());
            tvTexto.setText(nota.getTexto());
        }

        return convertView;
    }
}
