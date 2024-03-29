package ro.pub.cs.systems.eim.practicaltest01var03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PracticalTest01Var03MainActivity extends AppCompatActivity {
    EditText firstNumber;
    EditText secondNumber;
    TextView operationTextView;
    Button plus;
    Button minus;
    Button next_activity;
    int actionIndex = 0;
    boolean serviceStarted = false;
    private IntentFilter intentFilter = new IntentFilter();
    BroadcastReceiver broadcastReceiver = new PracticalTest01Var03BroadcastReceiver();

    public static final String[] actionTypes = new String[]{"ACTION1", "ACTION2"};
    public static final String EXTRA_OPERATION = "ro.pub.cs.systems.eim.practicaltest01var03.OPERATION_STRING";
    public static final String EXTRA_NUMBER1 = "ro.pub.cs.systems.eim.practicaltest01var03.NUMBER1";
    public static final String EXTRA_NUMBER2 = "ro.pub.cs.systems.eim.practicaltest01var03.NUMBER2";
    public static final String EXTRA_BROADCAST = "ro.pub.cs.systems.eim.practicaltest01var03.BROADCAST";
    public static final String BROADCAST_RECEIVER_TAG = "[BRECEIVER]";
    public static final int SECOND_ACTIVITY_REQUEST_CODE = 1236969;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_main);

        firstNumber = findViewById(R.id.first_number);
        secondNumber = findViewById(R.id.second_number);
        operationTextView = findViewById(R.id.operation);
        plus = findViewById(R.id.button_plus);
        minus = findViewById(R.id.button_minus);
        next_activity = findViewById(R.id.button_activity);

        intentFilter.addAction(actionTypes[0]);

        plus.setOnClickListener(l -> {
            int first, second;
            try {
                first = Integer.parseInt(String.valueOf(firstNumber.getText()));
                second = Integer.parseInt(String.valueOf(secondNumber.getText()));
                String operation = first + " + " + second + " = " + (first + second);
                operationTextView.setText(operation);
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var03Service.class);
                intent.putExtra(EXTRA_NUMBER1, first);
                intent.putExtra(EXTRA_NUMBER2, second);
                getApplicationContext().startService(intent);
                Log.d("[MainActivity]", "Service started");
                serviceStarted = true;
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
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var03Service.class);
                intent.putExtra(EXTRA_NUMBER1, first);
                intent.putExtra(EXTRA_NUMBER2, second);
                getApplicationContext().startService(intent);
                Log.d("[MainActivity]", "Service started");
                serviceStarted = true;
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Error: input not a number", Toast.LENGTH_SHORT).show();
            }
        });

        next_activity.setOnClickListener(l -> {
            Intent intent = new Intent(this, PracticalTest01Var03SecondaryActivity.class);
            String operation = (String) operationTextView.getText();
            if (operation.length() == 0) {
                Toast.makeText(getApplicationContext(), "Error: no operation made", Toast.LENGTH_SHORT).show();
            }
            intent.putExtra(EXTRA_OPERATION, operation);
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Result: Correct", Toast.LENGTH_SHORT).show();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast toast = Toast.makeText(getApplicationContext(), "Result: Incorrect", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(broadcastReceiver);
        if (serviceStarted) {
            Intent intent = new Intent(this, PracticalTest01Var03Service.class);
            stopService(intent);
        }
        super.onPause();
    }
}