package com.example.peseltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/********************************************************
 * nazwa klasy MainActivity
 * atrybuty klasy: brak
 * wartość zwracana: brak
 * autor: 56789012345
 * ****************************************************/
public class MainActivity extends AppCompatActivity {

    /********************************************************
     * nazwa funkcji: onCreate
     * parametry wejściowe: savedInstanceState - zapisany stan aplikacji
     * wartość zwracana: brak
     * autor: 56789012345
     * ****************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /********************************************************
     * nazwa funkcji: sprawdz
     * parametry wejściowe: vew - widok, na którym wystąpiło zdarzenie
     * wartość zwracana: brak
     * autor: 56789012345
     * ****************************************************/
    public void sprawdz(View view) {
        String komunikatStr = "";
        final int DLUGOSC_PESEL = 11;
        int[] wagi = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int[] peselCyfry = new int[DLUGOSC_PESEL];

        EditText imie = (EditText) findViewById(R.id.imie);
        String imieStr = imie.getText().toString();
        if(imieStr.length()==0) {
            komunikatStr += "Imię jest wymagane. ";
        }

        EditText nazwisko = (EditText) findViewById(R.id.nazwisko);
        String nazwiskoStr = nazwisko.getText().toString();
        if(nazwiskoStr.length()==0){
            komunikatStr += "Nazwisko jest wymagane. ";
        }

        EditText pesel = (EditText) findViewById(R.id.pesel);
        String peselStr = pesel.getText().toString();
        if(peselStr.length()!=DLUGOSC_PESEL){
            komunikatStr += "Pesel musi zawierać 11 cyfr";
        } else{
            for (int i = 0; i < DLUGOSC_PESEL ; i++) {
                String znak = peselStr.substring(i, i+1);
                int cyfra = Integer.parseInt(znak);
                peselCyfry[i] = cyfra;
            }
            int suma = 0;
            for (int i = 0; i < DLUGOSC_PESEL-1; i++) {
                suma = suma + peselCyfry[i] * wagi[i];
            }
            int wynik = 10 - (suma % 10);
            if(wynik == 10) wynik = 0;
            if(wynik != peselCyfry[10]) {
                komunikatStr += "Pesel ma niepoprawną wartość" ;
            }
        }

        if(komunikatStr.length()==0){
            komunikatStr = "Zweryfikowano użytkownika " + imieStr + " " + nazwiskoStr + " - witamy w systemie";
        }

        TextView komunikat = (TextView) findViewById(R.id.komunikat);
        komunikat.setText(komunikatStr);
    }
}