package com.android.tuttun;


import java.util.Timer;
import java.util.TimerTask;

//import pbl.app.com.MyView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class PBLAppView extends Activity {
	/** ��ʕ`��p View */
    private MyView view;
    /** Timer �����p�̃n���h�� */
    android.os.Handler handler = new android.os.Handler();
    /** Activity���������ꂽ���ɌĂ΂�� */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	int mode = 0;
    	Intent intent = getIntent();
    	String data = intent.getStringExtra("mode");
    	if (data.equals("1")){
    		mode = 1;
    	}
        super.onCreate(savedInstanceState);
        // �^�C�g���o�[�̍폜  
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        // �X�e�[�^�X�o�[�폜  
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);  
        // View �̐ݒ�
        view = new MyView(getApplication(),mode);
        //setContentView(view);
        setContentView(new MyView(this, mode));
        // Timer �̐ݒ������
        /*
        Timer timer = new Timer(false);
        timer.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        view.invalidate();
                    }
                });
            }
        },0, 10);
        */
    }
    
    /** 
     * �I�v�V�������j���[��Create���� 
     */  
    /** ���j���[ID */  
    private final int MENU_ID1 = Menu.FIRST;  
    private final int MENU_ID2 = Menu.FIRST + 1; 
    private final int MENU_ID3 = Menu.FIRST + 2; 
    private final int MENU_ID4 = Menu.FIRST + 3; 
  
    @Override  
    public boolean onCreateOptionsMenu(Menu menu) {  
        super.onCreateOptionsMenu(menu);  
        // ���j���[�A�C�e����ǉ�  
        MenuItem item0 = menu.add(0, MENU_ID1, 0, /*R.string.menu_end*/"�Q�[���I��");  
        // �ǉ��������j���[�A�C�e���̃A�C�R����ݒ�  
        item0.setIcon(android.R.drawable.ic_menu_close_clear_cancel); 
        
        // ���j���[�A�C�e����ǉ�  
        MenuItem item1 = menu.add(0, MENU_ID2, 0, /*R.string.menu_end*/"���Z�b�g");  
        // �ǉ��������j���[�A�C�e���̃A�C�R����ݒ�  
        item1.setIcon(android.R.drawable.ic_menu_revert);  
        
        // ���j���[�A�C�e����ǉ�  
        MenuItem item3 = menu.add(0, MENU_ID3, 0, /*R.string.menu_end*/"�w���v");  
        // �ǉ��������j���[�A�C�e���̃A�C�R����ݒ�  
        item3.setIcon(android.R.drawable.ic_menu_help);  
        
     	// ���j���[�A�C�e����ǉ�  
        MenuItem item4 = menu.add(0, MENU_ID4, 0, /*R.string.menu_end*/"DebugMode");  
        // �ǉ��������j���[�A�C�e���̃A�C�R����ݒ�  
        item4.setIcon(android.R.drawable.ic_menu_view);  
        
        return true;  
    }  
  
    /** 
     * �I�v�V�������j���[���N���b�N���ꂽ�Ƃ��̓��� 
     */  
    @Override  
    public boolean onOptionsItemSelected(MenuItem item) {  
        switch (item.getItemId()) {  
        case MENU_ID1:  
            // �e�{�^���������ꂽ�Ƃ��̏������L�q  
            Toast toast = Toast  
                    .makeText(this, "�Q�[���I��", Toast.LENGTH_LONG);  
            toast.show();  
           
            finish(); 
            
            Intent intent = null;
            intent = new Intent(this,home.class);
            //startActivity(intent);
            return true;  
              
        case MENU_ID2:  
            // �e�{�^���������ꂽ�Ƃ��̏������L�q  
            toast = Toast  
                    .makeText(this, "�Q�[�����Z�b�g", Toast.LENGTH_LONG);  
            toast.show();  
            //finish();  
            view.gameReset();
            return true;  
        case MENU_ID3:  
            // �e�{�^���������ꂽ�Ƃ��̏������L�q  
            toast = Toast  
                    .makeText(this, "���[��������", Toast.LENGTH_LONG);  
            toast.show();  
            //finish();  
            intent = new Intent(this,Rule.class);
            startActivity(intent);
            return true;  
        case MENU_ID4:  
        	/*
            // �e�{�^���������ꂽ�Ƃ��̏������L�q  
            toast = Toast  
                    .makeText(this, "DebugMode�ɓ���܂�", Toast.LENGTH_LONG);  
            toast.show();  
            //finish();  
            view.debugMode();*/
        	final CharSequence[] items = {"Debug","�߂Â��Ă���","����܂���"};


        	new AlertDialog.Builder(PBLAppView.this)
        	.setTitle("���[�h��I��")
        	.setItems(items, new DialogInterface.OnClickListener() {
        	    public void onClick(DialogInterface dialog, int item) {
        	    	//button.setText(String.format("%s���I������܂����B",items[item]));
        	    	view.debugMode(item);
        	    }
        	})
        	.show();

        	
            return true;  
        }  
        
        return true;  
    }  
    
}