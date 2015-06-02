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
	/** 画面描画用 View */
    private MyView view;
    /** Timer 処理用のハンドラ */
    android.os.Handler handler = new android.os.Handler();
    /** Activityが生成された時に呼ばれる */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	int mode = 0;
    	Intent intent = getIntent();
    	String data = intent.getStringExtra("mode");
    	if (data.equals("1")){
    		mode = 1;
    	}
        super.onCreate(savedInstanceState);
        // タイトルバーの削除  
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        // ステータスバー削除  
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);  
        // View の設定
        view = new MyView(getApplication(),mode);
        //setContentView(view);
        setContentView(new MyView(this, mode));
        // Timer の設定をする
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
     * オプションメニューをCreateする 
     */  
    /** メニューID */  
    private final int MENU_ID1 = Menu.FIRST;  
    private final int MENU_ID2 = Menu.FIRST + 1; 
    private final int MENU_ID3 = Menu.FIRST + 2; 
    private final int MENU_ID4 = Menu.FIRST + 3; 
  
    @Override  
    public boolean onCreateOptionsMenu(Menu menu) {  
        super.onCreateOptionsMenu(menu);  
        // メニューアイテムを追加  
        MenuItem item0 = menu.add(0, MENU_ID1, 0, /*R.string.menu_end*/"ゲーム終了");  
        // 追加したメニューアイテムのアイコンを設定  
        item0.setIcon(android.R.drawable.ic_menu_close_clear_cancel); 
        
        // メニューアイテムを追加  
        MenuItem item1 = menu.add(0, MENU_ID2, 0, /*R.string.menu_end*/"リセット");  
        // 追加したメニューアイテムのアイコンを設定  
        item1.setIcon(android.R.drawable.ic_menu_revert);  
        
        // メニューアイテムを追加  
        MenuItem item3 = menu.add(0, MENU_ID3, 0, /*R.string.menu_end*/"ヘルプ");  
        // 追加したメニューアイテムのアイコンを設定  
        item3.setIcon(android.R.drawable.ic_menu_help);  
        
     	// メニューアイテムを追加  
        MenuItem item4 = menu.add(0, MENU_ID4, 0, /*R.string.menu_end*/"DebugMode");  
        // 追加したメニューアイテムのアイコンを設定  
        item4.setIcon(android.R.drawable.ic_menu_view);  
        
        return true;  
    }  
  
    /** 
     * オプションメニューがクリックされたときの動作 
     */  
    @Override  
    public boolean onOptionsItemSelected(MenuItem item) {  
        switch (item.getItemId()) {  
        case MENU_ID1:  
            // 各ボタンが押されたときの処理を記述  
            Toast toast = Toast  
                    .makeText(this, "ゲーム終了", Toast.LENGTH_LONG);  
            toast.show();  
           
            finish(); 
            
            Intent intent = null;
            intent = new Intent(this,home.class);
            //startActivity(intent);
            return true;  
              
        case MENU_ID2:  
            // 各ボタンが押されたときの処理を記述  
            toast = Toast  
                    .makeText(this, "ゲームリセット", Toast.LENGTH_LONG);  
            toast.show();  
            //finish();  
            view.gameReset();
            return true;  
        case MENU_ID3:  
            // 各ボタンが押されたときの処理を記述  
            toast = Toast  
                    .makeText(this, "ルールを見る", Toast.LENGTH_LONG);  
            toast.show();  
            //finish();  
            intent = new Intent(this,Rule.class);
            startActivity(intent);
            return true;  
        case MENU_ID4:  
        	/*
            // 各ボタンが押されたときの処理を記述  
            toast = Toast  
                    .makeText(this, "DebugModeに入ります", Toast.LENGTH_LONG);  
            toast.show();  
            //finish();  
            view.debugMode();*/
        	final CharSequence[] items = {"Debug","近づいてくる","じゃまする"};


        	new AlertDialog.Builder(PBLAppView.this)
        	.setTitle("モードを選択")
        	.setItems(items, new DialogInterface.OnClickListener() {
        	    public void onClick(DialogInterface dialog, int item) {
        	    	//button.setText(String.format("%sが選択されました。",items[item]));
        	    	view.debugMode(item);
        	    }
        	})
        	.show();

        	
            return true;  
        }  
        
        return true;  
    }  
    
}