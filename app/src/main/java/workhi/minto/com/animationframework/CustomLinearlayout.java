package workhi.minto.com.animationframework;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/12/24 0024.
 */

public class CustomLinearlayout extends LinearLayout {
    private Context m_context;
    public CustomLinearlayout(Context context) {
        super(context);
        m_context = context;
    }

    public CustomLinearlayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        m_context = context;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {

        return new CustomLayoutParams(m_context,attrs);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        //此处偷梁换柱，添加每个动画view的时候，在子view外部包裹一层MyFrameLayout，然后将动画view的自定义属性取出来
        //再放到MyFrameLayout上面去，最后让MyFrameLayout动起来就可以了。
        if (hasCustomAttrs(params)) {
            FrameLayoutWraper layoutWraper = new FrameLayoutWraper(m_context);
            CustomLayoutParams param = (CustomLayoutParams) params;
            layoutWraper.setScrllview_translation(param.scrollview_translation);
            layoutWraper.setScrollview_alpha(param.scrollview_alpha);
            layoutWraper.setScrollview_fromBgcolor(param.scrollview_fromBgcolor);
            layoutWraper.setScrollview_toBgcolor(param.scrollview_toBgcolor);
            layoutWraper.setScrollview_scaleX(param.scrollview_scaleX);
            layoutWraper.setScrollview_scaleY(param.scrollview_scaleY);
            layoutWraper.addView(child);
            super.addView(layoutWraper, index, params);
        }else{
            super.addView(child, index, params);
        }


    }

    private boolean hasCustomAttrs(ViewGroup.LayoutParams params) {
        CustomLayoutParams param = (CustomLayoutParams) params;

        return param.scrollview_scaleY||
                param.scrollview_scaleX||
                param.scrollview_alpha||
                param.scrollview_toBgcolor!=-1||
                (param.scrollview_fromBgcolor!=-1&&param.scrollview_toBgcolor!=-1)
                ;
    }


}
