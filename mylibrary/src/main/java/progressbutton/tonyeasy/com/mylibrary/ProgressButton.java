package progressbutton.tonyeasy.com.mylibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * 作者：张恒同
 * 时间：2017/6/20 0020   16:31
 * 描述：--
 */

public class ProgressButton extends Button implements Animatable {
    private ProgressDrawable drawable;
    private CharSequence    mTextOn;
    private CharSequence    mTextOff;
    private float           drawPadding;

    public interface onAnimFinish {
        void onFinish();
    }
    private onAnimFinish listener;

    public ProgressButton(Context context) {
        super(context);
        init();
    }

    public ProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttrs(context, attrs);
        init();
    }

    public ProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttrs(context, attrs);
        init();
    }

    private void init() {
        drawable = new ProgressDrawable(getTextSize(), this);
        drawable.setColorDefault(getCurrentTextColor());
        drawable.setAnimatable(this);
    }

    private void parseAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressButton);
        if (typedArray == null) return;
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.ProgressButton_textOff) {
                mTextOff = typedArray.getString(attr);
            } else if (attr == R.styleable.ProgressButton_textOn) {
                mTextOn = typedArray.getString(attr);
            } else if (attr == R.styleable.ProgressButton_drawPadding){
                drawPadding = typedArray.getDimension(attr, 15);
            }
        }
        typedArray.recycle();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public void startRotate() {
        this.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        setCompoundDrawablePadding((int) drawPadding);
        drawable.startRotate();
    }

    public void animOn() {
        this.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        setCompoundDrawablePadding((int) drawPadding);
        drawable.animFinish();
        this.setText(mTextOn);
    }

    public void animOn(int rc) {
        this.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(rc), null, null, null);
        setCompoundDrawablePadding((int) drawPadding);
        drawable.animFinish();
    }


    public void animOff() {
        this.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        setCompoundDrawablePadding((int) drawPadding);
        drawable.animError();
        this.setText(mTextOff);
    }

    public void animOff(int rc) {
        this.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(rc), null, null, null);
        setCompoundDrawablePadding((int) drawPadding);
        drawable.animFinish();
        this.setText(mTextOff);
    }

    public void removeDrawable() {
        this.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        drawable.stopRotate();
    }

    @Override
    public void start() {
        startRotate();
    }

    @Override
    public void stop() {

        if (listener != null) {
            listener.onFinish();
        }
    }

    public void setOnAnimFinishListener(onAnimFinish listener) {
        this.listener = listener;
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
