package com.example.firstnumber;

import android.os.AsyncTask;
import android.os.SystemClock;

import java.util.ArrayList;

public class PrimeNumberAsyncTask extends AsyncTask<Integer, Integer, ArrayList<Integer>> {

    private MainActivity activity;
    private long startTime;

    public PrimeNumberAsyncTask(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Défini le temps de départ pour compter le temps d'execution en ms
        startTime = SystemClock.uptimeMillis();

    }

    @Override
    protected ArrayList<Integer> doInBackground(Integer... params) {
        int n = params[0];
        ArrayList<Integer> primeNumbers = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            // si i est un nombre premier on l'ajoute a la liste primeNumbers et on l'ajoute a
            if (isPrime(i)) {
                primeNumbers.add(i);
                // Utilisation de publishProgress pour mettre à jour l'UI sur le thread principal
                publishProgress(i);
            }
        }

        return primeNumbers;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        // Donne la valeur actuelle de l'essaie
        activity.updateProgress(values[0]);
    }


    @Override
    protected void onPostExecute(ArrayList<Integer> result) {
        super.onPostExecute(result);
        long elapsedTime = SystemClock.uptimeMillis() - startTime;
        // Met a jour la mainActivity avec les nombres premier trouvé et le temps que ça a pris.
        activity.updateResults(result, elapsedTime);
    }

    //Fonction qui défini si un nombre est premier ou non
    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}