package ro.pub.cs.systems.eim.practicaltest01var03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var03SecondaryActivity extends AppCompatActivity {
    Button correct;
    Button incorrect;
    TextView operation;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_secondary);

        correct = findViewById(R.id.button_correct);
        incorrect = findViewById(R.id.button_incorrect);
        operation = findViewById(R.id.second_operation);

        Intent intent = getIntent();
        String op = intent.getStringExtra(PracticalTest01Var03MainActivity.EXTRA_OPERATION);
        operation.setText(op);

        correct.setOnClickListener(l -> {
            setResult(RESULT_OK, intent);
            finish();
        });

        incorrect.setOnClickListener(l -> {
            setResult(RESULT_CANCELED, intent);
            finish();
        });
    }
}