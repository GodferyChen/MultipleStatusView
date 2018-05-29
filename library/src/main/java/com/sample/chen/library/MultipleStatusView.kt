package com.sample.chen.library

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

/**
 * 一个方便在多种状态切换的view
 * Created by chen on 2016/6/13.
 */
class MultipleStatusView : FrameLayout {

    private var mChildView: View? = null
    private var mCustomView: View? = null
    private var mOnActionListener: OnActionListener? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    override fun onFinishInflate() {
        val childCount = childCount
        if (childCount > 1) {
            throw IllegalStateException("MultipleStatusVIew only host 1 elements")
        } else if (childCount == 1) {
            mChildView = getChildAt(0)
        }
        super.onFinishInflate()
    }

    fun none() {
        custom(View(context))
    }

    fun loading() {
        custom(R.layout.layout_loading)
    }

    fun empty() {
        val res = resources
        custom(res.getDrawable(R.mipmap.ic_empty),
                res.getString(R.string.empty_tips),
                res.getString(R.string.empty_action))
    }

    fun reload() {
        val res = resources
        custom(res.getDrawable(R.mipmap.ic_reload),
                res.getString(R.string.reload_tips),
                res.getString(R.string.reload_action))
    }

    fun custom(drawable: Drawable?, tips: String, action: String) {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_action, this, false)

        val imageView = view.findViewById(R.id.image) as ImageView
        if (drawable == null) {
            imageView.visibility = View.GONE
        } else {
            imageView.visibility = View.VISIBLE
            imageView.setImageDrawable(drawable)
        }

        val textView = view.findViewById(R.id.txt_tips) as TextView
        if (TextUtils.isEmpty(tips)) {
            textView.visibility = View.GONE
        } else {
            textView.visibility = View.VISIBLE
            textView.text = tips
        }

        val button = view.findViewById(R.id.btn_action) as Button
        if (TextUtils.isEmpty(action)) {
            button.visibility = View.GONE
        } else {
            button.visibility = View.VISIBLE
            button.text = action
            button.setOnClickListener { v -> if (mOnActionListener != null) mOnActionListener!!.onAction(v) }
        }
        custom(view)
    }

    fun custom(resId: Int) {
        custom(LayoutInflater.from(context).inflate(resId, this, false))
    }

    fun custom(customView: View) {
        removeView(mCustomView)
        mCustomView = customView
        if (mChildView != null) mChildView!!.visibility = View.GONE
        if (mCustomView != null) mCustomView!!.visibility = View.VISIBLE
        addView(mCustomView, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
    }

    fun dismiss() {
        removeView(mCustomView)
        if (mChildView != null) mChildView!!.visibility = View.VISIBLE
    }

    interface OnActionListener {
        fun onAction(view: View)
    }

    fun setOnActionListener(onActionListener: OnActionListener) {
        mOnActionListener = onActionListener
    }
}
