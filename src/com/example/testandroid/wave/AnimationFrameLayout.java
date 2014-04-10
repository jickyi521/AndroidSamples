package com.example.testandroid.wave;

import java.lang.ref.SoftReference;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;

import com.example.testandroid.R;
public class AnimationFrameLayout extends FrameLayout {
    private SoftReference<Bitmap> m_bitmapRipple;//波纹图片 （软引用）
    private RoundImageView[] m_imageVRadars; //ImageView数组
                                                                                                                              
    public AnimationFrameLayout(Context context) {
        super(context);
        init();
    }
    public AnimationFrameLayout(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    public AnimationFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    /**初始化**/
    private void init() {
        loadRadarBitmap();
        m_imageVRadars = new RoundImageView[3];
        View v = LayoutInflater.from(getContext()).inflate(R.layout.wt_search_device_anima, this);
        m_imageVRadars[0] = (RoundImageView) v.findViewById(R.id.radar_ray_1);
        m_imageVRadars[1] = (RoundImageView) v.findViewById(R.id.radar_ray_2);
        m_imageVRadars[2] = (RoundImageView) v.findViewById(R.id.radar_ray_3);
    }
    /**加载图片**/
    private void loadRadarBitmap() {
        try {
            //获取波纹图片
            m_bitmapRipple = new SoftReference<Bitmap>(BitmapFactory.decodeStream(getContext().getResources()
                            .openRawResource(R.drawable.wifi_body_ripple)));
        } catch (Exception localException) {
            Log.e("WTSearchAnimationFrameLayout",
                    Log.getStackTraceString(localException));
        } catch (OutOfMemoryError localOutOfMemoryError) {
            Log.e("WTSearchAnimationFrameLayout",
                    Log.getStackTraceString(localOutOfMemoryError));
            System.gc();  //回收
        }
    }
                                                                                                                              
    /**重置，停止动画**/
    public void stopAnimation() {
        for (int  i= 0;  i< m_imageVRadars.length; ++i) {
            if(m_bitmapRipple != null){
                Bitmap localBitmap = m_bitmapRipple.get(); //软引用获取对象
                if(localBitmap != null && !localBitmap.isRecycled()) {
                    //回收图片资源
                    localBitmap.recycle();
                }
                m_bitmapRipple = null;
                RoundImageView localImageView = m_imageVRadars[i];
                localImageView.setImageBitmap(null); //设置ImageView为空
                localImageView.setVisibility(View.GONE);
                localImageView.clearAnimation(); //取消动画
            }
        }
    }
                                                                                                                              
    /**开始动画**/
    public void startAnimation() {
        if(m_bitmapRipple == null) {
            loadRadarBitmap();
        }
        for (int i = 0; i < m_imageVRadars.length; i++) {
            
            //if(i==1) break;
            RoundImageView localImageView;
            long ltime;
            while(true) {
                localImageView = m_imageVRadars[i];
                localImageView.setImageBitmap(m_bitmapRipple.get());  //获取图片
                localImageView.setVisibility(View.VISIBLE);
                //放大
                ltime= 333L * i;
                if(localImageView.getAnimation() == null) {
                    break;
                }
                localImageView.getAnimation().start();
            }
            
            localImageView.setBackgroundColor(getResources().getColor(R.color.holo_orange_light));
            
            ScaleAnimation localScaleAnimation = new ScaleAnimation(1.0f, 14.0f,1.0f,14.0f,1,0.5f,1,0.5f);
            localScaleAnimation.setRepeatCount(-1); //动画重复
            AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0f, 0.2f);
            AnimationSet localAnimationSet = new AnimationSet(true);  //true:使用相同的加速器
                                                                                                                                      
            localAnimationSet.addAnimation(localScaleAnimation);
            localAnimationSet.addAnimation(localAlphaAnimation);  //将两种动画效果添加进去
            //设置相关属性
            localAnimationSet.setDuration(4000L);  //持续时间
            localAnimationSet.setFillEnabled(true);
            localAnimationSet.setFillBefore(true);  //控件保持在动画开始之前
            localAnimationSet.setStartOffset(ltime);   //动画效果推迟ltime秒钟后启动
            localAnimationSet.setInterpolator(new AccelerateDecelerateInterpolator());
            localAnimationSet.setAnimationListener(new MySearchAnimationHandler(this,localImageView)); //绑定监听器
            //将动画集合设置进去
            localImageView.setAnimation(localAnimationSet);
            localImageView.startAnimation(localAnimationSet);//开启动画
        }
    }
    /**动画监听类**/
    public class MySearchAnimationHandler implements AnimationListener{
        private RoundImageView m_imageVRadar;
                                                                                                                                  
        public MySearchAnimationHandler(AnimationFrameLayout paramImageView,RoundImageView m_imageVRadar) {
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