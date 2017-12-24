package workhi.minto.com.animationframework;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2017/12/24 0024.
 */

public class FrameLayoutWraper extends FrameLayout implements ScrollListener{
       private boolean scrollview_alpha;
    private boolean scrollview_scaleX    ;
    private boolean scrollview_scaleY  ;
    private int scrollview_fromBgcolor;
    private int  scrollview_toBgcolor;
    private int   scrllview_translation;
    private int mWidth,mHeight;
    private ArgbEvaluator m_evaluator = new ArgbEvaluator();

    public int getmWidth() {
        return mWidth;
    }

    public void setmWidth(int mWidth) {
        this.mWidth = mWidth;
    }

    public int getmHeight() {
        return mHeight;
    }

    public void setmHeight(int mHeight) {
        this.mHeight = mHeight;
    }

    private static final int TRANSLATION_FROM_TOP = 0x01;
    private static final int TRANSLATION_FROM_RIGHT = 0x02;
    private static final int TRANSLATION_FROM_BOTTOM = 0x04;
    private static final int TRANSLATION_FROM_LEFT = 0x08;

    public boolean isScrollview_alpha() {
        return scrollview_alpha;
    }

    public void setScrollview_alpha(boolean scrollview_alpha) {
        this.scrollview_alpha = scrollview_alpha;
    }

    public boolean isScrollview_scaleX() {
        return scrollview_scaleX;
    }

    public void setScrollview_scaleX(boolean scrollview_scaleX) {
        this.scrollview_scaleX = scrollview_scaleX;
    }

    public boolean isScrollview_scaleY() {
        return scrollview_scaleY;
    }

    public void setScrollview_scaleY(boolean scrollview_scaleY) {
        this.scrollview_scaleY = scrollview_scaleY;
    }

    public int getScrollview_fromBgcolor() {
        return scrollview_fromBgcolor;
    }

    public void setScrollview_fromBgcolor(int scrollview_fromBgcolor) {
        this.scrollview_fromBgcolor = scrollview_fromBgcolor;
    }

    public int getScrollview_toBgcolor() {
        return scrollview_toBgcolor;
    }

    public void setScrollview_toBgcolor(int scrollview_toBgcolor) {
        this.scrollview_toBgcolor = scrollview_toBgcolor;
    }

    public int getScrllview_translation() {
        return scrllview_translation;
    }

    public void setScrllview_translation(int scrllview_translation) {
        this.scrllview_translation = scrllview_translation;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    /**
     *
     1010101000101    a
     0110010100101    b
     0101001010101    c


     1111111110101    d=a|b|c


     1010101000101    a&d = a
     0110010100101    b&d = b
     0101001010101    c&d = c

     掩码算法：下面的方法可以查出来明码scrllview_translation是否包含掩码translationMask  就如同检测上面的d是否含有a，b，c
     * @param translationMask
     * @return
     */
    private boolean isTranslationFrom(int translationMask){
        if (scrllview_translation == -1) {
            return false;
        }

        return (scrllview_translation&translationMask)==translationMask;
    }
    public FrameLayoutWraper(@NonNull Context context) {
        super(context);
    }

    public FrameLayoutWraper(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onScroll(float ratio) {
        if (scrollview_alpha) {
            setAlpha(ratio);
        }
        if (scrollview_scaleX) {
            setScaleX(ratio);
        }
        if (scrollview_scaleY) {
            setScaleY(ratio);
        }

        if (isTranslationFrom(TRANSLATION_FROM_TOP) ) {
            setTranslationY(mHeight*(1-ratio));
        }

        if (isTranslationFrom(TRANSLATION_FROM_BOTTOM) ) {
            setTranslationY(mHeight*(ratio));
        }
        if (isTranslationFrom(TRANSLATION_FROM_RIGHT) ) {
            setTranslationX(mWidth*(1-ratio));
        }
        if (isTranslationFrom(TRANSLATION_FROM_BOTTOM) ) {
            setTranslationX(mWidth*(ratio));
        }

        if (scrollview_fromBgcolor!=-1&& (scrollview_toBgcolor!=-1)) {
            setBackgroundColor((Integer) m_evaluator.evaluate(ratio,scrollview_fromBgcolor,scrollview_toBgcolor));
        }

    }

    @Override
    public void onResetScroll() {
        if (scrollview_alpha) {
            setAlpha(0);
        }
        if (scrollview_scaleX) {
            setScaleX(0);
        }
        if (scrollview_scaleY) {
            setScaleY(0);
        }

        if (isTranslationFrom(TRANSLATION_FROM_TOP) ) {
            setTranslationY(mHeight*(1-0));
        }

        if (isTranslationFrom(TRANSLATION_FROM_BOTTOM) ) {
            setTranslationY(mHeight*(0));
        }
        if (isTranslationFrom(TRANSLATION_FROM_RIGHT) ) {
            setTranslationX(mWidth*(1-0));
        }
        if (isTranslationFrom(TRANSLATION_FROM_BOTTOM) ) {
            setTranslationX(mWidth*(0));
        }

        if (scrollview_fromBgcolor!=-1&& (scrollview_toBgcolor!=-1)) {
            setBackgroundColor(scrollview_fromBgcolor);
        }
    }
}
