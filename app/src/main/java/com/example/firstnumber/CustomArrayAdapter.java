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

    private String formatNumbers() {
        StringBuilder formattedNumbers = new StringBuilder();

        for (int i = 0; i < numbers.size(); i++) {
            if (i > 0) {
                formattedNumbers.append(", "); // Ajoute une virgule entre les nombres
            }
            formattedNumbers.append(String.valueOf(numbers.get(i)));
        }

        return formattedNumbers.toString();
    }

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
