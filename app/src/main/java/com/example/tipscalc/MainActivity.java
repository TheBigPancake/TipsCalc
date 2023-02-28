package com.example.tipscalc;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tipscalc.databinding.ActivityMainBinding;
import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.calculateButton.setOnClickListener(view -> calculateTip());
    }

    private void calculateTip() {
        String stringInTextField = binding.costOfService.getText().toString();
        Double cost = Double.parseDouble(stringInTextField);
        if (cost == null) {
            binding.tipResult.setText("");
            return;
        }

        Double tipPercentage = 0.15;
        switch (binding.tipOptions.getCheckedRadioButtonId()) {
            case R.id.option_twenty_percent:
                tipPercentage = 0.20;
                break;
            case R.id.option_eighteen_percent:
                tipPercentage = 0.18;
                break;
        }

        Double tip = tipPercentage * cost;
        if (binding.roundUpSwitch.isChecked()) {
            tip = Math.ceil(tip);
        }

        String formattedTip = NumberFormat.getCurrencyInstance().format(tip);
        binding.tipResult.setText(getString(R.string.tip_amount, formattedTip));
    }
}