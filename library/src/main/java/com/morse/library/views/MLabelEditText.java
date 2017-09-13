package com.morse.library.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.morse.library.R;
import com.morse.library.utils.CountDown;

/**
 * 带倒计时控件的+label+edittext的组合控件
 *
 * @company:大后天
 * @Author:曾明
 * @Time:2017/8/21 19:14
 * @Description:
 */
public class MLabelEditText extends LinearLayout implements View.OnClickListener {
    private TextView tvLabel;
    private EditText etEdit;
    private TextView tvCounter;
    private CountDown mCountDown;
    private long mTime;
    private long mInterval;

    private IRightClickListener mListener;

    public void setRightClickListener(IRightClickListener listener) {
        mListener = listener;
    }

    public MLabelEditText(Context context) {
        this(context, null);
    }

    public MLabelEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public MLabelEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
//        setGravity(Gravity.CENTER_VERTICAL);
//        setOrientation(HORIZONTAL);
//        setWillNotDraw(false);
        View view = LayoutInflater.from(context).inflate(R.layout.include_label_editview, this);
//        view.setBackgroundResource(R.drawable.shape_input_bg);
        tvLabel = (TextView) view.findViewById(R.id.lev_label);
        etEdit = (EditText) view.findViewById(R.id.lev_edit);
        tvCounter = (TextView) view.findViewById(R.id.lev_count);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MLabelEditView);
        String label = ta.getString(R.styleable.MLabelEditView_m_lev_label);
        String hint = ta.getString(R.styleable.MLabelEditView_m_lev_edit_hint);
        String edit = ta.getString(R.styleable.MLabelEditView_m_lev_edit);
        String counter = ta.getString(R.styleable.MLabelEditView_m_lev_count);
        int vCount = ta.getInt(R.styleable.MLabelEditView_m_lev_count_visibilty, 0);
        int vLabel = ta.getInt(R.styleable.MLabelEditView_m_lev_label_visibilty, 0);
        int color = ta.getInt(R.styleable.MLabelEditView_m_lev_count_color, Color.WHITE);
        int ems = ta.getInt(R.styleable.MLabelEditView_m_lev_ems, 5);
        ta.recycle();

        setLabel(label);
        setEditHint(hint);
        setEditText(edit);
        setCountText(counter);
        setCountVisibility(tvLabel, vLabel);
        setCountVisibility(tvCounter, vCount);
        setCountColor(color);
        setMinEms(ems);
        tvCounter.setOnClickListener(this);
    }

    /**
     * 设置右边控件的背景颜色
     *
     * @param color
     */
    public void setCountColor(int color) {
        if (color != -1) {
            tvCounter.setBackgroundResource(color);
        }
    }

    public void setTvCounterEnable(boolean isEnable){
        tvCounter.setEnabled(isEnable);
    }

    /**
     * 设置左边控件文本
     *
     * @param label
     */
    public void setLabel(String label) {
        if (!TextUtils.isEmpty(label)) {
            tvLabel.setText(label);
        }
    }

    /**
     * 设置EditText hint
     *
     * @param hint
     */
    public void setEditHint(String hint) {
        if (!TextUtils.isEmpty(hint)) {
            etEdit.setHint(hint);
        }
    }

    /**
     * 设置EditText 文本
     *
     * @param edit
     */
    public void setEditText(String edit) {
        if (!TextUtils.isEmpty(edit)) {
            etEdit.setText(edit);
            etEdit.setSelection(edit.length());
        }
    }

    /**
     * 设置右边控件文本
     *
     * @param counter
     */
    public void setCountText(String counter) {
        if (!TextUtils.isEmpty(counter)) {
            tvCounter.setText(counter);
        }
    }

    /**
     * 设置输入文本类型
     *
     * @param type
     */
    public void setInputType(int type) {
        etEdit.setInputType(type);
    }

    /**
     * 设置倒计时时长
     *
     * @param time
     */
    public void setTime(long time) {
        if (time > 0) {
            mTime = time;
        }
    }

    /**
     * 设置倒计时时间间隔
     *
     * @param interval
     */
    public void setInterval(long interval) {
        if (interval > 0) {
            mInterval = interval;
        }
    }

    /**
     * 设置右边控件的显示与隐藏
     * <p>0:显示</p>
     * <p>1:隐藏</p>
     * <p>2:隐藏，但控件还是占有空间</p>
     *
     * @param v
     */
    public void setCountVisibility(View view, int v) {
        if (v > 0) {
            switch (v) {
                case 0:
                    view.setVisibility(VISIBLE);
                    break;
                case 1:
                    view.setVisibility(GONE);
                    break;
                case 2:
                    view.setVisibility(INVISIBLE);
                    break;
                default:
                    view.setVisibility(VISIBLE);
                    break;
            }
        }
    }

    /**
     * 获取EditText文本
     *
     * @return
     */
    public String getText() {
        Editable editable = etEdit.getText();
        if (null == editable || TextUtils.isEmpty(editable.toString())) {
            return "";
        }
        return editable.toString();
    }

    public void setTextType() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.lev_count) {
            if (null != mListener) {
                mListener.onRightClick();
            }
            if (null == mCountDown) {
                mCountDown = new CountDown(getContext(), mTime, mInterval, tvCounter, 0, 0, 0);
            }
            mCountDown.start();
        }
    }

    public void setMinEms(int minEms) {
        if (minEms > 0) {
            tvLabel.setMinEms(minEms);
        }
    }

    /**
     * 右边控件点击事件
     */
    public interface IRightClickListener {
        void onRightClick();
    }
}
