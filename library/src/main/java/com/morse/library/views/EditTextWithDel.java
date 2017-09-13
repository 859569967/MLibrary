package com.morse.library.views;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.Toast;

import com.morse.library.R;

/**
 * 带删除功能的edittext
 * <p>
 * Created by colin on 2016/1/12.
 */
public class EditTextWithDel extends EditText implements TextWatcher {
    private final static String TAG = "EditTextWithDel";
    private Drawable imgInable, imgAble;
    private Context mContext;

    //输入表情前的光标位置
    private int cursorPos;
    //输入表情前EditText中的文本
    private String inputAfterText;
    //是否重置了EditText的内容
    private boolean resetText = false;

    public void isReset(boolean reset) {
        resetText = reset;
    }

    public EditTextWithDel(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public EditTextWithDel(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public EditTextWithDel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        imgAble = mContext.getResources().getDrawable(R.drawable.icon_clear);
        setDrawable();
    }

    //设置删除图片
    private void setDrawable() {
        if (length() > 0) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, imgAble, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    //处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (imgAble != null && event.getAction() == MotionEvent.ACTION_UP) {
            int X = (int) event.getRawX();
            int Y = (int) event.getRawY();
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - 50;
            if (rect.contains(X, Y)) {
                setText("");
            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (!resetText) {
            cursorPos = getSelectionEnd();
            // 这里用s.toString()而不直接用s是因为如果用s，
            // 那么，inputAfterText和s在内存中指向的是同一个地址，s改变了，
            // inputAfterText也就改变了，那么表情过滤就失败了
            inputAfterText = s.toString();
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!resetText) {
            if (count >= 2) {//表情符号的字符长度最小为2
                CharSequence input = s.subSequence(cursorPos, cursorPos + count);
                if (containsEmoji(input.toString())) {
                    resetText = true;
                    Toast.makeText(mContext, "不支持输入Emoji表情符号", Toast.LENGTH_SHORT).show();
                    //是表情符号就将文本还原为输入表情符号之前的内容
                    setText(inputAfterText);
                    CharSequence text = getText();
                    if (text instanceof Spannable) {
                        Spannable spanText = (Spannable) text;
                        Selection.setSelection(spanText, text.length());
                    }
                }
            }
        } else {
            resetText = false;
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        setDrawable();
    }

    /**
     * 检测是否有emoji表情
     *
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }
}
