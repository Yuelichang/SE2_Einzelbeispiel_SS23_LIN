package com.example.se2_einzelbeispiel_ss23_lin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView answerView;
    EditText mNr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        answerView = (TextView) findViewById(R.id.answerView);
        mNr = (EditText) findViewById(R.id.userInput);


        btn.setOnClickListener(view -> changeMNr());
    }

    public void changeMNr() {
        char[] result = mNr.getText().toString().toCharArray();
        for (int i = 0; i < result.length; i++) {
            if (i % 2 != 0) {
                result[i] = (char) (96 + Character.getNumericValue(result[i]));
            }
        }
        String output = Arrays.toString(result);
        output = output.replace("[", "");
        output = output.replace("]", "");

        answerView.setText(output);
        //System.out.println(Arrays.toString(result));

    }
}