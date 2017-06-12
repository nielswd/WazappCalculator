package nielswd.wazappcalculator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CalculatorDisplay extends AppCompatActivity {
    private TextView        displayEditText;
    private GridLayout      numbersGrid;
    private GridLayout      opGrid;
    private TextView        cleanButton;
    private TextView        calculateButton;

    private String[]        numbersList = new String[] {"7","8","9","4","5","6","1","2","3",".","0"};
    private String[]        opList = new String[] {"/","*","-","+"};

    private float           firstNumber;
    private float           secondNumber;
    private String          operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_display);

        initComponents();
        addNumbers();
        addOperations();
    }


    private void addOperations() {
        for (final String element : opList){
            Button button = (Button)getLayoutInflater().inflate(R.layout.simple_button, null);
            button.setText(element);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String newText = displayEditText.getText() + " " + element + " ";
                    displayEditText.setText(newText);
                    operation = element;
                }
            });
            opGrid.addView(button);
        }
    }

    private void addNumbers() {
        for (final String element : numbersList){
                Button button = (Button)getLayoutInflater().inflate(R.layout.simple_button, null);
                button.setText(element);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String newText = displayEditText.getText() + element;
                        displayEditText.setText(newText);
                    }
                });
                numbersGrid.addView(button);
        }
    }

    private void calculate() {
        String elements = displayEditText.getText().toString();
        String[] seperateElements = elements.split(" ");
        if (seperateElements.length != 3){
            if (seperateElements.length == 1){
                displayEditText.setText(seperateElements[0]);
            } else {
                cleanDisplay();
                displayEditText.setText("Err.");
            }
        } else {
            firstNumber = Float.valueOf(seperateElements[0]);
            operation = seperateElements[1];
            secondNumber = Float.valueOf(seperateElements[2]);
            makeCalcul();
        }
    }

    private void makeCalcul() {
        switch (operation){
            case "/": divOp();
                break;
            case "*": multOp();
                break;
            case "-":minusOp();
                break;
            case "+":addOp();
                break;
        }
    }

    private void divOp() {
        if (secondNumber == 0){
            cleanDisplay();
            displayEditText.setText("Err. Div By 0");
        } else {
            Float resDiv = firstNumber / secondNumber;
            displayEditText.setText(Float.toString(resDiv));
        }
    }

    private void multOp() {
        Float resDiv = firstNumber * secondNumber;
        displayEditText.setText(Float.toString(resDiv));
    }

    private void minusOp() {
        Float resDiv = firstNumber - secondNumber;
        displayEditText.setText(Float.toString(resDiv));
    }

    private void addOp() {
        Float resDiv = firstNumber + secondNumber;
        displayEditText.setText(Float.toString(resDiv));
    }

    private void cleanDisplay(){
        displayEditText.setText("");
    }
    private void initComponents() {
        displayEditText = (TextView) findViewById(R.id.displayEditText);
        numbersGrid     = (GridLayout) findViewById(R.id.numbersGrid);
        opGrid          = (GridLayout) findViewById(R.id.opGrid);
        cleanButton     = (TextView)   findViewById(R.id.cleanButton);
        calculateButton     = (TextView)   findViewById(R.id.calculateButton);
        cleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cleanDisplay();
            }
        });
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calculator_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
