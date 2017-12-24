package workhi.minto.com.animationframework;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/12/24 0024.
 */

public class CustomScrollView extends ScrollView {
    private LinearLayout m_layout;
    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        m_layout = (LinearLayout) getChildAt(0);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        int count = m_layout.getChildCount();
        int scrollViewHeight = getHeight();
        for (int i = 0; i < count; i++) {
            View child = m_layout.getChildAt(i);
            if (!(child instanceof FrameLayoutWraper)) {
                continue;
            }
            ScrollListener view = (ScrollListener) child;
            int childTop = child.getTop();//child离parent顶部的高度
            int childHeight = child.getHeight();
            int absoluteTop = childTop-t;//t代表划出去的高度
            if (absoluteTop<=scrollViewHeight) {
                int visibleGap = scrollViewHeight-absoluteTop; //代表view看的见的高度
                float ratio = visibleGap/(float)childHeight;
                view.onScroll(clamp(ratio,1.0f,0f));
            }else{
                view.onResetScroll();
            }
        }
    }

    /**
     * 防止ratio
     * 越界
     * @param value
     * @param max
     * @param min
     * @return
     */
    public static float clamp(float value,float max,float min){
        return Math.max((Math.min(value, max)), min);
    }
}
