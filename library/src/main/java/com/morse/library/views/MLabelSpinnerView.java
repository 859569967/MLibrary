package com.morse.library.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.morse.library.R;

/**
 * 组合控件：标签+spinner+标签
 *
 * @company:大后天
 * @Author:曾明
 * @Time:2017/4/21 10:28
 * @Description:
 */
public class MLabelSpinnerView extends LinearLayout implements AdapterView.OnItemSelectedListener {

    private TextView tvLeftLabel;
    private Spinner sSpinner;
    private TextView tvRightLabel;

    private String leftLabel;
    private String rightLabel;
    private ArrayAdapter mAdapter;
    private Context mContext;
    private String[] mItems;
    private String mSelectItem;

    private ILabelSpinnerListener mListener;

    /**
     * 设置监听器
     *
     * @param listener
     */
    public void setLabelSpinnerListener(ILabelSpinnerListener listener) {
        mListener = listener;
    }

    public MLabelSpinnerView(Context context) {
        super(context, null);
    }

    public MLabelSpinnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(context, attrs);
    }

    public MLabelSpinnerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(context, attrs);
    }

    /**
     * 初始化view
     *
     * @param context
     * @param attrs
     */
    private void initView(Context context, AttributeSet attrs) {
        setGravity(Gravity.CENTER_VERTICAL);
        setOrientation(HORIZONTAL);
        setWillNotDraw(false);
        View view = LayoutInflater.from(context).inflate(R.layout.include_label_spinner, this);
        tvLeftLabel = (TextView) view.findViewById(R.id.tv_spinner_left_label);
        sSpinner = (Spinner) view.findViewById(R.id.s_spinner);
        tvRightLabel = (TextView) view.findViewById(R.id.tv_spinner_right_label);

        sSpinner.setOnItemSelectedListener(this);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MLabelSpinnerView);
        leftLabel = a.getString(R.styleable.MLabelSpinnerView_m_label_spinner_left_text);
        rightLabel = a.getString(R.styleable.MLabelSpinnerView_m_label_spinner_right_text);
        a.recycle();

        setLeftLabel(leftLabel);
        setRightLabel(rightLabel);
    }

    /**
     * 设置左边的标签
     *
     * @param label
     */
    public void setLeftLabel(String label) {
        if (!TextUtils.isEmpty(label)) {
            tvLeftLabel.setText(label);
        }
    }

    /**
     * 设置右边的标签
     *
     * @param label
     */
    public void setRightLabel(String label) {
        if (!TextUtils.isEmpty(label)) {
            tvRightLabel.setText(label);
            tvRightLabel.setVisibility(VISIBLE);
        }
    }

    /**
     * 设置适配器
     *
     * @param items
     */
    public void setAdapter(String[] items) {
        if (null == items || 0 == items.length) {
            return;
        }
        mItems = items;
        mSelectItem = mItems[0];
        if (null == mAdapter) {
            mAdapter = new ArrayAdapter<>(mContext, R.layout.simple_spanner_item, items);
            mAdapter.setDropDownViewResource(R.layout.include_simple_spanner);
            sSpinner.setAdapter(mAdapter);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (null == mItems || position >= mItems.length || position < 0) {
            return;
        }
        String str = mItems[position];
        mSelectItem = str;
        if (!TextUtils.isEmpty(str) && null != mListener) {
            mListener.onItemSelected(this, str, position);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public String getSelectText() {
        return mSelectItem;
    }

    /**
     * view回调接口
     */
    public interface ILabelSpinnerListener {
        /**
         * 选择接口回调
         *
         * @param view
         * @param str
         */
        void onItemSelected(View view, String str, int position);
    }

    /**
     * 初始化选中
     */
    public void setSelection(int selection) {
        sSpinner.setSelection(selection);
        mSelectItem = mItems[selection];
    }

    public int setSelectByValue(String value) {
        for (int index = 0; index < mItems.length; index++) {
            String arryValue = String.valueOf(mItems[index]);
            if (value.equals(arryValue)) return index;
        }
        return 0;
    }

    /**
     * 获取spinner实例
     */
    public Spinner getSpinner() {
        return sSpinner;
    }
}
