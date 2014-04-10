package com.example.testandroid.wave;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;

import com.example.testandroid.R;
public class AnimationFrameLayout2 extends FrameLayout {
    
    private RoundImageView mImageView;
                                                                                                                              
    public AnimationFrameLayout2(Context context) {
        super(context);
        init();
    }
    public AnimationFrameLayout2(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    public AnimationFrameLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    /**初始化**/
    private void init() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.wt_search_device_anima2, this);
        mImageView = (RoundImageView) v.findViewById(R.id.radar_ray_1);
    }
                                                                                                                              
    /**重置，停止动画**/
    public void stopAnimation() {
        mImageView.setVisibility(View.GONE);
        mImageView.clearAnimation();
    }
                                                                                                                              
    /**开始动画**/
    public void startAnimation() {
            //mImageView.setBackgroundColor(getResources().getColor(R.color.holo_orange_light));
            
            ScaleAnimation localScaleAnimation = new ScaleAnimation(0.0f, 1.0f,0.0f,1.0f,1,0.5f,1,0.5f);
            localScaleAnimation.setRepeatCount(-1); //动画重复
            MyAlphaAnimation localAlphaAnimation = new MyAlphaAnimation(0.0f, 1.0f);
            AnimationSet localAnimationSet = new AnimationSet(true);  //true:使用相同的加速器
                                                                                                                                      
            localAnimationSet.addAnimation(localScaleAnimation);
            localAnimationSet.addAnimation(localAlphaAnimation);  //将两种动画效果添加进去
            //设置相关属性
            localAnimationSet.setDuration(4000L);  //持续时间
            localAnimationSet.setFillEnabled(true);
            localAnimationSet.setFillBefore(true);  //控件保持在动画开始之前
            localAnimationSet.setInterpolator(new AccelerateDecelerateInterpolator());
            localAnimationSet.setAnimationListener(new MySearchAnimationHandler(this, mImageView)); //绑定监听器
            //将动画集合设置进去
            mImageView.setAnimation(localAnimationSet);
            mImageView.startAnimation(localAnimationSet);//开启动画
    }
    
    class MyAlphaAnimation extends AlphaAnimation{
        
        public MyAlphaAnimation(float fromAlpha, float toAlpha) {
            super(fromAlpha, toAlpha);
        }
        
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
           if(interpolatedTime <= 0.5){
               t.setAlpha(interpolatedTime*2);
           }else{
               t.setAlpha(1.0f - interpolatedTime);
           }
        }
    }
    
    /**动画监听类**/
    public class MySearchAnimationHandler implements AnimationListener{
        private RoundImageView m_imageVRadar;
                                                                                                                                  
        public MySearchAnimationHandler(AnimationFrameLayout2 paramImageView,RoundImageView m_imageVRadar) {
            super();
            this.m_imageVRadar = m_imageVRadar;
        }
        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onAnimationEnd(Animation animation) {
            this.m_imageVRadar.setVisibility(View.GONE);
        }
        @Override
        public void onAnimationRepeat(Animation animation) {
            animation.setStartOffset(0L);
        }
    }
}