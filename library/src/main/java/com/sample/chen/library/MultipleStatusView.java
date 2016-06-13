package com.sample.chen.library;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * 一个方便在多种状态切换的view
 * Created by chen on 2016/6/13.
 */
public class MultipleStatusView extends RelativeLayout{

    public final static int STATUS_CONTENT = 0;
    public final static int STATUS_LOADING = 1;
    public final static int STATUS_EMPTY = 2;
    public final static int STATUS_RELOAD = 3;

    public MultipleStatusView(Context context) {
        super(context);
    }

    public MultipleStatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultipleStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
