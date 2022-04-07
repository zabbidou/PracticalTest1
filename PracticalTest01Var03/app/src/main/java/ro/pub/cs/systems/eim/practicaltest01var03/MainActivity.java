package ro.pub.cs.systems.eim.practicaltest01var03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText firstNumber;
    EditText secondNumber;
    TextView operationTextView;
    Button plus;
    Button minus;
    Button next_activity;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNumber = findViewById(R.id.first_number);
        secondNumber = findViewById(R.id.second_number);
        operationTextView = findViewById(R.id.operation);
        plus = findViewById(R.id.button_plus);
        minus = findViewById(R.id.button_minus);
        next_activity = findViewById(R.id.button_activity);

        plus.setOnClickListener(l -> {
            int first, second;
            try {
                first = Integer.parseInt(String.valueOf(firstNumber.getText()));
                second = Integer.parseInt(String.valueOf(secondNumber.getText()));
                String operation = first + " + " + second + " = " + (first + second);
                operationTextView.setText(operation);
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Error: input not a number", Toast.LENGTH_SHORT).show();
            }

        });

        minus.setOnClickListener(l -> {
            int first, second;
            try {
                first = Integer.parseInt(String.valueOf(firstNumber.getText()));
                second = Integer.parseInt(String.valueOf(secondNumber.getText()));
                String operation = first + " - " + second + " = " + (first - second);
                operationTextView.setText(operation);
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Error: input not a number", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("number1_saved", firstNumber.getText().toString());
        savedInstanceState.putString("number2_saved", secondNumber.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String message = "Restored: ";
        if (savedInstanceState.getString("number1_saved") != null) {
            firstNumber.setText(savedInstanceState.getString("number1_saved"));
            message += savedInstanceState.getString("number1_saved") + " ";
        }
        if (savedInstanceState.getString("number2_saved") != null) {
            secondNumber.setText(savedInstanceState.getString("number2_saved"));
            message += savedInstanceState.getString("number2_saved");
        }

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}