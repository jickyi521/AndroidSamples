package com.example.testandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.testandroid.wave.AnimationFrameLayout2;

public class MainActivity extends Activity {

    public Button m_btn1;
    AnimationFrameLayout2 afl; //动画布局
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		setContentView(R.layout.activity_wave_main);
        
        afl = ((AnimationFrameLayout2) findViewById(R.id.search_animation_wf_main));// 搜索时的动画
        m_btn1 = (Button) findViewById(R.id.button1);
        m_btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                afl.startAnimation();
            }
        });
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onDestroy() {
	    // TODO Auto-generated method stub
	    super.onDestroy();
	    afl.stopAnimation();
	}

}
