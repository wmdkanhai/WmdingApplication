package com.wmding.myviewlib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wmding.myviewlib.R;

/**
 * @author 明月
 * @version 1.0
 * @date 1/24/22 1:43 PM
 * @description: 组合自定义 View
 */
public class CustomTitleView extends RelativeLayout implements View.OnClickListener {

    private View.OnClickListener mLeftOnClickListener;
    private Button mBackBtn;
    private TextView mTittleView;

    public CustomTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.custom_title_view, this);
        mBackBtn = findViewById(R.id.btn_left);
        mBackBtn.setOnClickListener(this);
        mTittleView = findViewById(R.id.title_tv);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_left) {
            if (mLeftOnClickListener != null) {
                mLeftOnClickListener.onClick(v);
            }
        }
    }

    public void setLeftOnClickListener(View.OnClickListener leftOnClickListener) {
        mLeftOnClickListener = leftOnClickListener;
    }

    public void setTittle(String title) {
        mTittleView.setText(title);
    }

    public void setBackText(String text) {
        mBackBtn.setText(text);
    }

    public void setBackColor(int color) {
        mBackBtn.setBackgroundColor(color);
    }
}
