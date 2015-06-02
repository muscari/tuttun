package com.android.tuttun;


public class Board {
	
	//Tile field[][] = new Tile[7][8];
	Tile[][] field = new Tile[7][8];
	int[][] movingArea = new int[7][8];//1なら移動可能0なら移動不可
	
	//ボード(タイル)の初期化
	Board(){
		init();
	}
	
	public void init(){
		for(int s=0;s<7;s++){
			for(int t=0;t<8;t++){
				field[s][t]=new Tile();
				
				//タイルの初期化処理
				field[s][t].setPos(s,t);
				field[s][t].setStatus(0);
				
				if((s==2&&t==1)||(s==5&&t==2)||
						(s==3&&t==3)||(s==1&&t==5)||(s==4&&t==6)){
					//黒の位置
					field[s][t].setStatus(1);
				}else if((s==1&&t==2)||(s==4&&t==1)||
						(s==3&&t==4)||(s==2&&t==6)||(s==5&&t==5)){
					//白の位置
					field[s][t].setStatus(-1);
				}
			}
		}
	}
	
	//debug用の配置
	public void originalInit(int item){
		switch (item) {
		case 0:
			
			break;
		case 1:
			for(int s=0;s<7;s++){
				for(int t=0;t<8;t++){
					field[s][t]=new Tile();
					
					//タイルの初期化処理
					field[s][t].setPos(s,t);
					field[s][t].setStatus(0);
					
					if((s==0&&t==3)||(s==0&&t==4)||
							(s==0&&t==5)||(s==0&&t==6)||(s==0&&t==7)){
						//白の位置
						field[s][t].setStatus(-1);
					}else if((s==0&&t==2)||(s==6&&t==0)||
							(s==2&&t==6)||(s==4&&t==3)||(s==6&&t==7)){
						//黒の位置
						field[s][t].setStatus(1);
					}
				}
			}
			break;
		case 2:
			for(int s=0;s<7;s++){
				for(int t=0;t<8;t++){
					field[s][t]=new Tile();
					
					//タイルの初期化処理
					field[s][t].setPos(s,t);
					field[s][t].setStatus(0);
					
					if((s==0&&t==5)||(s==0&&t==6)||
							(s==0&&t==7)||(s==4&&t==4)||(s==4&&t==5)){
						//白の位置
						field[s][t].setStatus(-1);
					}else if((s==0&&t==2)||(s==0&&t==3)||
							(s==0&&t==4)||(s==2&&t==0)||(s==5&&t==1)){
						//黒の位置
						field[s][t].setStatus(1);
					}
				}
			}
			break;
		}
	}
	//移動可能領域を生成
	public void makeMovingArea(int p,int q){
		for(int s=0;s<7;s++){
			for(int t=0;t<8;t++){
				movingArea[s][t]=0;//移動できない
			}
		}
		for(int s=p-1;s<=p+1;s++){
			for(int t=q-1;t<=q+1;t++){
				if(s>=0&&s<7&&t>=0&&t<8){
					movingArea[s][t]=1;
				}
			}
		}
		
	}
	public int getBoardStatus(int p,int q){
		return field[p][q].getStatus();
	}
	public int getMovingArea(int p, int q){
		return movingArea[p][q];
	}
	public void setBoardStatus(int p,int q,int s){
		field[p][q].setStatus(s);
	}
	public void move(int player, int s, int t, int p, int q){//移動元→移動先
		if(player==-1){
			field[s][t].setStatus(1);
			field[p][q].setStatus(-1);
		}else if(player==1){
			field[s][t].setStatus(-1);
			field[p][q].setStatus(1);
		}
	}
	public void ride(int player, int s, int t, int p, int q){
		if(player==-1){
			field[s][t].setStatus(1);
			field[p][q].setStatus(-1);
		}else if(player==1){
			field[s][t].setStatus(-1);
			field[p][q].setStatus(1);
		}
	}
	
	public void Tutuku(int player, int s, int t, int p, int q){//before,new
		//stpqからこまの移動方向を割り出す
		int x = s-p;
		int y = t-q;
		
		//for(int a=0;a>2;a++){
		tMove(player,s, t, x, y);
		tMove(player,p, q, -x, -y);
		//}	
	}
	
	
	
	public void tMove(int player, int s, int t, int x, int y){//移動駒，移動量 一マスずつ動かしてみる
		int status = 1;
		if(field[s][t].getStatus()<0){
			status = -1;
		}
		//同じ作業を2回繰り返してみる
		int i=0;
		while(i<2){
			int newx = s+x;
			int newy = t+y;
			
			boolean a = newx>6||newy>7||newx<0||newy<0;
			boolean b = s>6||t>7||s<0||t<0;
			
			if(a){
				i=2;
			}else if(b){
				i=2;
			}else if(field[s+x][t+y].getStatus()==0){//移動先に駒がない
				field[s][t].setStatus(status*-1);	//元が消える
				field[newx][newy].setStatus(status); //通常移動
			}else if(field[newx][newy].getStatus()==status){//移動先に仲間がいる
				//field[s+x][t+y].setStatus(status);
				//field[s][t].setStatus(status*-1);
				//移動終了
				i=2;
			}else{//移動先に敵がいる
				i=2;
			}
			s=s+x;
			t=t+y;
			i++;
			
		}
	}
	
	
	public void reset() {
		// TODO Auto-generated method stub
		this.init();
	}

	public void originalReset(int item) {
		// TODO Auto-generated method stub
		this.originalInit(item);
	}
}
	
