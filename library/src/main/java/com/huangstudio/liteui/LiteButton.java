package com.huangstudio.liteui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
* 自定义button
 * 可以设置圆角，方角，显示颜色，按下颜色
**/
public class LiteButton extends TextView {
    private final int DEFAULT_TEXT_COLOR = 0x8a000000;
    //文字演策
    private int txtC = DEFAULT_TEXT_COLOR;
    private int pressTxtC = DEFAULT_TEXT_COLOR;
    //背景色
    private int bgc;
    private int pressBgc;
    //圆角
    private float corner;

    public LiteButton(Context context) {
        super(context);
    }

    public LiteButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public LiteButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs){
        TypedArray typedArray = null;
        corner=-1;
        if (attrs != null) {
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.LiteButton);
        }
        bgc = optColor(typedArray, R.styleable.LiteButton_normalColor,
                0Xffffffff);//0XffAAAAAA;
        pressBgc = optColor(typedArray, R.styleable.LiteButton_pressColor,
                0Xffffffff);//0XffAAAAAA;

        txtC = optColor(typedArray, R.styleable.LiteButton_normalTextColor, DEFAULT_TEXT_COLOR);

        pressTxtC = optColor(typedArray, R.styleable.LiteButton_pressTextColor, DEFAULT_TEXT_COLOR);

        //处理圆角度
        corner = optInt(typedArray,R.styleable.LiteButton_corner,10);

        setClickable(true);

        if (typedArray != null) {
            typedArray.recycle();
        }
    }

    private static int optInt(TypedArray typedArray, int index,
                                    int def) {
        if (typedArray == null) {
            return def;
        }
        return typedArray.getInt(index,def);
    }

    private static int optColor(TypedArray typedArray, int index,
                                int def) {
        if (typedArray == null) {
            return def;
        }
        return typedArray.getColor(index, def);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (corner < 0) {
            corner = 10;
        }
        setBgcDrawable(bgc, pressBgc, corner);
        setTxtColor(txtC, pressTxtC);
    }

    /**
     *
     * @param txtC 正常情况下的字体颜色
     * @param pressTxtC 按下时的字体颜色
     */
    private void setTxtColor(int txtC, int pressTxtC) {
        if (txtC == pressTxtC) {
            setTextColor(txtC);
            return;
        }

        int[] colors = new int[]{pressTxtC, txtC};

        int[][] states = new int[2][];
        states[0] = new int[]{android.R.attr.state_pressed};
        states[1] = new int[]{};

        ColorStateList colorStateList = new ColorStateList(states, colors);

        setTextColor(colorStateList);
    }


    /**
     *
     * @param bgc 正常背景色
     * @param pressBgc 按下背景色
     * @param corner 圆角
     */
    private void setBgcDrawable(int bgc, int pressBgc, float corner) {

        GradientDrawable bgcDrawable = new GradientDrawable();
        GradientDrawable pBgcDrawable = new GradientDrawable();

        bgcDrawable.setCornerRadius(corner); // 设置圆角
        bgcDrawable.setStroke(0, txtC == 0 ? DEFAULT_TEXT_COLOR : txtC);//设置边框
        bgcDrawable.setColor(bgc);//设置背景颜色


        if (bgc == pressBgc) {
            setBackground(bgcDrawable);
            return;
        }

        pBgcDrawable.setCornerRadius(corner);
        pBgcDrawable.setStroke(0, pressBgc);
        pBgcDrawable.setColor(pressBgc);

        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pBgcDrawable);
        stateListDrawable.addState(new int[]{}, bgcDrawable);

        setBackground(stateListDrawable);
    }

    /**
     * 设置背景色
     *
     * @param bgc 正常显示的颜色
     * @param pressBgc 按下去显示的颜色
     */
    public void setBgcDrawable(int bgc,int pressBgc) {
        this.bgc = bgc;
        this.pressBgc = pressBgc;
    }

    /**
     * 设置文字颜色
     *
     * @param txtC 正常显示的文字颜色
     * @param pressTxtC 按下去显示的文字颜色
     */
    public void setTextColor(int txtC, int pressTxtC) {
        this.txtC = txtC;
        this.pressTxtC = pressTxtC;
    }

    /**
    * 设置圆角
    **/
    public void setCorner(float corner) {
        this.corner = corner;
    }

}
