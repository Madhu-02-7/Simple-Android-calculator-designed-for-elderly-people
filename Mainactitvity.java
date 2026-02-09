package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;

    double firstNumber = 0;
    double secondNumber = 0;
    String operator = "";
    boolean isNewInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
        tvResult.setText("0");
    }

    public void buttonClick(View view) {

        Button button = (Button) view;
        String value = button.getText().toString();

        Toast.makeText(this, "You pressed: " + value, Toast.LENGTH_SHORT).show();

        new AlertDialog.Builder(this)
                .setTitle("Button Pressed")
                .setMessage("You pressed: " + value)
                .setPositiveButton("OK", null)
                .show();

        if (value.matches("[0-9]")) {
            if (isNewInput || tvResult.getText().toString().equals("0")) {
                tvResult.setText(value);
                isNewInput = false;
            } else {
                tvResult.append(value);
            }
        }

        else if (value.equals("+") || value.equals("-")
                || value.equals("×") || value.equals("÷")) {

            firstNumber = Double.parseDouble(tvResult.getText().toString());
            operator = value;
            isNewInput = true;
        }

        else if (value.equals("=")) {

            secondNumber = Double.parseDouble(tvResult.getText().toString());
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "×":
                    result = firstNumber * secondNumber;
                    break;
                case "÷":
                    if (secondNumber == 0) {
                        tvResult.setText("Error");
                        return;
                    }
                    result = firstNumber / secondNumber;
                    break;
            }

            tvResult.setText(String.valueOf(result));
            isNewInput = true;
        }

        else if (value.equals("C")) {
            tvResult.setText("0");
            firstNumber = 0;
            secondNumber = 0;
            operator = "";
            isNewInput = true;
        }
    }
}
