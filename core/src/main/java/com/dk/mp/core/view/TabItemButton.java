package com.dk.mp.core.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.dk.mp.core.R;

/**
 * Created by dongqs on 2017/3/6.
 */

public class TabItemButton extends android.support.v7.widget.AppCompatButton {

    private Drawable drawableNormal;
    private Drawable drawableSelected;
    private boolean isSelected;
    private float width;
    private float height;

    public TabItemButton(Context context) {
        super(context);
    }

    public TabItemButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TypedArray是存放资源的array,1.通过上下文得到这个数组,attrs是构造函数传进来的,对应attrs.xml
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TabItemButton);
        // 获得xml里定义的属性,格式为 名称_属性名 后面是默认值
        drawableNormal = a.getDrawable(R.styleable.TabItemButton_drawableTop_normal);
        drawableSelected = a.getDrawable(R.styleable.TabItemButton_drawableTop_selected);
        isSelected = a.getBoolean(R.styleable.TabItemButton_isSelected,false);
        width = a.getDimension(R.styleable.TabItemButton_drawableWidth,18);
        height = a.getDimension(R.styleable.TabItemButton_drawableHeight,18);
        drawableView();
        // 为了保持以后使用该属性一致性,返回一个绑定资源结束的信号给资源
        a.recycle();
    }

    public TabItemButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void drawableView() {
        drawableNormal.setBounds(0, 0, (int) width, (int) height);
        drawableSelected.setBounds(0, 0, (int) width, (int)height);
        if (isSelected) {
            getSelected();
        } else {
            getNormal();
        }
    }

    public void beforeOnclick(){
        ViewGroup viewGroup = (ViewGroup) getParent();
        for (int i = 0 ; i < viewGroup.getChildCount() ; i++) {
            View v = viewGroup.getChildAt(i);
            if (v.getClass().getName().equals("com.dk.mp.core.view.TabItemButton")) {
                if (v == this) {
                    getSelected();
                } else {
                    getNormal();
                }
            }
        }
    }

    private void getNormal(){
        setCompoundDrawables(null, drawableNormal , null, null);
        setTextColor(Color.rgb(151,151,151));
    }

    private void getSelected(){
        setCompoundDrawables(null, drawableSelected , null, null);
        setTextColor(Color.rgb(33,150,243));
    }
}
