package com.morse.library.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.morse.library.R;

/**
 * @company:大后天
 * @Author:曾明
 * @Time:2017/8/22 12:01
 * @Description:
 */
public class MImgTextView extends RelativeLayout implements View.OnClickListener {

    private TextView mTVImgText;
    private ImageView mIvImg;
    private TextView startOrClose;

    private IImgTextOperationListener mListener;

    public void setImgTextOperationListener(IImgTextOperationListener listener) {
        mListener = listener;
    }

    public MImgTextView(Context context) {
        this(context, null);
    }

    public MImgTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public MImgTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attr) {
        View view = LayoutInflater.from(context).inflate(R.layout.include_img_text_layout, this);
        mTVImgText = (TextView) view.findViewById(R.id.tv_img_text);
        mIvImg = (ImageView) view.findViewById(R.id.iv_img_text);
        startOrClose = (TextView) view.findViewById(R.id.startOrClose);
        TypedArray ta = context.obtainStyledAttributes(attr, R.styleable.MImgTextView);
        String text = ta.getString(R.styleable.MImgTextView_m_img_text);
        startOrClose.setVisibility(INVISIBLE);
        mIvImg.setVisibility(INVISIBLE);
        ta.recycle();

        setLabelText(text);

        setOnClickListener(this);
    }

    public void setLabelText(String text) {
        if (!TextUtils.isEmpty(text)) {
            mTVImgText.setText(text);
        }
    }

    public void showStarText(boolean visible) {
        startOrClose.setVisibility(visible ? VISIBLE : INVISIBLE);
    }

    public void setStartText(String startText) {
        startOrClose.setText(startText);
    }

    public void showImage(boolean visible) {
        mIvImg.setVisibility(visible ? VISIBLE : INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        if (null != mListener) {
            mListener.onClick(this);
        }
    }

    public interface IImgTextOperationListener {
        void onClick(View view);
    }
}
