package com.rabbit.gradienttextview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.rabbit.gradienttextview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 设置背景渐变色
        binding.tvText.setBackgroundResource(R.drawable.bg_gradient);
    }
}