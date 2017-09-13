package com.morse.library.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.morse.library.R;

/**
 * 组合控件：标签+输入框+checkbox
 *
 * @company:大后天
 * @Author:曾明
 * @Time:2017/8/21 21:38
 * @Description:
 */
public class MCheckEditView extends RelativeLayout implements CompoundButton.OnCheckedChangeListener {

    private TextView tvCheckLabel;
    private EditText etText;
    private CheckBox cbCheck;

    private ICheckEditListener mListener;

    public void setCheckEditListener(ICheckEditListener listener) {
        mListener = listener;
    }

    public MCheckEditView(Context context) {
        super(context, null);
    }

    public MCheckEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public MCheckEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.include_check_edit, this);
        tvCheckLabel = (TextView) view.findViewById(R.id.tv_check_label);
        etText = (EditText) view.findViewById(R.id.et_check_edit);
        cbCheck = (CheckBox) view.findViewById(R.id.cb_check_box);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MCheckEditView);
        String label = ta.getString(R.styleable.MCheckEditView_m_check_label);
        String text = ta.getString(R.styleable.MCheckEditView_m_check_edit_text);
        String hint = ta.getString(R.styleable.MCheckEditView_m_check_edit_hint);
        boolean check = ta.getBoolean(R.styleable.MCheckEditView_m_check_edit_check, false);
        Drawable bg = ta.getDrawable(R.styleable.MCheckEditView_m_check_bg);
        int ems = ta.getInt(R.styleable.MCheckEditView_m_check_ems, 5);
        ta.recycle();

        setLabel(label);
        setText(text);
        setHint(hint);
        setCheck(check);
        setCheckBg(bg);
        setEms(ems);
        cbCheck.setOnCheckedChangeListener(this);
    }

    /**
     * 设置checkbox背景
     *
     * @param bg
     */
    public void setCheckBg(Drawable bg) {
        if (null != bg) {
            cbCheck.setButtonDrawable(bg);
//            cbCheck.setCompoundDrawables(getResources().getDrawable(R.drawable.selector_check_bg), null, null, null);
        }
    }

    /**
     * 设置checkbox状态
     *
     * @param check
     */
    public void setCheck(boolean check) {
        cbCheck.setChecked(check);
    }

    /**
     * 设置输入框提示
     *
     * @param hint
     */
    public void setHint(String hint) {
        if (!TextUtils.isEmpty(hint)) {
            etText.setHint(hint);
        }
    }

    /**
     * 设置输入框文本
     *
     * @param text
     */
    public void setText(String text) {
        if (!TextUtils.isEmpty(text)) {
            etText.setText(text);
            etText.setSelection(text.length());
        }
    }

    /**
     * 获取checkbox状态
     *
     * @return
     */
    public boolean isCheck() {
        return cbCheck.isChecked();
    }

    /**
     * 获取输入文本
     *
     * @return
     */
    public String getText() {
        Editable edit = etText.getText();
        if (null == edit) {
            return null;
        }
        if (TextUtils.isEmpty(edit.toString())) {
            return null;
        }
        return edit.toString();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView instanceof CheckBox && null != mListener) {
            etText.setTransformationMethod(isChecked ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
            mListener.onCheckChange(this, isChecked);
        }
    }

    /**
     * 设置右边标签文本
     *
     * @param label
     */
    public void setLabel(String label) {
        if (!TextUtils.isEmpty(label)) {
            tvCheckLabel.setText(label);
        }
    }

    /**
     * 设置最下文本占用空间
     *
     * @param ems
     */
    public void setEms(int ems) {
        if (ems > 0) {
            tvCheckLabel.setMinEms(ems);
        }
    }

    /**
     * 设置输入文本类型
     *
     * @param type
     */
    public void setInputType(int type) {
        etText.setInputType(type);
    }

    public interface ICheckEditListener {
        void onCheckChange(View view, boolean check);
    }
}
