package com.example.firstnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText inputNumber;
    private Button buttonCalc;
    private ListView list;
    private TextView viewResult;
    private TextView viewProgress;
    private TextView viewTime;

    private ArrayAdapter<String> adapter;  // Utilisation d'un ArrayAdapter de String

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Déclaration des champs de la vue : activity_main.xml
        inputNumber = findViewById(R.id.etnNumbert);
        buttonCalc = findViewById(R.id.btnCalc);
        list = findViewById(R.id.lvResult);
        viewResult = findViewById(R.id.tvResultVal);
        viewProgress = findViewById(R.id.tvProgressVal);
        viewTime = findViewById(R.id.tvTimeVal);

        // Création du listener sur le bouton btnCalc
        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = inputNumber.getText().toString();
                if (!input.isEmpty()) {
                    int n = Integer.parseInt(input);
                    // Envoie la donnée entrée dans l'input
                    new PrimeNumberAsyncTask(MainActivity.this).execute(n);
                }
            }
        });

        // Création de l'adaptateur pour la ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);
    }

    public void updateProgress(int number) {
        // Met à jour le nombre testé
        viewProgress.setText(getString(R.string.testing_number, number));
    }

    // Mise à jour des champs qui donnent les résultats
    public void updateResults(ArrayList<Integer> primeNumbers, long elapsedTime) {
        // Efface le contenu précédent
        adapter.clear();

        // Ajoute tous les nombres au format d'une seule ligne séparés par des virgules
        StringBuilder formattedNumbers = new StringBuilder();
        for (int i = 0; i < primeNumbers.size(); i++) {
            if (i > 0) {
                formattedNumbers.append(", ");
            }
            formattedNumbers.append(primeNumbers.get(i));
        }

        // Ajoute la ligne complète à l'adaptateur
        adapter.add(formattedNumbers.toString());

        // Mise à jour des autres vues
        viewProgress.setText(String.valueOf(primeNumbers.size()));
        viewTime.setText(String.valueOf(elapsedTime));
    }
}
