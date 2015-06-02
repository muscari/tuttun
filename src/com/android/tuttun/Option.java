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

public class Option extends Activity {
	
	private final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT; 
	
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
			tv1.setText("Option(・ω・○)\n\n\n\n\n");
			tv1.setTextSize(20.0f);
			layout1.addView(tv1,
					new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

			TextView tv2 = new TextView(this);
			tv2.setText("Option(・ω・○)\n\n\n\n\n");
			tv2.setTextSize(20.0f);
			layout1.addView(tv2,
					new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
			

	       //ボタン表示   
	       Button button1 = new Button(this);
	       button1.setText("HOME");
	       button1.setWidth(80);
		   button1.setHeight(10);
	       button1.setTextSize(10.0f);
	       layout1.addView(button1,    
	        new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
	       button1.setOnClickListener(new OnClickListener(){
	       	public void onClick(View arg0) {
	       		Button1_OnClick();		
				}
	       });   
	       
		}
		
		   private void Button1_OnClick(){ 
			   Intent intent = new Intent();
			   
		       intent.setClassName(
		               "com.android.tuttun",
		               "com.android.tuttun.home");
		       startActivity(intent);
		       
		       finish();

		   }
		

}
