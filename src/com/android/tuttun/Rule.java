package com.android.tuttun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Rule extends Activity {

	private final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT; 
	
	@Override     
    public void onCreate(Bundle savedInstanceState) {     
	       super.onCreate(savedInstanceState);
	       
	       // レイアウトを作成   
	       LinearLayout layout1 = new LinearLayout(this);   
	       layout1.setOrientation(LinearLayout.VERTICAL); 
	       layout1.setGravity(Gravity.CENTER_HORIZONTAL);
	       setContentView(layout1);

	       LinearLayout layout2 = new LinearLayout(this);   
	       layout2.setOrientation(LinearLayout.HORIZONTAL); 
	       layout2.setGravity(Gravity.CENTER_HORIZONTAL);
	       
	        //テキスト表示
	       TextView tv1 = new TextView(this);
	        tv1.setText("Rule(・ω・○)");
	        tv1.setTextSize(20.0f);
	        layout1.addView(tv1,
	         new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
	        
		   TextView tv2 = new TextView(this);
		    tv2.setText("");
		    tv2.setTextSize(10.0f);
		    layout1.addView(tv2,
		     new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
	        
		   TextView tv3 = new TextView(this);
		   tv3.setText("勝利条件：仲間を３つ積み上げたら勝ち☆★");
		    tv3.setTextSize(15.0f);
		    layout1.addView(tv3,
		     new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
		    
		   TextView tv4 = new TextView(this);
		   	tv4.setText("");
		   	tv4.setTextSize(10.0f);
		   	layout1.addView(tv4,
		   	 new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

		   TextView tv5 = new TextView(this);
		    tv5.setText("行動は1.移動 2.TUTTUN 3.乗るのどれか１つです。敵のターンとプレーヤーのターンは交互に行います。\n");
		    tv5.setTextSize(13.0f);
		    layout1.addView(tv5,
		     new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
		    
		   TextView tv6 = new TextView(this);
		  	tv6.setText("1.移動");
			tv6.setTextSize(15.0f);
			layout1.addView(tv6,
		 	 new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

		   TextView tv7 = new TextView(this);
			tv7.setText("1ターンで、自分のコマのどれか1つを「タテ・ヨコ・ナナメ」に移動することができます。\n");
			tv7.setTextSize(13.0f);
			layout1.addView(tv7,
			 new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
			
			   TextView tv8 = new TextView(this);
			  	tv8.setText("2.TUTTUN");
				tv8.setTextSize(15.0f);
				layout1.addView(tv8,
			 	 new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

			   TextView tv9 = new TextView(this);
				tv9.setText("行動範囲内であれば、どの方向にもTUTTUNすることができます。自分のコマが相手のコマにTUTTUNする場合、TUTTUNしたコマとされたコマは、TUTTUNした方向と反対の方向に互いに２マス移動します。これは、味方同士でも可能です。\nただし、コマが飛ばされる方向に壁やコマが存在する場合、飛ばされるコマは壁やコマが存在する所で移動が終了します。\n");
				tv9.setTextSize(13.0f);
				layout1.addView(tv9,
				 new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

				TextView tv10 = new TextView(this);
			  	tv10.setText("3.乗る");
				tv10.setTextSize(15.0f);
				layout1.addView(tv10,
			 	 new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

			   TextView tv11 = new TextView(this);
				tv11.setText("乗るというコマンドは仲間同士のみ選択可能です。コマの行動範囲内ならば、どの方向にも乗ることができます。\n");
				tv11.setTextSize(13.0f);
				layout1.addView(tv11,
				 new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
				
				
		        layout1.addView(layout2, createParam(WRAP_CONTENT, WRAP_CONTENT));

		        
			/*
	       //ボタン表示   
	       Button button1 = new Button(this);
	       
	       button1.setText("次へ");
	       button1.setWidth(80);
	       button1.setHeight(10);
	       button1.setTextSize(10.0f);
	       layout2.addView(button1,    
	        new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
	       button1.setOnClickListener(new OnClickListener(){
	       	public void onClick(View arg0) {
	       		Button1_OnClick();		
				}
	       });   */

	       //ボタン表示   
	       Button button2 = new Button(this);
	       button2.setText("HOME");
	       button2.setWidth(80);
	       button2.setHeight(10);
	       button2.setTextSize(10.0f);
	       layout2.addView(button2,    
	        new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
	       button2.setOnClickListener(new OnClickListener(){
	       	public void onClick(View arg0) {
	       		Button2_OnClick();		
				}
	       });   
	       
		}
		
		   private LayoutParams createParam(int wRAPCONTENT, int wRAPCONTENT2) { 
			   return new LinearLayout.LayoutParams(wRAPCONTENT, wRAPCONTENT2);

	}

		private void Button1_OnClick(){ 
			   Intent intent = new Intent();
			   
		       intent.setClassName(
		               "com.android.tuttun",
		               "com.android.tuttun.Rule2");
		       startActivity(intent);
		       
		       finish();

		   }

		   private void Button2_OnClick(){ 
			   Intent intent = new Intent();
			   /*
		       intent.setClassName(
		               "com.android.tuttun",
		               "com.android.tuttun.home");
		       startActivity(intent);
		       */
		       finish();

		   }
		
}

