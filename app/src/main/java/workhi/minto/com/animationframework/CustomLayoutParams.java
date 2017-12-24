package workhi.minto.com.animationframework;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/12/24 0024.
 */

public class CustomLayoutParams extends LinearLayout.LayoutParams {
    public boolean scrollview_alpha;
    public boolean scrollview_scaleX    ;
    public boolean scrollview_scaleY  ;
    public int scrollview_fromBgcolor;
    public int  scrollview_toBgcolor;
    public int   scrollview_translation;
    public CustomLayoutParams(Context c, AttributeSet attrs) {

        super(c, attrs);
        TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.scrollview_layoutparams);
        scrollview_alpha = a.getBoolean(R.styleable.scrollview_layoutparams_scrollview_alpha, false);
        scrollview_scaleX = a.getBoolean(R.styleable.scrollview_layoutparams_scrollview_scaleX, false);
        scrollview_scaleY = a.getBoolean(R.styleable.scrollview_layoutparams_scrollview_scaleY, false);
        scrollview_fromBgcolor = a.getColor(R.styleable.scrollview_layoutparams_scrollview_fromBgcolor, -1);
        scrollview_toBgcolor = a.getColor(R.styleable.scrollview_layoutparams_scrollview_toBgcolor, -1);
        scrollview_translation = a.getInt(R.styleable.scrollview_layoutparams_scrllview_translation, -1);
        a.recycle();
    }
}
