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
	       
	       // ���C�A�E�g���쐬   
	       LinearLayout layout1 = new LinearLayout(this);   
	       layout1.setOrientation(LinearLayout.VERTICAL); 
	       layout1.setGravity(Gravity.CENTER_HORIZONTAL);
	       setContentView(layout1);

	       LinearLayout layout2 = new LinearLayout(this);   
	       layout2.setOrientation(LinearLayout.HORIZONTAL); 
	       layout2.setGravity(Gravity.CENTER_HORIZONTAL);
	       
	        //�e�L�X�g�\��
	       TextView tv1 = new TextView(this);
	        tv1.setText("Rule(�E�ցE��)");
	        tv1.setTextSize(20.0f);
	        layout1.addView(tv1,
	         new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
	        
		   TextView tv2 = new TextView(this);
		    tv2.setText("");
		    tv2.setTextSize(10.0f);
		    layout1.addView(tv2,
		     new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
	        
		   TextView tv3 = new TextView(this);
		   tv3.setText("���������F���Ԃ��R�ςݏグ���珟������");
		    tv3.setTextSize(15.0f);
		    layout1.addView(tv3,
		     new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
		    
		   TextView tv4 = new TextView(this);
		   	tv4.setText("");
		   	tv4.setTextSize(10.0f);
		   	layout1.addView(tv4,
		   	 new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

		   TextView tv5 = new TextView(this);
		    tv5.setText("�s����1.�ړ� 2.TUTTUN 3.���̂ǂꂩ�P�ł��B�G�̃^�[���ƃv���[���[�̃^�[���͌��݂ɍs���܂��B\n");
		    tv5.setTextSize(13.0f);
		    layout1.addView(tv5,
		     new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
		    
		   TextView tv6 = new TextView(this);
		  	tv6.setText("1.�ړ�");
			tv6.setTextSize(15.0f);
			layout1.addView(tv6,
		 	 new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

		   TextView tv7 = new TextView(this);
			tv7.setText("1�^�[���ŁA�����̃R�}�̂ǂꂩ1���u�^�e�E���R�E�i�i���v�Ɉړ����邱�Ƃ��ł��܂��B\n");
			tv7.setTextSize(13.0f);
			layout1.addView(tv7,
			 new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
			
			   TextView tv8 = new TextView(this);
			  	tv8.setText("2.TUTTUN");
				tv8.setTextSize(15.0f);
				layout1.addView(tv8,
			 	 new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

			   TextView tv9 = new TextView(this);
				tv9.setText("�s���͈͓��ł���΁A�ǂ̕����ɂ�TUTTUN���邱�Ƃ��ł��܂��B�����̃R�}������̃R�}��TUTTUN����ꍇ�ATUTTUN�����R�}�Ƃ��ꂽ�R�}�́ATUTTUN���������Ɣ��΂̕����Ɍ݂��ɂQ�}�X�ړ����܂��B����́A�������m�ł��\�ł��B\n�������A�R�}����΂��������ɕǂ�R�}�����݂���ꍇ�A��΂����R�}�͕ǂ�R�}�����݂��鏊�ňړ����I�����܂��B\n");
				tv9.setTextSize(13.0f);
				layout1.addView(tv9,
				 new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

				TextView tv10 = new TextView(this);
			  	tv10.setText("3.���");
				tv10.setTextSize(15.0f);
				layout1.addView(tv10,
			 	 new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

			   TextView tv11 = new TextView(this);
				tv11.setText("���Ƃ����R�}���h�͒��ԓ��m�̂ݑI���\�ł��B�R�}�̍s���͈͓��Ȃ�΁A�ǂ̕����ɂ���邱�Ƃ��ł��܂��B\n");
				tv11.setTextSize(13.0f);
				layout1.addView(tv11,
				 new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
				
				
		        layout1.addView(layout2, createParam(WRAP_CONTENT, WRAP_CONTENT));

		        
			/*
	       //�{�^���\��   
	       Button button1 = new Button(this);
	       
	       button1.setText("����");
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

	       //�{�^���\��   
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

