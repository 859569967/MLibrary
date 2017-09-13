package com.morse.library.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.morse.library.R;

/**
 * @company:大后天
 * @Author:曾明
 * @Time:2017/8/22 12:00
 * @Description:
 */
public class MCheckTextView extends RelativeLayout implements CompoundButton.OnCheckedChangeListener {

    private TextView mTvCheck;
    private CheckBox mCbCheck;
    private ICheckOperationListener mListener;

    public void setCheckOperationListener(ICheckOperationListener listener) {
        mListener = listener;
    }

    public MCheckTextView(Context context) {
        this(context, null);
    }

    public MCheckTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public MCheckTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attr) {
        View view = LayoutInflater.from(context).inflate(R.layout.include_check_text_layout, this);
        mTvCheck = (TextView) view.findViewById(R.id.ctv_check_text);
        mCbCheck = (CheckBox) view.findViewById(R.id.cb_check_text);

        TypedArray ta = context.obtainStyledAttributes(attr, R.styleable.MCheckTextView);
        String text = ta.getString(R.styleable.MCheckTextView_m_check_text);
        boolean isCheck = ta.getBoolean(R.styleable.MCheckTextView_m_ischeck, false);

        ta.recycle();

        setLabelText(text);
        setChecked(isCheck);
        mCbCheck.setOnCheckedChangeListener(this);
    }

    public void setChecked(boolean isCheck) {
        mCbCheck.setChecked(isCheck);
    }

    public boolean isCheck() {
        return mCbCheck.isChecked();
    }

    public void setEnable(boolean enable) {
        mCbCheck.setEnabled(enable);
    }

    public void setLabelText(String text) {
        if (!TextUtils.isEmpty(text)) {
            mTvCheck.setText(text);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView instanceof CheckBox && null != mListener) {
            mListener.onChange(this, isChecked);
        }
    }

    public interface ICheckOperationListener {
        void onChange(View view, boolean isChecked);
    }
}
