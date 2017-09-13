package com.morse.library.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 倒计时工具类
 *
 * @company:大后天
 * @Author:曾明
 * @Time:2017/8/23 21:06
 * @Description:
 */
public class CountDown extends CountDownTimer {

    private Context mContext;
    private View mView;
    private int mColor;
    private int mText;
    private int mFinishText;

    /**
     * 倒计时
     *
     * @param context
     * @param millisInFuture    倒计时时长
     * @param countDownInterval 倒计时时间间隔
     * @param view              倒计时控件
     * @param color             控件颜色
     * @param text              倒计时开始之后显示的文本
     * @param finishText        倒计时结束之后显示的文本
     */
    public CountDown(Context context, long millisInFuture, long countDownInterval, View view, int color, int text, int finishText) {
        super(millisInFuture, countDownInterval);
        mContext = context;
        mView = view;
        mColor = color;
        mText = text;
        mFinishText = finishText;
    }

    public CountDown(Context context, long millisInFuture, long countDownInterval, View view, int text, int finishText) {
        this(context, millisInFuture, countDownInterval, view, -1, text, finishText);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (mView instanceof TextView || mView instanceof Button) {
            mView.setClickable(false);
            if (mColor > 0) {
                mView.setBackgroundColor(mContext.getResources().getColor(mColor));
            }
            if (mText <= 0) {
                return;
            }
            mView.setTag(mContext.getResources().getString(mText, millisUntilFinished / 1000));
        }
    }

    @Override
    public void onFinish() {
        if (mView instanceof TextView || mView instanceof Button) {
            mView.setClickable(true);
            if (mColor > 0) {
                mView.setBackgroundColor(mContext.getResources().getColor(mColor));
            }
            if (mFinishText <= 0) {
                return;
            }
            mView.setTag(mContext.getResources().getString(mFinishText));
        }
    }
}
