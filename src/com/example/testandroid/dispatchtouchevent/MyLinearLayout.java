package com.example.testandroid.dispatchtouchevent;

import android.content.Context; 
import android.util.AttributeSet; 
import android.util.Log; 
import android.view.MotionEvent; 
import android.widget.LinearLayout; 

public class MyLinearLayout extends LinearLayout { 
    private final String TAG = "MyLinearLayout"; 

    public MyLinearLayout(Context context, AttributeSet attrs) { 

        super(context, attrs); 

        Log.e(TAG, TAG); 

    } 

    @Override 
    public boolean dispatchTouchEvent(MotionEvent ev) { 
        int action = ev.getAction(); 

        switch (action) { 

        case MotionEvent.ACTION_DOWN: 

            Log.e(TAG, "dispatchTouchEvent action:ACTION_DOWN"); 

            break; 

        case MotionEvent.ACTION_MOVE: 

            Log.e(TAG, "dispatchTouchEvent action:ACTION_MOVE"); 

            break; 

        case MotionEvent.ACTION_UP: 

            Log.e(TAG, "dispatchTouchEvent action:ACTION_UP"); 

            break; 

        case MotionEvent.ACTION_CANCEL: 

            Log.e(TAG, "dispatchTouchEvent action:ACTION_CANCEL"); 

            break; 

        } 
        return super.dispatchTouchEvent(ev); 
    } 

    @Override 
    public boolean onInterceptTouchEvent(MotionEvent ev) { 

        int action = ev.getAction(); 

        switch (action) { 

        case MotionEvent.ACTION_DOWN: 

            Log.e(TAG, "onInterceptTouchEvent action:ACTION_DOWN"); 

            break; 

        case MotionEvent.ACTION_MOVE: 

            Log.e(TAG, "onInterceptTouchEvent action:ACTION_MOVE"); 

            break; 

        case MotionEvent.ACTION_UP: 

            Log.e(TAG, "onInterceptTouchEvent action:ACTION_UP"); 

            break; 

        case MotionEvent.ACTION_CANCEL: 

            Log.e(TAG, "onInterceptTouchEvent action:ACTION_CANCEL"); 

            break; 

        } 

        return false; 

    } 

    @Override 
    public boolean onTouchEvent(MotionEvent ev) { 

        int action = ev.getAction(); 

        switch (action) { 

        case MotionEvent.ACTION_DOWN: 

            Log.e(TAG, "---onTouchEvent action:ACTION_DOWN"); 

            break; 

        case MotionEvent.ACTION_MOVE: 

            Log.e(TAG, "---onTouchEvent action:ACTION_MOVE"); 

            break; 

        case MotionEvent.ACTION_UP: 

            Log.e(TAG, "---onTouchEvent action:ACTION_UP"); 

            break; 

        case MotionEvent.ACTION_CANCEL: 

            Log.e(TAG, "---onTouchEvent action:ACTION_CANCEL"); 

            break; 

        } 

        return true; 
    } 

} 
