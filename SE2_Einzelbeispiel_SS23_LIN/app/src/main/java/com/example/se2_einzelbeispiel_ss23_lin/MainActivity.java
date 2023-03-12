package com.example.se2_einzelbeispiel_ss23_lin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button serverBtn;
    Button ex2Btn;

    TextView answerView;
    TextView ex2View;
    EditText mNr;


    String answerText;
    String nullText = "Bitte geben Sie Ihre Matrikelnummer ein!";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serverBtn = (Button) findViewById(R.id.sendToServerButton);
        ex2Btn = (Button) findViewById(R.id.ex2Button);
        answerView = (TextView) findViewById(R.id.answerView);
        ex2View = (TextView) findViewById(R.id.ex2View);
        mNr = (EditText) findViewById(R.id.userInput);



        serverBtn.setOnClickListener(view -> changeMNr());
    }

    public Runnable createThread() {
        Runnable runnable = () -> {
            try {
                String userInput = answerView.getText().toString();
                Socket clientSocket = new Socket("se2-isys.aau.at", 53212);
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outToServer.writeBytes(userInput + "\n");
                answerText = inFromServer.readLine();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        return runnable;
    }

    // Da a = 1... Annahme 0 = j
    public void changeMNr() {
        char[] result = mNr.getText().toString().toCharArray();
        for (int i = 0; i < result.length; i++) {
            if (result[i] != '0' && i % 2 != 0) {
                result[i] = (char) (96 + Character.getNumericValue(result[i]));
            }
            if (result[i] == '0' && i % 2 != 0) {
                result[i] = (char) 'j';
            }
            System.out.println(Arrays.toString(result));

        }
        String output = Arrays.toString(result);
        output = output.replace("[", "");
        output = output.replace("]", "");

        answerView.setText(output);
        //System.out.println(Arrays.toString(result));

    }
}