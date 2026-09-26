package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etInputText;
    private Button btnGenerateTest;
    private Button btnCalculate;
    private TextView tvCurrentMetrics;
    private TextView tvHistory;

    private TextMetricsCalculator calculator;
    private List<String> historyList;
    private int sampleIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInputText = findViewById(R.id.etInputText);
        btnGenerateTest = findViewById(R.id.btnGenerateTest);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvCurrentMetrics = findViewById(R.id.tvCurrentMetrics);
        tvHistory = findViewById(R.id.tvHistory);

        calculator = new TextMetricsCalculator();
        historyList = new ArrayList<>();

        btnGenerateTest.setOnClickListener(v -> onGenerateTestClicked());
        btnCalculate.setOnClickListener(v -> onCalculateClicked());
    }

    private void onGenerateTestClicked() {
        String[] samples = getResources().getStringArray(R.array.test_sentences);
        if (samples.length > 0) {
            etInputText.setText(samples[sampleIndex]);
            etInputText.setError(null);
            sampleIndex = (sampleIndex + 1) % samples.length;
        }
    }

    private void onCalculateClicked() {
        String input = etInputText.getText().toString().trim();

        // Check if input field is empty and notify the user
        if (input.isEmpty()) {
            String errorMsg = getString(R.string.error_empty_input);
            etInputText.setError(errorMsg);
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
            return;
        }

        // Calculate text metrics using the separate calculator class
        TextMetricsCalculator.MetricsResult result = calculator.calculate(input);

        // Display current metrics result
        String formattedResult = getString(
                R.string.metrics_result_format,
                result.getSentences(),
                result.getWords(),
                result.getPunctuationMarks(),
                result.getNumbers()
        );
        tvCurrentMetrics.setText(formattedResult);

        // Prepend entry to runtime calculation history
        String historyEntry = calculator.formatHistoryEntry(input, result);
        historyList.add(0, historyEntry);

        StringBuilder historyBuilder = new StringBuilder();
        for (int i = 0; i < historyList.size(); i++) {
            historyBuilder.append(historyList.get(i));
            if (i < historyList.size() - 1) {
                historyBuilder.append("\n");
            }
        }
        tvHistory.setText(historyBuilder.toString());
    }
}