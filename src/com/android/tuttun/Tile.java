package com.android.tuttun;

public class Tile {
	/*
	 * statusでタイルの状態を表す
	 * 0・・・駒がない
	 * 1～3・・・プレイヤー1の駒がある
	 * -1～-3・・・プレイヤー2（コンピュータ）の駒がある
	 */
	int x;		//tileのx座標
	int y;
	int status;		//何があるの？？
	
	Tile(){
		
		x=0;
		y=0;
		status=0;
	}
	public void setPos(int s, int t){
		x=s;
		y=t;
	}
	public void setStatus(int a){
		status = status+a;
	}
	public int  getStatus(){
		return status;
	}
}