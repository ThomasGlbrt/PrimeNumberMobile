package com.example.firstnumber;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<Integer> {

    private Context context;
    private ArrayList<Integer> numbers;

    public CustomArrayAdapter(Context context, ArrayList<Integer> numbers) {
        super(context, 0, numbers);
        this.context = context;
        this.numbers = numbers;
    }

    // Fonction pour la mise en forme de listView, ajoute une virgule entre les nombres
    private String formatNumbers() {
        StringBuilder formattedNumbers = new StringBuilder();

        for (int i = 0; i < numbers.size(); i++) {
            if (i > 0) {
                formattedNumbers.append(", ");
            }
            formattedNumbers.append(String.valueOf(numbers.get(i)));
        }

        return formattedNumbers.toString();
    }

//      Cette méthode crée ou réutilise une vue pour chaque élément de la liste.
//      Si la vue à réutiliser (convertView) est nulle, elle est créée en utilisant un
//      layout prédéfini (android.R.layout.simple_list_item_1). Ensuite, le texte de
//      la vue est défini en appelant la méthode formatNumbers(). Enfin, la vue est
//      renvoyée pour être affichée à l'écran.
//     
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(formatNumbers());

        return convertView;
    }
}
