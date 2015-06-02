package com.android.tuttun;

import java.util.Calendar;

public class GameControl {
	int turn = 0;
	int command = 0;
	int player = -1;		//1P�Ȃ�-1,2P�Ȃ�1�ŕ\��
	int logicStatus = 0;	//�Q�[���̏��
							//0 �ړ���������̑I��҂�
							//1 ��̈ړ���I��҂�
							//2 �ړ���ɒ��Ԃ̋�����ꍇ�̏���(�R�}���h�I���p�^�[��)
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
	
	public void gameLogic(int s, int t){	//���C�����W�b�N
		int a = board.getBoardStatus(s, t);
		if(logicStatus==0){
			//�v���C���[���^�C����I������
			//int a = board.getBoardStatus(s, t);
			if(a==0){
				//�}�X�ɋ1���Ȃ��p�^�[��
				//�������Ȃ�(�������̓G���[��Ԃ�)
			} else if(a==player){
				//�}�X�ɋ1����(�ړ��\)
				//�ړ��\�̈��Ԃ�
				board.makeMovingArea(s, t);
				logicStatus=logicStatus+1;
				beforeS = s;
				beforeT = t;
			} else if(a==player*2){
				//�}�X�ɋ2����(�ړ��s�\)
			} else if(a==player*3){
				//�}�X�ɋ3����(�Q�[���I��)
				logicStatus=10;
			}
		}else if(logicStatus==1){//���ۂ̈ړ�
			if(getMovingArea(s, t)!=0){	//touch���ꂽ�ꏊ�͈ړ��\�͈͂����ׂ�
				if(s==beforeS&&t==beforeT){	//�ړ��\�ł��邪���Ɠ����ʒu���^�b�`�����Ƃ���
					logicStatus=0;
				}else if(a==0){ //�ړ���ɋ�Ȃ�
					//beforeS,T�ɂ�����touch���ꂽ�ꏊ�Ɉړ�����
					board.move(player, beforeS, beforeT, s, t);
					logicStatus=0;
					player = -1 * player;
					turn++;
				}else if(a/Math.abs(a)==player){//�ړ���ɒ��Ԃ̋�������Ƃ�
						logicStatus=2;
				}else if(a/Math.abs(a)!=player){//�ړ���̋�G�̎�
						//�˂��������Ȃ�
						//logicStatus=3;
					board.Tutuku(player, beforeS, beforeT, s, t);
					logicStatus=0;
					player = -1 * player;
					turn++;
				}
			}
		}else if(logicStatus==2){//�ړ���ɒ��Ԃ̋�������Ƃ�
			if(command==1){//���
				board.ride(player, beforeS, beforeT, s, t);
				logicStatus=0;
				player = -1 * player;
				turn++;
			}else if(command == 2){//����
				board.Tutuku(player, beforeS, beforeT, s, t);
				logicStatus=0;
				player = -1 * player;
				turn++;
			}
			command = 0;
		}else if(logicStatus==3){//�ړ���ɓG�̋�������Ƃ�(�˂��������I�����͂Ȃ�)�폜
			/*
			board.Tutuku(player, beforeS, beforeT, s, t);
			logicStatus=0;
			player = -1 * player;
			turn++;*/
		}else if(logicStatus==10){
			//�Q�[���I��
			
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
	public void setBoardStatus(int p,int q,int s){//(x���W,y���W,�^�C���̐��l)
		board.setBoardStatus(p, q, s);
	}
	public void tileClick(int s,int t){
		//�����ꂽ�^�C���𔻕ʂ��ăQ�[�����W�b�N�𓮂���
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