package com.rabbit.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by RabbitFeng on 2022/2/10
 */
public class GradientTextView extends AppCompatTextView {
    private static final String TAG = GradientTextView.class.getSimpleName();

    private float[] position = new float[]{0f, 1f};
    /**
     * 边框渐变色
     */
    private int[] colorBorder = new int[2];
    /**
     * 边框着色器
     */
    private Shader shaderBorder;
    /**
     * 绘制边框画笔
     */
    private Paint paintBorder;
    /**
     * 描边
     */
    private float strokeWidthBorder;
    /**
     * 边缘矩形范围
     */
    private RectF rectFBorder;

    public GradientTextView(@NonNull Context context) {
        super(context);
        init(null);
    }

    public GradientTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public GradientTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        // 初始化颜色值
        colorBorder[0] = Color.parseColor("#FF65FDF0");
        colorBorder[1] = Color.parseColor("#FF1D6FA3");

        // 初始化描边宽度
        strokeWidthBorder = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());

        if (attrs != null) {

        }

        paintBorder = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBorder.setStrokeWidth(strokeWidthBorder);
        paintBorder.setStyle(Paint.Style.STROKE);

        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w == 0 || h == 0) {
            return;
        }

        int borderShift = Math.round(strokeWidthBorder / 2);
        rectFBorder = new RectF(borderShift, borderShift, w - borderShift, h - borderShift);

        shaderBorder = new LinearGradient(0, 0, w, 0, colorBorder, position, Shader.TileMode.CLAMP);
        paintBorder.setShader(shaderBorder);

        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBorder(canvas);
        super.onDraw(canvas);

    }

    /**
     * 绘制边缘
     */
    private void drawBorder(Canvas canvas) {
        canvas.drawRect(rectFBorder, paintBorder);
    }
}
