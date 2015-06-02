package com.android.tuttun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class game_mode extends Activity {

		private final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
		int a = 0;
		@Override
		public void onCreate(Bundle savedInstanceState) {     
			super.onCreate(savedInstanceState);
			// レイアウトを作成   
			LinearLayout layout1 = new LinearLayout(this);   
			layout1.setOrientation(LinearLayout.VERTICAL); 
			layout1.setGravity(Gravity.CENTER_HORIZONTAL);
			setContentView(layout1);

			//テキスト表示
			TextView tv1 = new TextView(this);
			tv1.setText("Game Mode(・ω・○)");
			tv1.setTextSize(20.0f);
			layout1.addView(tv1,
					new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
			
			TextView tv2 = new TextView(this);
			tv2.setText("\n対戦方法を選択してね!\n");
			tv2.setTextSize(15.0f);
			layout1.addView(tv2,
					new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
			
	
			
	        //ボタン表示
	        Button button1 = new Button(this);
	        button1.setText("2人で対戦");
	        button1.setWidth(150);
	        button1.setTextSize(24.0f);
		    layout1.addView(button1,    
		    		new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
	        button1.setOnClickListener(new OnClickListener(){
	        	public void onClick(View arg0) {
	        		Button1_OnClick();
	        		
				}
	        });

			TextView tv3 = new TextView(this);
			tv3.setText("");
			tv3.setTextSize(10.0f);
			layout1.addView(tv3,
					new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
	        
	        Button button2 = new Button(this);
	        button2.setText("CPUと対戦");
	        button2.setWidth(150);
	        button2.setTextSize(24.0f);
		    layout1.addView(button2,    
		    		new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
	        button2.setOnClickListener(new OnClickListener(){
	        	public void onClick(View arg0) {
	        		Button2_OnClick();
	        		a = 1;
				}
	        });

			TextView tv4 = new TextView(this);
			tv4.setText("");
			tv4.setTextSize(30.0f);
			layout1.addView(tv4,
					new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
	        
/*
	        Button button3 = new Button(this);
	        button3.setText("HOME");
	        button3.setWidth(80);
		    button3.setHeight(10);
	        button3.setTextSize(10.0f);
		    layout1.addView(button3,    
		    		new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
	        button3.setOnClickListener(new OnClickListener(){
	        	public void onClick(View arg0) {
	        		Button3_OnClick();
				}
	        });*/
	    }
	   
	   
	   //ボタンのタッチイベントの指定（リンク先の指定）
	   private void Button1_OnClick(){
		   
		   Intent intent = new Intent();

	       intent.setClassName(
	               "com.android.tuttun",
	               "com.android.tuttun.PBLAppView");
	       intent.putExtra("mode", 0+"");
	       startActivity(intent);
		    /*
		   Intent intent = new Intent(game_mode.this,
				   PBLAppView.class);
		   intent.putExtra("mode", 0+"");
		   startActivity(intent);*/
	       finish();
	   }
	   private void Button2_OnClick(){//cpu対戦
		   
		   Intent intent = new Intent();

	       intent.setClassName(
	               "com.android.tuttun",
	               "com.android.tuttun.PBLAppView");
	       intent.putExtra("mode", 1+"");
	       startActivity(intent);/*
		   Intent intent = new Intent(game_mode.this,
				   PBLAppView.class);
		   intent.putExtra("mode", 1+"");
		   startActivity(intent);
*/
	       finish();
	   }

	   private void Button3_OnClick(){
		   Intent intent = new Intent();

	       intent.setClassName(
	               "com.android.tuttun",
	               "com.android.tuttun.PBLAppView");
	       startActivity(intent);

	       finish();
	   }
}