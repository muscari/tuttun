package com.android.tuttun;

import java.util.Calendar;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View{
	
	final static int S = 7;
	final static int T = 8;
	int dispWidth;
	int dispHeight;
	int touchX;
	int touchY;
	int touchState = MotionEvent.ACTION_CANCEL;
	int oldTouchState;
	int beforeS, beforeT;
	private Canvas mCanvas = new Canvas();
	private Area area = new Area();
	private static GameControl gc = new GameControl();
	private AlertDialog alertDialog = new AlertDialog.Builder(this.getContext()).create();
	private AlertDialog endDialog = new AlertDialog.Builder(this.getContext()).create();
	Intent Intent = null;
	int aiFlag = 0;
	int gameMode = 0;
	
	// View の初期化
    public MyView(Context context, int mode) {
        super(context);
        //gc.reset();
        //setFocusable(true);
        gameMode = mode;
    }

	/**
     * Viewのサイズが変更された時
     * setContentView()の時に呼び出される
     */
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        dispWidth = w;
        dispHeight = h;
        area.makeArea(w, h);
        
    }
    
    /*
     * 実際に描画を行うメソッド(non-Javadoc)
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    protected void onDraw(Canvas canvas) {
    	Bitmap mBitmap = Bitmap.createBitmap(dispWidth, dispHeight,Bitmap.Config.RGB_565);
		mCanvas.setBitmap(mBitmap);
		mCanvas.drawColor(Color.WHITE);
		
		/*
		if(gc.getPlayer() == 1){
			aiFlag++;
		}*/
		if(gameMode==1){
			if(gc.getPlayer() == 1 && aiFlag == 0){
				gc.aiStart();
				//gc.aiStart2();
				aiFlag = 1;
			}else if(gc.getPlayer() == 1 && aiFlag == 1){
				//gc.aiStart();
				gc.aiStart2();
				aiFlag = 2;
			}
			if(gc.getPlayer() == -1){
				aiFlag = 0;
			}
		}
		
		/***********************************
		 *
		 * ろじっくステータスにかかわらず描画を行う部分
		 * 
		 ***********************************/
		//マス とコマ
		tileDraw(mCanvas);
		//マス以外のオプション
		optDraw(mCanvas);
		//駒を書く
		//komaDraw(mCanvas);
		
		
    	/****************************
    	 * 
    	 * ロジックのステータスと連動して動かす部分
    	 * 
    	 *****************************/
		int logicStatus = gc.getLogicStatus();
    	if(logicStatus==0){
    		//移動駒選択待ち画面

    	}else if(logicStatus==1){
    		//移動駒選択済み
    		//移動先選択待ち
    		tileBlack(mCanvas);
    	}else if(logicStatus==2){
    		//アラーとダイアログを表示して
    		//つっつんか乗るかを選択
    		if(aiFlag == 0){
    			commandDialog();
    		}else{
    			if(gc.getPlayer() == -1){
	    			commandDialog();
	    		}
    		}
    	}else if(logicStatus==10){
    		endDialog("黒");
    	}else if(logicStatus==11){
    		endDialog("白");
    	}
    	super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        
        invalidate();
        
        touchState = MotionEvent.ACTION_CANCEL;
    }
    
    public void endDialog(String string) {
		// TODO Auto-generated method stub
    	// 表示されてたら何もしない
        if (endDialog.isShowing()) {
            return;
        }
    	//AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
        // アラートダイアログのタイトルを設定します
        endDialog.setTitle("GameSet!");
        // アラートダイアログのメッセージを設定します
        endDialog.setMessage(string+"の勝ちです!");
        // アラートダイアログの肯定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
        endDialog.setButton("終了",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    	//setCommand(1);
                    	gameReset();
                    }
                });
        endDialog.setButton2("もう一度",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    	//setCommand(1);
                    	gameReset();
                    }
                });
        // アラートダイアログのキャンセルが可能かどうかを設定します
        endDialog.setCancelable(false);
        //alertDialog = alertDialog.create();
        // アラートダイアログを表示します
        endDialog.show();
	}

	public void commandDialog(){
    	// 表示されてたら何もしない
		
        if (alertDialog.isShowing()) {
            return;
        }
        
    	//AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
        // アラートダイアログのタイトルを設定します
        alertDialog.setTitle("Set Command");
        // アラートダイアログのメッセージを設定します
        alertDialog.setMessage("コマンドを選択してください");
        // アラートダイアログの肯定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
        alertDialog.setButton("のる",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    	setCommand(1);
                    }
                });
        // アラートダイアログの否定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
        alertDialog.setButton2("つっつん",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    	setCommand(2);
                    }
                });
        // アラートダイアログのキャンセルが可能かどうかを設定します
        alertDialog.setCancelable(false);
        //alertDialog = alertDialog.create();
        // アラートダイアログを表示します
        alertDialog.show();
    }
	
    private void tileBlack(Canvas canvas) {
		// TODO Auto-generated method stub
    	Paint paint = new Paint();
		paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.argb(180, 10, 10, 10));
        // 四角形を適当な位置に描画する
        for(int s=0;s<S;s++){
        	for(int t=0;t<T;t++){
        		if(gc.getMovingArea(s, t)==0){
        			canvas.drawRect(area.getTileArea(s, t), paint);
        		}
        	}
        }
	}

	/***
     * 駒を書く
     * @param canvas
     */
    private void komaDraw(Canvas canvas){
    	/*
    	Paint paint = new Paint();
		paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        */
    	for(int s=0;s<S;s++){
    		for(int t=0;t<T;t++){
    			if(gc.getBoardStatus(s, t)!=0){
    				//canvas.drawCircle(area.getKomaPoint(s, t)[0], area.getKomaPoint(s, t)[1], area.getKomaPoint(s, t)[2]/3, paint);
    				koma(canvas,s,t,gc.getBoardStatus(s, t));
    			}
    		}
    	}
    }
    
    /***
     * @param canvas
     * @param s
     * @param t
     * @param state
     */
    protected void koma(Canvas canvas, int s, int t, int state)
	{
		Paint pGray = new Paint(Paint.ANTI_ALIAS_FLAG);
		pGray.setColor(Color.rgb(50, 50, 50));
		pGray.setStyle(Paint.Style.STROKE);
		pGray.setStrokeWidth(1);
		
		Paint pColor = new Paint(Paint.ANTI_ALIAS_FLAG);
		pColor.setStyle(Paint.Style.FILL);
		if(state > 0){
			pColor.setColor(Color.rgb(150,40,0));		//black
		}else if(state < 0){
			pColor.setColor(Color.rgb(255, 240, 210));	//white
		}
		
		int x = area.getKomaPoint(s, t)[0];
		int y = area.getKomaPoint(s, t)[1];
		int r = area.getKomaPoint(s, t)[2]/3;
		
		if(state == 0){
			//何も書かかない
		}
		else if(Math.abs(state) == 1){
			canvas.drawCircle(x,y,r,pColor);
			canvas.drawCircle(x,y,r,pGray);
		}
		else if(Math.abs(state) == 2){
			canvas.drawCircle(x,y,r,pColor);
			canvas.drawCircle(x,y,r,pGray);
			
			canvas.drawCircle(x,y-6,r,pColor);
			canvas.drawCircle(x,y-6,r,pGray);
		}
		else if(Math.abs(state) == 3){
			canvas.drawCircle(x,y,r,pColor);
			canvas.drawCircle(x,y,r,pGray);
			
			canvas.drawCircle(x,y-6,r,pColor);
			canvas.drawCircle(x,y-6,r,pGray);
			
			canvas.drawCircle(x,y-12,r,pColor);
			canvas.drawCircle(x,y-12,r,pGray);
		}
	}
    
    /******
     * タイルとボードを描画
     ******/
	private void tileDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		//背景
		Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.back); 
		int h = mBitmap.getHeight();
		int w= mBitmap.getWidth();
		// 描画元の矩形イメージ
		Rect src = new Rect(0, 0, w, h);
		// 描画先の矩形イメージ
		//Rect dst = new Rect(0,0,dispWidth,dispHeight);
		Rect dst = area.getOptArea();
		canvas.drawBitmap(mBitmap, src, dst,  null);
		
		//アイコン
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tuttunfin);
		h = mBitmap.getHeight();
		w= mBitmap.getWidth();
		// 描画元の矩形イメージ
		src = new Rect(0, 0, w, h);
		// 描画先の矩形イメージ
		//Rect dst = new Rect(0,0,dispWidth,dispHeight);
		RectF dst2 = area.getOptButtonArea();
		canvas.drawBitmap(mBitmap, src, dst2,  null);
		
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tree);     
		h = mBitmap.getHeight();
		w= mBitmap.getWidth();
		// 描画元の矩形イメージ
		src = new Rect(0, 0, w, h);
		// 描画先の矩形イメージ
		dst = area.getBoardArea();
		canvas.drawBitmap(mBitmap, src, dst,  null);

		
		Paint paint = new Paint();
		paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        // 四角形を適当な位置に描画する
        for(int s=0;s<S;s++){
        	for(int t=0;t<T;t++){
                canvas.drawRect(area.getTileArea(s, t), paint);
                koma(canvas,s,t,gc.getBoardStatus(s, t));
                
                //デバッグ用表示↓
                //centeringText(canvas, gc.getBoardStatus(s, t)+"", 20, area.getTileArea(s, t));
                
                //touchについての処理(タッチされたマスが赤くなる)
                if(gc.getLogicStatus()!=3){
                if(touchState==MotionEvent.ACTION_UP/*||touchState==MotionEvent.ACTION_MOVE*/){
                	
                	//タッチされた状態でかつ
                	if(areaCheck(area.getTileArea(s, t), touchX, touchY)){
                		//場所があっているとき
                		// 描画オブジェクトの生成
                        Paint fillBox = new Paint();
                        fillBox.setAntiAlias(true);
                        fillBox.setStyle(Paint.Style.FILL);
                        fillBox.setColor(Color.RED);
                		canvas.drawRect(area.getTileArea(s, t), fillBox);
                		//tileクリック
                		gc.tileClick(s, t);
                		beforeS = s;
                		beforeT	= t;
                	}
                }}
        	}
        }
	}
	
	/****
	 * ボード以外の情報を描画
	 */
	private void optDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Paint paint = new Paint();
		paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        // 四角形を適当な位置に描画する
        
        //canvas.drawRect(area.getOptButtonArea(), paint);	//optボタン
        //centeringText(canvas, touchState+""/*"Option"*/, 20, area.getOptButtonArea());
        //canvas.drawRect(area.getTurnArea(), paint);			//ターン表示
        String a = "茶の番です";
        if(gc.getPlayer()==-1){
        	a = "白の番です";
        }
        
        Calendar calendar1 = Calendar.getInstance();
        long time = gc.getPlayTime(calendar1.getTimeInMillis())/1000;
		long s = time % 60;
		time = time / 60;
		long m = time % 60;
        centeringText(canvas, a, 25, area.getTurnArea());
        //canvas.drawRect(area.getTimeArea(), paint);			//時間表示
        //String b = "logic"+gc.getLogicStatus();
        String b;
        if(s/10 == 0){
        	b = "Time  " + m + ":0" + s;
        }else{
        	b = "Time  " + m + ":" + s;
        }
        centeringText(canvas, b, 25, area.getTimeArea());
	}
	
	/**
	 * textをセンタリングして表示
	 * @param canvas
	 * @param text
	 * @param size
	 * @param rect
	 */
	protected void centeringText(Canvas canvas, String text, int size, RectF rect){
		// 中心座標
        float centerX = (rect.right+rect.left)/2;
        float centerY = (rect.bottom+rect.top)/2;
        // テキスト用ペイントの生成
        Paint textPaint = new Paint( Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(size);
        textPaint.setColor( Color.BLACK);
        FontMetrics fontMetrics = textPaint.getFontMetrics();
        // 文字列の幅を取得
        float textWidth = textPaint.measureText( text);
        // 中心にしたいX座標から文字列の幅の半分を引く
        float baseX = centerX - textWidth / 2;
        // 中心にしたいY座標からAscentとDescentの半分を引く
        float baseY = centerY - (fontMetrics.ascent + fontMetrics.descent) / 2;
        // テキストの描画
        canvas.drawText( text, baseX, baseY, textPaint);
	}
	
	/**
	 * タッチイベントを処理
	 **/
    public boolean onTouchEvent(MotionEvent event) {
        //Point p = new Point();
    	touchState = event.getAction();
        touchX = (int)event.getX();
        touchY = (int)event.getY();
        oldTouchState = touchState;
        return true;
    }
	
    /******
     * 範囲をチェックする
     ******/
    public boolean areaCheck(RectF area, int touchX, int touchY){
    	if(touchX>area.left&&touchX<area.right&&touchY>area.top&&touchY<area.bottom){

    		return true;
    	}else{
    		return false;
    	}
    }
    /*protected void dialog(Canvas canvas){
    	blackOut(mCanvas);
    	messageBox(mCanvas);
    }
    /***
     * 画面を暗くする
     * @param canvas
     */
    protected void blackOut(Canvas canvas){
    	// 描画オブジェクトの生成
        Paint pause = new Paint();
        pause.setAntiAlias(true);
        pause.setStyle(Paint.Style.FILL);
        pause.setColor(Color.argb(180, 10, 10, 10));
        
        canvas.drawRect(0,0,dispWidth,dispHeight, pause);
    }
    
    private void setCommand(int a){
    	gc.setCommand(a);
    	gc.tileClick(beforeS, beforeT);
    }
    
    public void gameReset(){
    	gc.reset();
    }


	public void debugMode(int item) {
		// TODO Auto-generated method stub
		
		gc.debugMode(item);
	}
}
