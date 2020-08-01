package com.example.learnandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btMinus, btPlus;
    TextView txCount, txReset;
    SharedPreferences mySharedPref;
    int myCountValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Access Components using IDs
        btMinus = findViewById(R.id.minusButton);
        btPlus = findViewById(R.id.plusButton);
        txCount = findViewById(R.id.totalCount);
        txReset = findViewById(R.id.restLink);

        //Read Saved Count using Shared Preferences
        mySharedPref = getSharedPreferences("mypreferences", MODE_PRIVATE);
        myCountValue = getSavedCount();
        txCount.setText(myCountValue + "");
        Log.d("testlog",myCountValue+" is the count value");


        //Difine Action For Minus Button
        btMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (myCountValue == 0) {
                    Toast.makeText(MainActivity.this, "Value Already Zero", Toast.LENGTH_SHORT).show();
                } else {
                    myCountValue = myCountValue - 1;
                    txCount.setText(myCountValue + "");//A simple hack to convert to string format!
                    saveCount(myCountValue);
                }

            }
        });

        //Difine Action For Minus Button
        btPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCountValue = myCountValue + 1;
                txCount.setText(String.valueOf(myCountValue));//You can also do like this to convert to string
                saveCount(myCountValue);
            }
        });

        //Difine Action for Reset Link
        txReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCountValue = 0;
                txCount.setText(myCountValue + "");
            }
        });

    }

    private void saveCount(int countValue) {
        //Modify Shared Preference
        SharedPreferences.Editor myPrefEditor = mySharedPref.edit();
        myPrefEditor.putString("count", countValue + ""); //Similarly you can add more key value pars if neeeded
        myPrefEditor.apply();
    }


    private int getSavedCount() {
        //Read Shared Preference
        String welcommessage = mySharedPref.getString("count", "0");
        return Integer.parseInt(welcommessage);
    }

}