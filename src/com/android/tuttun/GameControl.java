package com.android.tuttun;

import java.util.Calendar;

public class GameControl {
	int turn = 0;
	int command = 0;
	int player = -1;		//1Pなら-1,2Pなら1で表す
	int logicStatus = 0;	//ゲームの状態
							//0 移動したい駒の選択待ち
							//1 駒の移動先選択待ち
							//2 移動先に仲間の駒がいた場合の処理(コマンド選択パターン)
	private static Board board = new Board();
	
  	AI ai = new AI();
  	int aiflag =0;

	
	int beforeS;
	int beforeT;
	long startTime;
	long playTime;
	
	GameControl(){
		//startTime = calendar1.getTimeInMillis();
		reset();
	}
	
	public void gameLogic(int s, int t){	//メインロジック
		int a = board.getBoardStatus(s, t);
		if(logicStatus==0){
			//プレイヤーがタイルを選択した
			//int a = board.getBoardStatus(s, t);
			if(a==0){
				//マスに駒が1つもないパターン
				//何もしない(もしくはエラーを返す)
			} else if(a==player){
				//マスに駒が1つある(移動可能)
				//移動可能領域を返す
				board.makeMovingArea(s, t);
				logicStatus=logicStatus+1;
				beforeS = s;
				beforeT = t;
			} else if(a==player*2){
				//マスに駒が2つある(移動不可能)
			} else if(a==player*3){
				//マスに駒が3つある(ゲーム終了)
				logicStatus=10;
			}
		}else if(logicStatus==1){//実際の移動
			if(getMovingArea(s, t)!=0){	//touchされた場所は移動可能範囲か調べる
				if(s==beforeS&&t==beforeT){	//移動可能であるが元と同じ位置をタッチしたときは
					logicStatus=0;
				}else if(a==0){ //移動先に駒がない
					//beforeS,Tにある駒をtouchされた場所に移動する
					board.move(player, beforeS, beforeT, s, t);
					logicStatus=0;
					player = -1 * player;
					turn++;
				}else if(a/Math.abs(a)==player){//移動先に仲間の駒があったとき
						logicStatus=2;
				}else if(a/Math.abs(a)!=player){//移動先の駒が敵の時
						//突っつくしかない
						//logicStatus=3;
					board.Tutuku(player, beforeS, beforeT, s, t);
					logicStatus=0;
					player = -1 * player;
					turn++;
				}
			}
		}else if(logicStatus==2){//移動先に仲間の駒があったとき
			if(command==1){//乗る
				board.ride(player, beforeS, beforeT, s, t);
				logicStatus=0;
				player = -1 * player;
				turn++;
			}else if(command == 2){//つっつく
				board.Tutuku(player, beforeS, beforeT, s, t);
				logicStatus=0;
				player = -1 * player;
				turn++;
			}
			command = 0;
		}else if(logicStatus==3){//移動先に敵の駒があったとき(突っつくしか選択肢はない)削除
			/*
			board.Tutuku(player, beforeS, beforeT, s, t);
			logicStatus=0;
			player = -1 * player;
			turn++;*/
		}else if(logicStatus==10){
			//ゲーム終了
			
		}
		
		if(board.getBoardStatus(s, t)==3){
			logicStatus=10;
		}else if(board.getBoardStatus(s, t)==-3){
			logicStatus=11;
		}
		//logicStatus = logicStatus+1;
		//beforeS = s;
		
		
		
		
		/*
		aiflag ++;
		//if(aiflag==2){	
		if(player == 1 && board.getBoardStatus(s, t)!=-3){	
			ai.calc(board);
			aiflag =0;
			player = -1 * player;
			//logicStatus = logicStatus+1;
			//beforeS = s;
			//beforeT = t;
			for(s=0;s<7;s++){
				for(t=0;t<7;t++){
					if(board.getBoardStatus(s, t)==3){
						logicStatus=10;
					}else if(board.getBoardStatus(s, t)==-3){
						logicStatus=11;
					}
				}
			}
			
		}
		*/
		//beforeT = t;
		
	}
	
	public void aiStart(){
		ai.calc(board);
		//gameLogic(ai.beforeS,ai.beforeT);
		tileClick(ai.beforeS,ai.beforeT);
		//gameLogic(ai.s,ai.t);
		//setCommand(1);
	}
	public void aiStart2(){
		//ai.calc(board);
		//gameLogic(ai.beforeS,ai.beforeT);
		tileClick(ai.s,ai.t);
		setCommand(1);
		//gameLogic(ai.s,ai.t);
		if(board.getBoardStatus(ai.s, ai.t)>0){
			tileClick(ai.s,ai.t);
		}
		//tileClick(ai.beforeS,ai.beforeT);
	}
	public void aiCommand(){
		setCommand(1);
		//gameLogic(ai.s,ai.t);
		tileClick(ai.beforeS,ai.beforeT);
	}
	public int getLogicStatus(){
		return logicStatus;
	}
	public int getBoardStatus(int p,int q){
		return board.getBoardStatus(p, q);
	}
	public int getMovingArea(int p,int q){
		return board.getMovingArea(p, q);
	}
	public void setBoardStatus(int p,int q,int s){//(x座標,y座標,タイルの数値)
		board.setBoardStatus(p, q, s);
	}
	public void tileClick(int s,int t){
		//押されたタイルを判別してゲームロジックを動かす
		//if(player==-1&&board.getBoardStatus(s, t)<0){
			gameLogic(s,t);
		//}else if(player==1&&board.getBoardStatus(s, t)>0){
			//gameLogic(s,t);
		//}
	}

	public void reset() {
		// TODO Auto-generated method stub
		Calendar calendar1 = Calendar.getInstance();
		board.reset();
		turn =0;
		player=-1;
		logicStatus = 0;
		startTime = calendar1.getTimeInMillis();
	}
	public void debugMode(int item){
		this.reset();
		board.originalReset(item);
	}
	public int getPlayer(){
		return player;
	}
	public int getTurn(){
		return turn+1;
	}
	public void setCommand(int a){
		command = a;
	}
	public void setStartTime(long a){
		startTime = a;
	}
	public long getPlayTime(long a){
		long s = a - startTime;
		return s;
	}
}