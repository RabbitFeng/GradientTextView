package com.rabbit.gradienttextview;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.rabbit.gradienttextview.databinding.ActivityMainBinding;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Thread(() -> {
            binding.tvText.post(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "run: " + binding.tvText.getWidth());
                }
            });
        }).start();


        // 设置背景渐变色
//        binding.tvText.setBackgroundResource(R.drawable.bg_gradient);

        // 设置文字渐变色
        // LinearGradient的初始化参数需要获取控件宽度(或者高度)
        // `onCreate`方法`tvText`虽然创建了实例，但是不能确定是否完成`measure`
        // 1. 添加布局变化监听器，回调方法中设置渐变色（View完成layout前）
        binding.tvText.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                int paddingStart = binding.tvText.getPaddingStart();
                int paddingEnd = binding.tvText.getPaddingEnd();
                int paddingTop = binding.tvText.getPaddingTop();
                int paddingBottom = binding.tvText.getPaddingBottom();
                LinearGradient linearGradient
                        = new LinearGradient(0, 0, right - left - paddingStart - paddingEnd, 0,
                        getResources().getColor(R.color.color_start_1),
                        getResources().getColor(R.color.color_end_1),
                        Shader.TileMode.CLAMP);
                binding.tvText.getPaint().setShader(linearGradient);
            }
        });

        // 2. 将任务加入消息队列中
        binding.tvText.post(new Runnable() {
            @Override
            public void run() {
                LinearGradient linearGradient = new LinearGradient(0, 0, binding.tvText.getWidth(), 0,
                        getResources().getColor(R.color.color_start_1),
                        getResources().getColor(R.color.color_end_1),
                        Shader.TileMode.CLAMP);
                binding.tvText.getPaint().setShader(linearGradient);
            }
        });

    }
}