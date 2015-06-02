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
	
	// View �̏�����
    public MyView(Context context, int mode) {
        super(context);
        //gc.reset();
        //setFocusable(true);
        gameMode = mode;
    }

	/**
     * View�̃T�C�Y���ύX���ꂽ��
     * setContentView()�̎��ɌĂяo�����
     */
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        dispWidth = w;
        dispHeight = h;
        area.makeArea(w, h);
        
    }
    
    /*
     * ���ۂɕ`����s�����\�b�h(non-Javadoc)
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
		 * �낶�����X�e�[�^�X�ɂ�����炸�`����s������
		 * 
		 ***********************************/
		//�}�X �ƃR�}
		tileDraw(mCanvas);
		//�}�X�ȊO�̃I�v�V����
		optDraw(mCanvas);
		//�������
		//komaDraw(mCanvas);
		
		
    	/****************************
    	 * 
    	 * ���W�b�N�̃X�e�[�^�X�ƘA�����ē���������
    	 * 
    	 *****************************/
		int logicStatus = gc.getLogicStatus();
    	if(logicStatus==0){
    		//�ړ���I��҂����

    	}else if(logicStatus==1){
    		//�ړ���I���ς�
    		//�ړ���I��҂�
    		tileBlack(mCanvas);
    	}else if(logicStatus==2){
    		//�A���[�ƃ_�C�A���O��\������
    		//���񂩏�邩��I��
    		if(aiFlag == 0){
    			commandDialog();
    		}else{
    			if(gc.getPlayer() == -1){
	    			commandDialog();
	    		}
    		}
    	}else if(logicStatus==10){
    		endDialog("��");
    	}else if(logicStatus==11){
    		endDialog("��");
    	}
    	super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        
        invalidate();
        
        touchState = MotionEvent.ACTION_CANCEL;
    }
    
    public void endDialog(String string) {
		// TODO Auto-generated method stub
    	// �\������Ă��牽�����Ȃ�
        if (endDialog.isShowing()) {
            return;
        }
    	//AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
        // �A���[�g�_�C�A���O�̃^�C�g����ݒ肵�܂�
        endDialog.setTitle("GameSet!");
        // �A���[�g�_�C�A���O�̃��b�Z�[�W��ݒ肵�܂�
        endDialog.setMessage(string+"�̏����ł�!");
        // �A���[�g�_�C�A���O�̍m��{�^�����N���b�N���ꂽ���ɌĂяo�����R�[���o�b�N���X�i�[��o�^���܂�
        endDialog.setButton("�I��",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    	//setCommand(1);
                    	gameReset();
                    }
                });
        endDialog.setButton2("������x",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    	//setCommand(1);
                    	gameReset();
                    }
                });
        // �A���[�g�_�C�A���O�̃L�����Z�����\���ǂ�����ݒ肵�܂�
        endDialog.setCancelable(false);
        //alertDialog = alertDialog.create();
        // �A���[�g�_�C�A���O��\�����܂�
        endDialog.show();
	}

	public void commandDialog(){
    	// �\������Ă��牽�����Ȃ�
		
        if (alertDialog.isShowing()) {
            return;
        }
        
    	//AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
        // �A���[�g�_�C�A���O�̃^�C�g����ݒ肵�܂�
        alertDialog.setTitle("Set Command");
        // �A���[�g�_�C�A���O�̃��b�Z�[�W��ݒ肵�܂�
        alertDialog.setMessage("�R�}���h��I�����Ă�������");
        // �A���[�g�_�C�A���O�̍m��{�^�����N���b�N���ꂽ���ɌĂяo�����R�[���o�b�N���X�i�[��o�^���܂�
        alertDialog.setButton("�̂�",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    	setCommand(1);
                    }
                });
        // �A���[�g�_�C�A���O�̔ے�{�^�����N���b�N���ꂽ���ɌĂяo�����R�[���o�b�N���X�i�[��o�^���܂�
        alertDialog.setButton2("����",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    	setCommand(2);
                    }
                });
        // �A���[�g�_�C�A���O�̃L�����Z�����\���ǂ�����ݒ肵�܂�
        alertDialog.setCancelable(false);
        //alertDialog = alertDialog.create();
        // �A���[�g�_�C�A���O��\�����܂�
        alertDialog.show();
    }
	
    private void tileBlack(Canvas canvas) {
		// TODO Auto-generated method stub
    	Paint paint = new Paint();
		paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.argb(180, 10, 10, 10));
        // �l�p�`��K���Ȉʒu�ɕ`�悷��
        for(int s=0;s<S;s++){
        	for(int t=0;t<T;t++){
        		if(gc.getMovingArea(s, t)==0){
        			canvas.drawRect(area.getTileArea(s, t), paint);
        		}
        	}
        }
	}

	/***
     * �������
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
			//�����������Ȃ�
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
     * �^�C���ƃ{�[�h��`��
     ******/
	private void tileDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		//�w�i
		Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.back); 
		int h = mBitmap.getHeight();
		int w= mBitmap.getWidth();
		// �`�挳�̋�`�C���[�W
		Rect src = new Rect(0, 0, w, h);
		// �`���̋�`�C���[�W
		//Rect dst = new Rect(0,0,dispWidth,dispHeight);
		Rect dst = area.getOptArea();
		canvas.drawBitmap(mBitmap, src, dst,  null);
		
		//�A�C�R��
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tuttunfin);
		h = mBitmap.getHeight();
		w= mBitmap.getWidth();
		// �`�挳�̋�`�C���[�W
		src = new Rect(0, 0, w, h);
		// �`���̋�`�C���[�W
		//Rect dst = new Rect(0,0,dispWidth,dispHeight);
		RectF dst2 = area.getOptButtonArea();
		canvas.drawBitmap(mBitmap, src, dst2,  null);
		
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tree);     
		h = mBitmap.getHeight();
		w= mBitmap.getWidth();
		// �`�挳�̋�`�C���[�W
		src = new Rect(0, 0, w, h);
		// �`���̋�`�C���[�W
		dst = area.getBoardArea();
		canvas.drawBitmap(mBitmap, src, dst,  null);

		
		Paint paint = new Paint();
		paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        // �l�p�`��K���Ȉʒu�ɕ`�悷��
        for(int s=0;s<S;s++){
        	for(int t=0;t<T;t++){
                canvas.drawRect(area.getTileArea(s, t), paint);
                koma(canvas,s,t,gc.getBoardStatus(s, t));
                
                //�f�o�b�O�p�\����
                //centeringText(canvas, gc.getBoardStatus(s, t)+"", 20, area.getTileArea(s, t));
                
                //touch�ɂ��Ă̏���(�^�b�`���ꂽ�}�X���Ԃ��Ȃ�)
                if(gc.getLogicStatus()!=3){
                if(touchState==MotionEvent.ACTION_UP/*||touchState==MotionEvent.ACTION_MOVE*/){
                	
                	//�^�b�`���ꂽ��Ԃł���
                	if(areaCheck(area.getTileArea(s, t), touchX, touchY)){
                		//�ꏊ�������Ă���Ƃ�
                		// �`��I�u�W�F�N�g�̐���
                        Paint fillBox = new Paint();
                        fillBox.setAntiAlias(true);
                        fillBox.setStyle(Paint.Style.FILL);
                        fillBox.setColor(Color.RED);
                		canvas.drawRect(area.getTileArea(s, t), fillBox);
                		//tile�N���b�N
                		gc.tileClick(s, t);
                		beforeS = s;
                		beforeT	= t;
                	}
                }}
        	}
        }
	}
	
	/****
	 * �{�[�h�ȊO�̏���`��
	 */
	private void optDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Paint paint = new Paint();
		paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        // �l�p�`��K���Ȉʒu�ɕ`�悷��
        
        //canvas.drawRect(area.getOptButtonArea(), paint);	//opt�{�^��
        //centeringText(canvas, touchState+""/*"Option"*/, 20, area.getOptButtonArea());
        //canvas.drawRect(area.getTurnArea(), paint);			//�^�[���\��
        String a = "���̔Ԃł�";
        if(gc.getPlayer()==-1){
        	a = "���̔Ԃł�";
        }
        
        Calendar calendar1 = Calendar.getInstance();
        long time = gc.getPlayTime(calendar1.getTimeInMillis())/1000;
		long s = time % 60;
		time = time / 60;
		long m = time % 60;
        centeringText(canvas, a, 25, area.getTurnArea());
        //canvas.drawRect(area.getTimeArea(), paint);			//���ԕ\��
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
	 * text���Z���^�����O���ĕ\��
	 * @param canvas
	 * @param text
	 * @param size
	 * @param rect
	 */
	protected void centeringText(Canvas canvas, String text, int size, RectF rect){
		// ���S���W
        float centerX = (rect.right+rect.left)/2;
        float centerY = (rect.bottom+rect.top)/2;
        // �e�L�X�g�p�y�C���g�̐���
        Paint textPaint = new Paint( Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(size);
        textPaint.setColor( Color.BLACK);
        FontMetrics fontMetrics = textPaint.getFontMetrics();
        // ������̕����擾
        float textWidth = textPaint.measureText( text);
        // ���S�ɂ�����X���W���當����̕��̔���������
        float baseX = centerX - textWidth / 2;
        // ���S�ɂ�����Y���W����Ascent��Descent�̔���������
        float baseY = centerY - (fontMetrics.ascent + fontMetrics.descent) / 2;
        // �e�L�X�g�̕`��
        canvas.drawText( text, baseX, baseY, textPaint);
	}
	
	/**
	 * �^�b�`�C�x���g������
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
     * �͈͂��`�F�b�N����
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
     * ��ʂ��Â�����
     * @param canvas
     */
    protected void blackOut(Canvas canvas){
    	// �`��I�u�W�F�N�g�̐���
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
