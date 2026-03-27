package com.example.calculator_v2;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button simpleButton = findViewById(R.id.button_simple);
        Button advancedButton = findViewById(R.id.button_advanced);
        Button aboutButton = findViewById(R.id.button_about);
        Button exitButton = findViewById(R.id.button_exit);

        String savedInput = getIntent().getStringExtra("inputText");

        simpleButton.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            intent.putExtra("isScientific", false);
            if (savedInput != null) {
                intent.putExtra("inputText", savedInput);
            }
            startActivity(intent);
        });

        advancedButton.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            intent.putExtra("isScientific", true);
            if (savedInput != null) {
                intent.putExtra("inputText", savedInput);
            }
            startActivity(intent);
        });

        aboutButton.setOnClickListener(v -> showAboutDialog());

        exitButton.setOnClickListener(v -> finishAffinity());
    }

    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("About Calculator")
                .setMessage("App: Calculator\nAuthor: Łukasz Zbrzeski 245969\nSimple and advanced calculator app.")
                .setPositiveButton("OK", null)
                .show();
    }
}
