package com.android.tuttun;

public class Tile {
	/*
	 * status�Ń^�C���̏�Ԃ�\��
	 * 0�E�E�E��Ȃ�
	 * 1�`3�E�E�E�v���C���[1�̋����
	 * -1�`-3�E�E�E�v���C���[2�i�R���s���[�^�j�̋����
	 */
	int x;		//tile��x���W
	int y;
	int status;		//��������́H�H
	
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