package com.android.tuttun;

import android.graphics.Rect;
import android.graphics.RectF;

public class Area {
	final static int S = 7;
	final static int T = 8;
	
	int tileBorderLineX[] = new int[S+1];
	int tileBorderLineY[] = new int[T+1];
	RectF optButtonRectF;
	RectF turnRectF;
	RectF timeRectF;
	Rect optRectF;
	
	/*************
	 * 
	 * 画面でのエリア設定を行う
	 * 
	 **************/
	void makeArea(int dWidth, int dHeight){
		int tileSize;			//tileのサイズ
		int optButtonH = 150;	//オプションボタンサイズ
		int optButtonW = 150;	
		int turnH = 70;			//ターンを表示
		int turnW = 150;	
		int timeH = 70;			//時間を表示
		int timeW = 150;	
		
		if(dWidth>dHeight){		//横長画面の設定
			int margine = (dHeight % T)/2;
			tileSize = dHeight / T;
			for(int s=0;s<S+1;s++){
				tileBorderLineX[s] = margine + tileSize*s;
			}
			for(int t=0;t<T+1;t++){
				tileBorderLineY[t] = margine + tileSize*t;
			}
			
			//オプションボタン，ターン表示，時間が縦に三分割されるようにマージンを設定
			margine = (dHeight - optButtonH - turnH - timeH)/4;
			int buttom = margine+optButtonH;
			int sideMargine = getBoardArea().right+(dWidth-optButtonW-getBoardArea().right)/2;
			optButtonRectF = new RectF(sideMargine,margine,sideMargine + optButtonW,buttom);
			buttom = buttom + margine;
			sideMargine = getBoardArea().right+(dWidth-turnW-getBoardArea().right)/2;
			turnRectF = new RectF(sideMargine,buttom,sideMargine+turnW,buttom+turnH);
			buttom = buttom + turnH+ margine;
			sideMargine = getBoardArea().right+(dWidth-timeW-getBoardArea().right)/2;
			timeRectF = new RectF(sideMargine,buttom,sideMargine+timeW,buttom+timeH);
			
			optRectF = new Rect(getBoardArea().right+10,0,dWidth,dHeight);
			
		}else{					//縦長画面の設定
			int margine = (dWidth % S)/2;
			tileSize = dWidth / S;
			for(int s=0;s<S+1;s++){
				tileBorderLineX[s] = margine + tileSize*s;
			}
			for(int t=0;t<T+1;t++){
				tileBorderLineY[t] = margine + tileSize*t;
			}
			
			
			//オプションボタン，ターン表示，時間が横に２分割されるようにマージンを設定
			margine = (dWidth - optButtonW -timeW)/3;
			int hmargine = (dHeight-getBoardArea().bottom-timeH-turnH)/3;
			int top = dHeight-2*hmargine-timeH-turnH;
			timeRectF = new RectF(margine,top,margine+timeW,top+timeH);
			top = top + hmargine+timeH;
			turnRectF = new RectF(margine,top,margine+turnW,top+turnH);
			
			hmargine = (dHeight-getBoardArea().bottom-optButtonH)/2;
			top = dHeight-hmargine-optButtonH;
			optButtonRectF = new RectF(margine*2+timeW,top,dWidth-margine,top+optButtonH);
			
			optRectF = new Rect(0,getBoardArea().bottom + 10,dWidth,dHeight);
			
		}
	}
	
	RectF getOptButtonArea(){
		return optButtonRectF;
	}
	RectF getTurnArea(){
		return turnRectF;
	}
	RectF getTimeArea(){
		return timeRectF;
	}
	Rect getOptArea(){
		return optRectF;
	}
	
	int[] getKomaPoint(int s, int t){
		int a[] = new int[3];
		a[0]=(tileBorderLineX[s]+tileBorderLineX[s+1])/2;
		a[1]=(tileBorderLineY[t]+tileBorderLineY[t+1])/2;
		a[2]=-tileBorderLineX[s]+tileBorderLineX[s+1];
		return a;
	}
	RectF getTileArea(int s, int t){
		int left = tileBorderLineX[s];
		int top = tileBorderLineY[t];
		int right = tileBorderLineX[s+1];
		int bottom = tileBorderLineY[t+1];
		RectF rectf = new RectF(left, top, right, bottom);
		return rectf;
	}
	Rect getBoardArea(){
		int left = tileBorderLineX[0];
		int top = tileBorderLineY[0];
		int right = tileBorderLineX[S];
		int bottom = tileBorderLineY[T];
		Rect rect = new Rect(left, top, right, bottom);
		return rect;
	}
}