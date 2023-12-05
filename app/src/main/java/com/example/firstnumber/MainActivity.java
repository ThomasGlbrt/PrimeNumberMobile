package com.example.firstnumber;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputNumber;
    private Button buttonCalc;
    private ListView list;
    private TextView viewResult;
    private TextView viewProgress;
    private TextView viewTime;

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
                    //Envoie la donnée entrée dans l'input
                    new PrimeNumberAsyncTask(MainActivity.this).execute(n);
                }
            }
        });
    }

    public void updateProgress(int number) {
        // Met a jour le nombre testé (pas actualisé tout le temps pour garder des performances)
        //testing_number est un valeur ajouté dans res/values/strings.xml .
        viewProgress.setText(getString(R.string.testing_number, number));
    }

    //Update des champs qui donne les resultats
    public void updateResults(ArrayList<Integer> primeNumbers, long elapsedTime) {
        // Création de l'adapater pour rentrer les valeurs de l'array dans la listView
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, primeNumbers);
        list.setAdapter(adapter);

        //Mise a jour du texte dans les champs tvProgressVal et tvTimeVal qui donne réspectivement :
        // - le nombre total de nombre premier
        // - le temps final de la procédure de calcul a la fin de l'AsyncTask

        viewProgress.setText(String.valueOf(primeNumbers.size()));
        viewTime.setText(String.valueOf(elapsedTime));
    }

}

