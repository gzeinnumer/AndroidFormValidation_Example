package com.gzeinnumer.androidformvalidation_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.gzeinnumer.afv.Validator;
import com.gzeinnumer.afv.constant.TypeForm;
import com.gzeinnumer.afv.model.FormInput;
import com.gzeinnumer.afv.model.Rule;
import com.gzeinnumer.androidformvalidation_example.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        example1();
        example2();
    }

    private void example1() {
        Validator validator = new Validator();

        validator.addView(binding.ed1);
        validator.addView(binding.ed2);
        validator.addView(new FormInput(binding.ed3P, binding.ed3), new Rule(TypeForm.TEXT, 4));

        binding.btnSubmit.setOnClickListener(v -> {
            boolean result = validator.validate();
            //true if validate success
            //false if validate failed
            if (result) {
                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Not Done", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void example2() {
        binding.btnSubmit.setOnClickListener(view -> {
            if (binding.ed1.getText().toString().length()==0){
                binding.ed1.setError("Tidak boleh kosong");
            } else if (binding.ed2.getText().toString().length()==0){
                binding.ed2.setError("Tidak boleh kosong");
            } else if (binding.ed3.getText().length()<8){
                binding.ed3P.setError("Tidak boleh kosong");
            } else {
                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
            }
        });

        binding.ed3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.ed3P.setError(null);
            }
        });
    }
}