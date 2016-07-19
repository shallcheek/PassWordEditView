package com.chaek.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.chaek.android.R;


public class PassWordEditView extends EditText implements TextWatcher {
    public static final int MAX_LEN = 12;
    public int password_max_len = 6;

    private int width;
    private int height;
    private int passWordCount = 0;

    private RectF rectF;
    private Paint passwordPaint;
    private Paint passwordRoundPaint;
    private int circleSize;
    private int passwordRadius;
    private String password;
    private boolean isComplete;
    private PassWordChangeListener passWordChangeListener;

    private int backgroundColor = 0xffF5F7F9;
    private int lineColor = 0xffE1E4E8;
    private int lineFocusColor = 0xffE1E4E8;
    private int circleColor;
    private int circleFocusColor;


    /**
     * add password change listener
     *
     * @param passWordChangeListener passWordChangeListener
     */
    public void setPassWordChangeListener(PassWordChangeListener passWordChangeListener) {
        this.passWordChangeListener = passWordChangeListener;
    }


    /**
     * @return edit is complete
     */
    public boolean isComplete() {
        return isComplete;
    }


    public String getPassword() {
        if (password == null)
            return "";
        return password;
    }

    public PassWordEditView(Context context) {
        this(context, null);
    }

    public PassWordEditView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PassWordEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PassWordEditView);
        password_max_len = a.getInt(R.styleable.PassWordEditView_edit_max_length, 6);
        backgroundColor = a.getColor(R.styleable.PassWordEditView_edit_background, 0xffF5F7F9);
        lineColor = a.getColor(R.styleable.PassWordEditView_edit_line_color, 0xffF5F7F9);
        lineFocusColor = a.getColor(R.styleable.PassWordEditView_edit_line_focus_color, lineColor);
        circleSize = a.getDimensionPixelSize(R.styleable.PassWordEditView_edit_circle_size, dip2px(5));
        passwordRadius = a.getDimensionPixelSize(R.styleable.PassWordEditView_edit_background_radius, dip2px(2));
        circleColor = a.getColor(R.styleable.PassWordEditView_edit_circle_color, 0xff000000);
        circleFocusColor = a.getColor(R.styleable.PassWordEditView_edit_circle_focus_color, circleColor);

        a.recycle();
        init();
    }


    @Override
    public void setInputType(int type) {
        super.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
    }

    private void init() {

        if (password_max_len > MAX_LEN) {
            password_max_len = MAX_LEN;
        }

        passwordPaint = new Paint();
        passwordPaint.setAntiAlias(true);
        passwordRoundPaint = new Paint();
        passwordRoundPaint.setAntiAlias(true);

        rectF = new RectF();

        this.setCursorVisible(false);
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(password_max_len)});
        setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        addTextChangedListener(this);
        setTextColor(0x00000000);
        setBackgroundColor(0x00000000);
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(0x00000000);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_MOVE) {
            setSelection(getText().toString().length());
            setFocusable(true);
            setFocusableInTouchMode(true);
            requestFocus();
            InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(this, 0);
        }
        return true;

    }

    private int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
        int min = Math.min(width / password_max_len, height);
        if (circleSize > min / 2)
            circleSize = min / 2;

        rectF.top = 0;
        rectF.left = 0;
        rectF.right = width;
        rectF.bottom = height;


    }

    /**
     * @param circleColor password circle background color
     */
    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
        invalidate();
    }

    /**
     * @param background view background color
     */
    public void setBackground(int background) {
        backgroundColor = background;
        invalidate();
    }

    /**
     * @param circleFocusColor password circle focus background color
     */
    public void setCircleFocusColor(int circleFocusColor) {
        this.circleFocusColor = circleFocusColor;
        invalidate();
    }

    /**
     * @param lineColor line color
     */
    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
        invalidate();
    }

    /**
     * @param passwordRadius view background radius
     */
    public void setPasswordRadius(int passwordRadius) {
        this.passwordRadius = passwordRadius;
        invalidate();
    }

    /**
     * @param lineFocusColor this is focus line color
     */
    public void setLineFocusColor(int lineFocusColor) {
        this.lineFocusColor = lineFocusColor;
        invalidate();
    }

    /**
     * @param circleSize password circle size
     */
    public void setCircleSize(int circleSize) {
        this.circleSize = circleSize;
        invalidate();
    }

    /**
     * @param password_max_len edit view input max len
     */
    public void setMaxLen(int password_max_len) {
        this.password_max_len = password_max_len;
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(password_max_len)});
        if (password_max_len > MAX_LEN) {
            this.password_max_len = MAX_LEN;
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        passwordPaint.setColor(backgroundColor);
        passwordPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(rectF, passwordRadius, passwordRadius, passwordPaint);

        if (isFocused()) {
            passwordPaint.setColor(lineFocusColor);
        } else {
            passwordPaint.setColor(lineColor);
        }
        passwordPaint.setStrokeWidth(2f);
        passwordPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(rectF, passwordRadius, passwordRadius, passwordPaint);

        int w = width / password_max_len;
        int h = getHeight() / 2;
        passwordPaint.setStrokeWidth(1f);
        passwordPaint.setStyle(Paint.Style.FILL);

        for (int i = 0; i < password_max_len - 1; i++) {
            canvas.drawLine(i * w + w, 1, i * w + w, height, passwordPaint);
        }
        if (isFocusable()) {
            passwordRoundPaint.setColor(circleFocusColor);
        } else {
            passwordRoundPaint.setColor(circleColor);
        }

        for (int i = 0; i < passWordCount; i++) {
            canvas.drawCircle(i * w + w / 2, h, circleSize, passwordRoundPaint);// 小圆
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        password = s.toString();
        passWordCount = s.length();
        isComplete = passWordCount == password_max_len;

        if (passWordChangeListener != null) {
            passWordChangeListener.onTextChange();
            if (isComplete()) {
                passWordChangeListener.onComplete();
            }
        }
        invalidate();
    }


    @Override
    public void afterTextChanged(Editable s) {

    }

    public interface PassWordChangeListener {
        void onTextChange();

        void onComplete();
    }


}
