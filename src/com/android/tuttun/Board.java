package com.android.tuttun;


public class Board {
	
	//Tile field[][] = new Tile[7][8];
	Tile[][] field = new Tile[7][8];
	int[][] movingArea = new int[7][8];//1�Ȃ�ړ��\0�Ȃ�ړ��s��
	
	//�{�[�h(�^�C��)�̏�����
	Board(){
		init();
	}
	
	public void init(){
		for(int s=0;s<7;s++){
			for(int t=0;t<8;t++){
				field[s][t]=new Tile();
				
				//�^�C���̏���������
				field[s][t].setPos(s,t);
				field[s][t].setStatus(0);
				
				if((s==2&&t==1)||(s==5&&t==2)||
						(s==3&&t==3)||(s==1&&t==5)||(s==4&&t==6)){
					//���̈ʒu
					field[s][t].setStatus(1);
				}else if((s==1&&t==2)||(s==4&&t==1)||
						(s==3&&t==4)||(s==2&&t==6)||(s==5&&t==5)){
					//���̈ʒu
					field[s][t].setStatus(-1);
				}
			}
		}
	}
	
	//debug�p�̔z�u
	public void originalInit(int item){
		switch (item) {
		case 0:
			
			break;
		case 1:
			for(int s=0;s<7;s++){
				for(int t=0;t<8;t++){
					field[s][t]=new Tile();
					
					//�^�C���̏���������
					field[s][t].setPos(s,t);
					field[s][t].setStatus(0);
					
					if((s==0&&t==3)||(s==0&&t==4)||
							(s==0&&t==5)||(s==0&&t==6)||(s==0&&t==7)){
						//���̈ʒu
						field[s][t].setStatus(-1);
					}else if((s==0&&t==2)||(s==6&&t==0)||
							(s==2&&t==6)||(s==4&&t==3)||(s==6&&t==7)){
						//���̈ʒu
						field[s][t].setStatus(1);
					}
				}
			}
			break;
		case 2:
			for(int s=0;s<7;s++){
				for(int t=0;t<8;t++){
					field[s][t]=new Tile();
					
					//�^�C���̏���������
					field[s][t].setPos(s,t);
					field[s][t].setStatus(0);
					
					if((s==0&&t==5)||(s==0&&t==6)||
							(s==0&&t==7)||(s==4&&t==4)||(s==4&&t==5)){
						//���̈ʒu
						field[s][t].setStatus(-1);
					}else if((s==0&&t==2)||(s==0&&t==3)||
							(s==0&&t==4)||(s==2&&t==0)||(s==5&&t==1)){
						//���̈ʒu
						field[s][t].setStatus(1);
					}
				}
			}
			break;
		}
	}
	//�ړ��\�̈�𐶐�
	public void makeMovingArea(int p,int q){
		for(int s=0;s<7;s++){
			for(int t=0;t<8;t++){
				movingArea[s][t]=0;//�ړ��ł��Ȃ�
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
	public void move(int player, int s, int t, int p, int q){//�ړ������ړ���
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
		//stpq���炱�܂̈ړ�����������o��
		int x = s-p;
		int y = t-q;
		
		//for(int a=0;a>2;a++){
		tMove(player,s, t, x, y);
		tMove(player,p, q, -x, -y);
		//}	
	}
	
	
	
	public void tMove(int player, int s, int t, int x, int y){//�ړ���C�ړ��� ��}�X���������Ă݂�
		int status = 1;
		if(field[s][t].getStatus()<0){
			status = -1;
		}
		//������Ƃ�2��J��Ԃ��Ă݂�
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
			}else if(field[s+x][t+y].getStatus()==0){//�ړ���ɋ�Ȃ�
				field[s][t].setStatus(status*-1);	//����������
				field[newx][newy].setStatus(status); //�ʏ�ړ�
			}else if(field[newx][newy].getStatus()==status){//�ړ���ɒ��Ԃ�����
				//field[s+x][t+y].setStatus(status);
				//field[s][t].setStatus(status*-1);
				//�ړ��I��
				i=2;
			}else{//�ړ���ɓG������
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
	
