package com.example.calculator_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.DisplayMetrics;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView TVResult;
    LinearLayout scientificButtonsLayout1;
    LinearLayout scientificButtonsLayout2;
    MaterialButton buttonC, buttonBracketOpen, buttonBracketClose, buttonDivide,
            button7, button8, button9, buttonMultiply,
            button4, button5, button6, buttonPlus,
            button1, button2, button3, buttonMinus,
            buttonAC, button0, buttonDot, buttonEquals, buttonPlusMinus;
    MaterialButton buttonSin, buttonCos, buttonTan, buttonExp, buttonLn, buttonLog, buttonSqrt, buttonExp2, buttonPercent;

    boolean isScientific;
    boolean lastWasEquals = false;
    boolean justClearedEntry = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TVResult = findViewById(R.id.text_view_result);
        scientificButtonsLayout1 = findViewById(R.id.scientific_buttons_layout1);
        scientificButtonsLayout2 = findViewById(R.id.scientific_buttons_layout2);

        isScientific = getIntent().getBooleanExtra("isScientific", false);
        toggleScientificButtons(isScientific ? View.VISIBLE : View.GONE);

        assignId(buttonC, R.id.button_c);
        assignId(buttonBracketOpen, R.id.button_open_bracket);
        assignId(buttonBracketClose, R.id.button_close_bracket);
        assignId(buttonDivide, R.id.button_divide);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(buttonMultiply, R.id.button_multiply);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(buttonPlus, R.id.button_plus);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(buttonMinus, R.id.button_minus);
        assignId(buttonAC, R.id.button_ac);
        assignId(button0, R.id.button_0);
        assignId(buttonDot, R.id.button_dot);
        assignId(buttonEquals, R.id.button_equals);
        assignId(buttonPlusMinus, R.id.button_plus_minus);

        assignId(buttonSin, R.id.button_sin);
        assignId(buttonCos, R.id.button_cos);
        assignId(buttonTan, R.id.button_tan);
        assignId(buttonExp, R.id.button_exp);
        assignId(buttonLn, R.id.button_ln);
        assignId(buttonLog, R.id.button_log);
        assignId(buttonSqrt, R.id.button_sqrt);
        assignId(buttonExp2, R.id.button_exp2);
        assignId(buttonPercent, R.id.button_percent);

        this.getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(MainActivity.this, StartActivity.class);
                intent.putExtra("inputText", TVResult.getText().toString());
                startActivity(intent);
                finish();
            }
        });

        String restoredInput = getIntent().getStringExtra("inputText");
        if (restoredInput != null && !restoredInput.isEmpty()) {
            TVResult.setText(restoredInput);
        }
    }

    void assignId(MaterialButton button, int id) {
        button = findViewById(id);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String currentText = TVResult.getText().toString();

        if (buttonText.equals("AC")) {
            TVResult.setText("0");
            lastWasEquals = false;
            justClearedEntry = false;
            return;
        }


        if (buttonText.equals("=")) {
            try {
                String result = getResult(currentText);
                TVResult.setText(result);
                lastWasEquals = true;
            } catch (Exception e) {
                TVResult.setText("Error");
            }
            return;
        }

        if (buttonText.equals("C")) {
            if (justClearedEntry) {
                TVResult.setText("0");
                justClearedEntry = false;
                lastWasEquals = false;
            } else {
                if (!currentText.isEmpty() && !currentText.equals("0")) {
                    int i = currentText.length() - 1;

                    while (i >= 0 && (Character.isDigit(currentText.charAt(i)) || currentText.charAt(i) == '.' || currentText.charAt(i) == ')' || currentText.charAt(i) == '(' || currentText.charAt(i) == '-' || currentText.charAt(i) == '%')) {
                        i--;
                    }

                    currentText = currentText.substring(0, i + 1);
                    if (currentText.isEmpty()) {
                        currentText = "0";
                    }

                    TVResult.setText(currentText);
                }
                justClearedEntry = true;
            }
            return;
        }


        if (buttonText.equals("Sin") || buttonText.equals("Cos") || buttonText.equals("Tan") ||
                buttonText.equals("Ln") || buttonText.equals("Log") || buttonText.equals("Sqrt")) {
            if (lastWasEquals || currentText.equals("0")) {
                currentText = "";
                lastWasEquals = false;
            }
            currentText += buttonText + "(";
            TVResult.setText(currentText);
            return;
        }

        if (buttonText.equals("%") || buttonText.equals("x^y")) {
            if (currentText.isEmpty()) return;

            char lastChar = currentText.charAt(currentText.length() - 1);
            if (isOperator(String.valueOf(lastChar)) || lastChar == '^' || lastChar == '%') {
                currentText = currentText.substring(0, currentText.length() - 1);
            }

            if (buttonText.equals("%")) {
                currentText += "%";
            } else if (buttonText.equals("x^y")) {
                currentText += "^";
            }

            lastWasEquals = false;
            TVResult.setText(currentText);
            return;
        }

        if (buttonText.equals("x^2")) {
            if (lastWasEquals || currentText.equals("0")) {
                currentText = "";
                lastWasEquals = false;
            }
            currentText += "sqr(";
            TVResult.setText(currentText);
            return;
        }

        if (buttonText.equals("+/-")) {
            if (!currentText.isEmpty()) {
                int i = currentText.length() - 1;
                while (i >= 0 && (Character.isDigit(currentText.charAt(i)) || currentText.charAt(i) == '.')) {
                    i--;
                }

                if (i >= 0 && currentText.charAt(i) == '-') {
                    currentText = currentText.substring(0, i) + currentText.substring(i + 1);
                } else {
                    String lastNumber = currentText.substring(i + 1);
                    currentText = currentText.substring(0, i + 1) + "(-" + lastNumber + ")";
                }

                TVResult.setText(currentText);
            }
            return;
        }

        if (lastWasEquals && !isOperator(buttonText)) {
            currentText = buttonText;
        } else if (isOperator(buttonText)) {
            if (!currentText.isEmpty()) {
                char lastChar = currentText.charAt(currentText.length() - 1);
                if (isOperator(String.valueOf(lastChar)) || lastChar == '^' || lastChar == '%') {
                    currentText = currentText.substring(0, currentText.length() - 1) + buttonText;
                } else {
                    currentText += buttonText;
                }
            }
            lastWasEquals = false;
            TVResult.setText(currentText);
            return;
        }
        else {
            if (buttonText.equals(".")) {
                if (currentText.endsWith(".")) return;

                int i = currentText.length() - 1;
                while (i >= 0 && (Character.isDigit(currentText.charAt(i)) || currentText.charAt(i) == '.')) {
                    if (currentText.charAt(i) == '.') return;
                    i--;
                }
            }

            if (currentText.equals("0") && !buttonText.equals(".")) {
                currentText = buttonText;
            } else {
                currentText += buttonText;
            }

        }

        lastWasEquals = false;
        TVResult.setText(currentText);


        lastWasEquals = false;
        TVResult.setText(currentText);
    }


    private boolean isOperator(String input) {
        return input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/");
    }

    String getResult(String data) {
        if (data.isEmpty()) return "0";

        try {
            data = data.replaceAll("(\\d+(\\.\\d+)?)%", "($1/100)");

            double result = new ExpressionBuilder(data)
                    .function(new net.objecthunter.exp4j.function.Function("sqr", 1) {
                        @Override
                        public double apply(double... args) {
                            return args[0] * args[0];
                        }
                    })
                    .functions(TrigonometricFunction.values())
                    .build()
                    .evaluate();

            if (Double.isNaN(result)) {
                runOnUiThread(() ->
                        Toast.makeText(this, "Błąd: wynik nie jest liczbą lub nie istnieje", Toast.LENGTH_SHORT).show()
                );
                return "Error";
            }
            if (Double.isInfinite(result)) {
                runOnUiThread(() ->
                        Toast.makeText(this, "Błąd: nieskończony wynik", Toast.LENGTH_SHORT).show()
                );
                return "Error";
            }

            return formatResult(result);
        } catch (ArithmeticException ae) {
            runOnUiThread(() ->
                    Toast.makeText(this, "Błąd arytmetyczny: " + ae.getMessage(), Toast.LENGTH_SHORT).show()
            );
            return "Error";
        } catch (Exception e) {
            runOnUiThread(() ->
                    Toast.makeText(this, "Nieprawidłowe działanie", Toast.LENGTH_SHORT).show()
            );
            return "Error";
        }
    }

    String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.format("%.4f", result);
        }
    }

    private void toggleScientificButtons(int visibility) {
        scientificButtonsLayout1.setVisibility(visibility);
        scientificButtonsLayout2.setVisibility(visibility);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("inputText", TVResult.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String restoredText = savedInstanceState.getString("inputText", "0");
        TVResult.setText(restoredText);
    }
}