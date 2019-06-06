package com.gizwits.opensource.appkit.utils;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * Created by shang on 2018/1/29.
 */

public class XBottomSheetDialog extends BottomSheetDialog {
    private BottomSheetBehavior behavior;

    public XBottomSheetDialog(@NonNull Context context) {
        this(context, 0);
    }

    public XBottomSheetDialog(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);
    }

    protected XBottomSheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initBehavior();
    }

    private void initBehavior() {
        FrameLayout bottomSheet = (FrameLayout) findViewById(android.support.design.R.id.design_bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);

    }

    public BottomSheetBehavior getBehavior(){
        if(behavior != null ){
            return behavior;
        }else{
            return null;
        }

    }
    @Override
    public void setContentView(@LayoutRes int layoutResId) {
        super.setContentView(layoutResId);
        initBehavior();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        initBehavior();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (null != behavior && (behavior.getState() == BottomSheetBehavior.STATE_HIDDEN)) {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }else if(null == behavior){
           // Log.d(TAG, "onStart: -------");
        }else if(null != behavior){
         //   Log.d(TAG, "onStart: @@@@@@@@@@@@" + behavior.getState());
        }
    }
}
