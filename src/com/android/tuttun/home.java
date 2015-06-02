package com.android.tuttun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class home extends Activity {
    /** Called when the activity is first created. */

        /** Called when the activity is first created. */
    	 private final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;

    	   /** Called when the activity is first created. */
    	   @Override
    	    public void onCreate(Bundle savedInstanceState) {
    	       super.onCreate(savedInstanceState);

    	        // レイアウトを作成
    	        LinearLayout layout = new LinearLayout(this);
    	        layout.setOrientation(LinearLayout.VERTICAL);
    	        layout.setGravity(Gravity.CENTER_HORIZONTAL);
    	        setContentView(layout);

    	        //テキスト表示
    	       TextView tv = new TextView(this);
    	        tv.setText("");
    	        tv.setTextSize(20.0f);
    	        layout.addView(tv,
    	         new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

    	        //テキスト表示
    	       TextView tv1 = new TextView(this);
    	        tv1.setText("TUTTUN     ");
    	        tv1.setTextSize(46.0f);
    	        layout.addView(tv1,
    	         new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

    	        //テキスト表示
    	       TextView tv2 = new TextView(this);
    	        tv2.setText("      TOWER");
    	        tv2.setTextSize(46.0f);
    	        layout.addView(tv2,
    	         new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));


    	        LinearLayout buttonLayout = new LinearLayout(this);
    	        buttonLayout.setOrientation(1);

    	        LinearLayout.LayoutParams layoutParams =
    	            new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
    	        layoutParams.setMargins(0, 50, 0, 0);
    	        layout.addView(buttonLayout, layoutParams);


    	        //ボタン表示
    	        Button button1 = new Button(this);
    	        button1.setText("START");
    	        button1.setWidth(150);
    	        button1.setTextSize(24.0f);
    	        button1.setOnClickListener(new OnClickListener(){
    	        	public void onClick(View arg0) {
    	        		Button1_OnClick();
    				}
    	        });
    	        buttonLayout.addView(button1,
    	                new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
    	        
    	        
    	        Button button2 = new Button(this);
    	        button2.setText("RULE");
    	        button2.setWidth(150);
    	        button2.setTextSize(24.0f);
    	        button2.setOnClickListener(new OnClickListener(){
    	        	public void onClick(View arg0) {
    	        		Button2_OnClick();
    				}
    	        });
    	        buttonLayout.addView(button2,
    	                new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));


    	        


    	        Button button3 = new Button(this);
    	        button3.setText("OPTION");
    	        button3.setWidth(150);
    	        button3.setTextSize(24.0f);
    	        button3.setOnClickListener(new OnClickListener(){
    	        	public void onClick(View arg0) {
    	        		Button3_OnClick();
    				}
    	        });
    	        buttonLayout.addView(button3,
    	                new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
    	        
    	        Button button4 = new Button(this);
    	        button4.setText("Staff");
    	        button4.setWidth(150);
    	        button4.setTextSize(24.0f);
    	        button4.setOnClickListener(new OnClickListener(){
    	        	public void onClick(View arg0) {
    	        		Button4_OnClick();
    				}
    	        });
    	        buttonLayout.addView(button4,
    	                new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
    	    }
    	   
    	   
    	   //ボタンのタッチイベントの指定（リンク先の指定）
    	   private void Button1_OnClick(){
    		   Intent intent = new Intent();

    	       intent.setClassName(
    	               "com.android.tuttun",
    	               "com.android.tuttun.game_mode");
    	       startActivity(intent);

    	       //finish();
    	   }
    	   private void Button2_OnClick(){
    		   Intent intent = new Intent();

    	       intent.setClassName(
    	               "com.android.tuttun",
    	               "com.android.tuttun.Rule");
    	       startActivity(intent);

    	       //finish();
    	   }

    	   private void Button3_OnClick(){
    		   Intent intent = new Intent();

    	       intent.setClassName(
    	               "com.android.tuttun",
    	               "com.android.tuttun.Option");
    	       startActivity(intent);

    	       //finish();
    	   }
    	   
    	   private void Button4_OnClick(){
    		   Intent intent = new Intent();

    	       intent.setClassName(
    	               "com.android.tuttun",
    	               "com.android.tuttun.Staff");
    	       startActivity(intent);

    	       //finish();
    	   }


}