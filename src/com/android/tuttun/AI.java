package com.android.tuttun;
import java.util.Timer;

import java.util.TimerTask;

import java.util.Random;

public class AI {

	int beforeS;
	int beforeT;
	int s;
	int t;
	
	/*AI����������ɓ�����E�E�E
	 * 
	 * GameControl�ɂ�
	 * ���Ղ�
	 * 	AI ai = new AI();
	 * 	int aiflag =0;
	 * 
	 * ���Ղ�logicStatus==10��}�̒����
	 *
	 * aiflag ++;
		if(aiflag==2){
			ai.calc(board);
			aiflag =0;
		}
	 * ���L�����Ă���
	 * 
	 * �܂��A������player== -1�̃^�[���ɂ��邽�߂�
	 * //player = -1 * player;
	 * �̂悤�Ƀv���C���[��������S�ăR�����g�����Ă���
	 * 
	 * ������logicstatus = 0 �̌�ɂ͔������̂���
	 * 			aiflag --;
	 * �ƋL�q���Ă���
	 * */
	
	
	
	public void calc(Board board){
		
		//board.getBoardStatus(1, 1);
		
		//(y+1,x+1,���g)
		//board.setBoardStatus(1, 1, 1);

		//(���g�A�ړ��Oy+1�A�ړ��Ox+1�A�ړ���y+1�A�ړ���x+1)
		//board.move(-1,2,1,3,1);
		
		
		int kasouboard[][] = new int[7+2][8+2]; 
		
		int a = 1;
		int b = 1;
		
		/**Andorid�ł�1P��-1,CPU��+1�𑀂�
		 * cx��move�Ƃ��ŁA�ړ�������̂͂ǂ����������肷��ۂ�
		 * ���p����
		 */
		int cx = 1;






		//��������{�[�h�̏������z�{�[�h�ɑ��
		for (int j = 1; j <= 8; j++) {
			for (int i = 1; i <= 7; i++) {
				
				//(i,j)�ł͂Ȃ�(j,i)�ł��邱�Ƃɒ���
				//�Ȃ��Ȃ�O����(y���W�Ax���W)�Ƃ��Ă��邩��
				//�܂��A����̍ۂ͊O���͍����(0,0)�ɂ��Ă��邪�A����(1,1)�Ƃ��Ă��邽�߁A������C�����悤
				kasouboard[a][b] = -1*cx*board.getBoardStatus(i-1, j-1);
				a++;
			}
			a = 1;
			b++;
		}
		
		

		int status;
		int status1=0;
		int a1 = 0;
		int b1 = 0;
		int a2 = 0;
		int b2 = 0;
		int a3 = 0;
		int b3 = 0;
		int a4 = 0;
		int b4 = 0;
		int a5 = 0;
		int b5 = 0;
		int a6 = 0;
		int b6 = 0;
		int count = 1;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		int k = 1;

		int  kyori[] = new int[50];
		int  minimum = 200;
		
		
		int kaigyo = 0;
		int line = 1;
		//Scanner stdIn = new Scanner(System.in);
		System.out.println("���z�{�[�h�̕\����");
		System.out.println(" |  1  2  3  4  5  6  7");
		System.out.println("-----------------------");
		for (int d = 1; d <= 8; d++) {
			System.out.print(line+"|");
			for (int c = 1; c <= 7; c++) {
				System.out.printf("%3d",kasouboard[c][d]);
				kaigyo++;
					if(kaigyo == 7){
						System.out.println();
						line++;
						kaigyo = 0;
					}
			}
		}
		
		
		
		
		/**��������v���O����
		 * flag1==0		���z�{�[�h��-1�����Ȃ��̂ŁA������߂Â��Ă����B�������ړ��ɂ������Ă����͔������Ȃ�����
		 * flag1==1		���z�{�[�h��-2������̂ŁA�����-1���߂Â��Ă����B�������ړ��ɂ������Ă����͔������Ȃ�����
		 * flag1==2		
		 * flag1==3		���z�{�[�h��2������̂ŁA�N���A���ז����ׂ������-1���߂Â��Ă���
		 */
		int flag1 = 0;
		int flag2 = 0;
		
		
		//�D��x�������̂����ɗ��Ă���
		//�����flag1==3���flag1==1��D�悵�Ă���
		
		
		
		for (int d = 1; d <= 8; d++) {
			for (int c = 1; c <= 7; c++) {
			 	status = kasouboard[c][d];
			 	if(status == -2){
 					a1 = c;
 					b1 = d;
 					flag1 = 1;
			 	}
			}
		}
		for (int d = 1; d <= 8; d++) {
			for (int c = 1; c <= 7; c++) {
			 	status = kasouboard[c][d];
			 	if(status == 2){
 					a1 = c;
 					b1 = d;
 					flag1 = 3;
			 	}
			}
		}
		
		if(flag1==3){
			for (int d = 1; d <= 8; d++) {
				for (int c = 1; c <= 7; c++) {
				 	status = kasouboard[c][d];
				 	if(status == -2){
	 					a1 = c;
	 					b1 = d;
	 					flag1 = 4;
				 	}
				}
			}
			
			
		}
		
		
		
		
		
		
		
		


		//���ォ�猟�����āA�P�߂̃R�}�����_�Ƃ��ĂQ�߈ȍ~�̋������v�Z
		for (int d = 1; d <= 8; d++) {
			for (int c = 1; c <= 7; c++) {
			 	status = kasouboard[c][d];
			 	switch (count){
			 		case 1:		if(status== -1 || status == -2){
			 					a1 = c;
			 					b1 = d;
			 					status1 = status;
			 					count++;
			 				}break;
			 				
			 		case 2:		if (status== -1 || status == -2){
 								a2 = c;
 								b2 = d;
 								kyori[k] = ((a2-a1)*(a2-a1))+((b2-b1)*(b2-b1));
 								if(kyori[k]<0){
 									kyori[k]= -1*kyori[k];
 								}
 								
 								//������̓f�o�b�O�p�̕\���v���O�����A�R�����g�ŏ��O���Ă����Ȃ�
 								System.out.println((status1)+"��(x,y)=("+a1+","+b1+")��"+status+"��(x,y)=("+a2+","+b2+")�̋�����"+kyori[k]);
 								count3++;
 								if( kyori[k] < minimum ){
 					                 minimum = kyori[k];
 					                 a4 = a1;
 					                 b4 = b1;
 					                 a5 = a2;
 					                 b5 = b2;
 					                 
 					             }
 								if( kyori[k] == minimum ){
					                 Random rand = new Random();
					                int select = rand.nextInt(2);
					                if(select == 0){
					                	//�����O�Ȃ�A��̍ŒZ������I��	
					                }
					                if(select == 1){
					                	//�����P�Ȃ�A���Ƃ̍ŒZ������I��	
	 					                 a4 = a1;
	 					                 b4 = b1;
	 					                 a5 = a2;
	 					                 b5 = b2;
					                } 
					             }
 								k++;
 							}break;						
			 	}	
			}
		}
		
		//�@X�ځiX���P�C�Q�E�E�j�̃R�}�̌ォ�猟�����āAY�ځiY���Q�C�R�E�E�j�̃R�}�����_�Ƃ��ĂQ�߈ȍ~�̋������v�Z
		while(count2 <= 2){
		count = 1;
		
		if(a1 ==7){
			a1 =0;
		}
		a3 = a1+1;
		b3 = b1;
		for (int d = b3; d <= 8; d++) {
			for (int c = a3 ; c <= 7; c++) {
			 	status = kasouboard[c][d];
			 	switch (count){
			 		case 1:		if(status== -1 || status == -2){
			 					a1 = c;
			 					b1 = d;
			 					status1 = status;
			 					count++;
			 				}break;
			 				
			 		case 2:		if (status== -1 || status == -2){
 								a2 = c;
 								b2 = d;
 								kyori[k] = ((a2-a1)*(a2-a1))+((b2-b1)*(b2-b1));
 								if(kyori[k]<0){
 									kyori[k]= -1*kyori[k];
 								}
 								
 								//������̓f�o�b�O�p�̕\���v���O�����A�R�����g�ŏ��O���Ă����Ȃ�
 								System.out.println((status1)+"��(x,y)=("+a1+","+b1+")��"+status+"��(x,y)=("+a2+","+b2+")�̋�����"+kyori[k]);
 								
 								count3++;
 								if( kyori[k] < minimum ){
 					                 minimum = kyori[k];
 					                 a4 = a1;
 					                 b4 = b1;
 					                 a5 = a2;
 					                 b5 = b2;
 					                 
 					             }
 								if( kyori[k] == minimum ){
					                 Random rand = new Random();
					                int select = rand.nextInt(2);
					                if(select == 0){
					                	//�����O�Ȃ�A��̍ŒZ������I��	
					                }
					                if(select == 1){
					                	//�����P�Ȃ�A���Ƃ̍ŒZ������I��	
	 					                 a4 = a1;
	 					                 b4 = b1;
	 					                 a5 = a2;
	 					                 b5 = b2;
					                } 
					             }
 								k++;
 							}break;						
			 	}	a3 = 1;
			}
		}count2++;
		}
		
		System.out.println("�ŏ�������(x,y)=("+a4+","+b4+")��(x,y)=("+a5+","+b5+")�ŋ�����"+minimum);

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		//����Ƃ����Q�[���̐��i��A�ł��邾���R�}���^�񒆂Ɋ���čs���悤��𓮂����A���S���牓�����̃R�}���ǂ��炩�Z�o
		
		//���S���W��(4,5)�Ƃ���
		int center_x = 4;
		int center_y = 5; 
		
		//���S����(a4,b4)��(a5,b5)�܂ł̋������Z�o
		int center_r4 = (a4-center_x)*(a4-center_x)+(b4-center_y)*(b4-center_y);
		int center_r5 = (a5-center_x)*(a5-center_x)+(b5-center_y)*(b5-center_y);
		
		//���ꂼ��̃R�}�ւ̋����̉��������Z�o
		int center_r = center_r4;
		if (center_r5 > center_r4){
			center_r = center_r5;
		}
		
		//���̎��ɂ��A�������R�}�͕K��(a4,b4)�ƂȂ�
		if(center_r==center_r5){
			a6 = a5;
			b6 = b5;
			a5 = a4;
			b5 = b4;
			a5 = a6;
			b5 = b6;
		}
		
		//(a4,b4)�������Ɉړ��\�͈͂ňړ������Ă݂āi�W�����P�}�X�j�A���ꂼ��ƁA(a5,b5)�Ƃ̋������Z�o
		//�W������(����A��A�E��A���A�E�A�����A���A�E��)�̏���
		int a41 = (a5-(a4-1))*(a5-(a4-1))+(b5-(b4-1))*(b5-(b4-1));
		int a42 = (a5-(a4+0))*(a5-(a4+0))+(b5-(b4-1))*(b5-(b4-1));
		int a43 = (a5-(a4+1))*(a5-(a4+1))+(b5-(b4-1))*(b5-(b4-1));
		
		int a44 = (a5-(a4-1))*(a5-(a4-1))+(b5-(b4-0))*(b5-(b4-0));
		int a45 = (a5-(a4+1))*(a5-(a4+1))+(b5-(b4-0))*(b5-(b4-0));			
		
		int a46 = (a5-(a4-1))*(a5-(a4-1))+(b5-(b4+1))*(b5-(b4+1));
		int a47 = (a5-(a4+0))*(a5-(a4+0))+(b5-(b4+1))*(b5-(b4+1));
		int a48 = (a5-(a4+1))*(a5-(a4+1))+(b5-(b4+1))*(b5-(b4+1));
		
		
		int mini_a = a41;
		if(a42 < mini_a){
			mini_a = a42;
		}
		if(a43 < mini_a){
			mini_a = a43;
		}
		if(a44 < mini_a){
			mini_a = a44;
		}
		if(a45 < mini_a){
			mini_a = a45;
		}
		if(a46 < mini_a){
			mini_a = a46;
		}
		if(a47 < mini_a){
			mini_a = a47;
		}
		if(a48 < mini_a){
			mini_a = a48;
		}
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		if(flag1 == 0){

		//(a4,b4)�̃R�}���ǂ����ɓ��������ƂŁA�ŏ��l���o���B�@�ł́A��̓I�ɂǂ��ɓ���������ŏ��l���ł��H ��v�����Ƃ��ɃR�}�𓮂���
		if(mini_a == a41){
			a6 = a4-1;
			b6 = b4-1;
			if(kasouboard[a6][b6]== cx){
				flag1 =2;
			}
			kasouboard[a4-1][b4-1]= kasouboard[a4][b4] + kasouboard[a4-1][b4-1];
			kasouboard[a4][b4] = 0;
		}
		if(mini_a == a42){
			a6 = a4;
			b6 = b4-1;
			if(kasouboard[a6][b6]== cx){
				flag1 =2;
			}
			
			kasouboard[a4][b4-1]= kasouboard[a4][b4] + kasouboard[a4][b4-1];
			kasouboard[a4][b4] = 0;

		}
		if(mini_a == a43){
			a6 = a4+1;
			b6 = b4-1;
			if(kasouboard[a6][b6]== cx){
				flag1 =2;
			}
			kasouboard[a4+1][b4-1]= kasouboard[a4][b4] + kasouboard[a4+1][b4-1];
			kasouboard[a4][b4] = 0;

		}
		if(mini_a == a44){
			a6 = a4-1;
			b6 = b4;
			if(kasouboard[a6][b6]== cx){
				flag1 =2;
			}
			kasouboard[a4-1][b4]= kasouboard[a4][b4] + kasouboard[a4-1][b4];
			kasouboard[a4][b4] = 0;

		}
		if(mini_a == a45){
			a6 = a4+1;
			b6 = b4;
			if(kasouboard[a6][b6]== cx){
				flag1 =2;
			}
			kasouboard[a4+1][b4]= kasouboard[a4][b4] + kasouboard[a4+1][b4];
			kasouboard[a4][b4] = 0;

		}
		if(mini_a == a46){
			a6 = a4-1;
			b6 = b4+1;
			if(kasouboard[a6][b6]== cx){
				flag1 =2;
			}
			kasouboard[a4-1][b4+1]= kasouboard[a4][b4] + kasouboard[a4-1][b4+1];
			kasouboard[a4][b4] = 0;

		}
		if(mini_a == a47){
			a6 = a4;
			b6 = b4+1;
			if(kasouboard[a6][b6]== cx){
				flag1 =2;
			}
			kasouboard[a4][b4+1]= kasouboard[a4][b4] + kasouboard[a4][b4+1];
			kasouboard[a4][b4] = 0;

		}
		if(mini_a == a48){
			a6 = a4+1;
			b6 = b4+1;
			if(kasouboard[a6][b6]== cx){
				flag1 =2;
			}
			kasouboard[a4+1][b4+1]= kasouboard[a4][b4] + kasouboard[a4+1][b4+1];
			kasouboard[a4][b4] = 0;

		}
		
		}
		


		if(flag1 == 1){
			
			int kyori2[] = new int[20];
			k = 1;
			minimum = 200;
			
			for (int d = 1; d <= 8; d++) {
				for (int c = 1; c <= 7; c++) {
				 	status = kasouboard[c][d];
				 	if(status == -2){
	 					a1 = c;
	 					b1 = d;
	 					status1 = status;
	 					flag1 = 1;
				 	}
				}
			}
			
			
			for (int d = 1; d <= 8; d++) {
				for (int c = 1; c <= 7; c++) {
					status = kasouboard[c][d];
					if(status == -1){
						a2 = c;
						b2 = d;
						kyori2[k] = ((a2-a1)*(a2-a1))+((b2-b1)*(b2-b1));
						System.out.println(status1+"��("+a1+","+b1+")��"+status+"��("+a2+","+b2+")�̋�����"+kyori2[k]);
							//count3++;
							if( kyori2[k] < minimum ){
				                 minimum = kyori2[k];
				                 
				                 /**��ƈ����
				                  * a4 = a1;
				                  * b4 = b1;
				                  * a5 = a2;
				                  * b5 = b2;
				                  * �ł͂Ȃ�
				                  * �Ȃ��Ȃ�(a4,b4)�𓮂����v���O�������ȍ~�Ŏg���񂷂̂�
				                  * (a4,b4)��-1���A(a5,b5)��-2�̍��W�����Ă���
				                  * 
				                  */
				                 a5 = a1;
				                 b5 = b1;
				                 a4 = a2;
				                 b4 = b2;
				             }
							if( kyori[k] == minimum ){
				                 Random rand = new Random();
				                int select = rand.nextInt(2);
				                if(select == 0){
				                	//�����O�Ȃ�A��̍ŒZ������I��	
				                }
				                if(select == 1){
				                	//�����P�Ȃ�A���Ƃ̍ŒZ������I��	
				                	 /**��ƈ����
					                  * a4 = a1;
					                  * b4 = b1;
					                  * a5 = a2;
					                  * b5 = b2;
					                  * �ł͂Ȃ�
					                  * �Ȃ��Ȃ�(a4,b4)�𓮂����v���O�������ȍ~�Ŏg���񂷂̂�
					                  * (a4,b4)��-1���A(a5,b5)��-2�̍��W�����Ă���
					                  * 
					                  */
					                 a5 = a1;
					                 b5 = b1;
					                 a4 = a2;
					                 b4 = b2;
				                } 
				             }
						k++;
					}
				}
			}
		}
		
		if(flag1 == 1){
		System.out.println("-2��-1�̍ŏ�������(x,y)=("+a5+","+b5+")��(x,y)=("+a4+","+b4+")�ŋ�����"+minimum);
		}
		
		if(flag1 == 1){
			
			
			a41 = (a5-(a4-1))*(a5-(a4-1))+(b5-(b4-1))*(b5-(b4-1));
			a42 = (a5-(a4+0))*(a5-(a4+0))+(b5-(b4-1))*(b5-(b4-1));
			a43 = (a5-(a4+1))*(a5-(a4+1))+(b5-(b4-1))*(b5-(b4-1));
			
			a44 = (a5-(a4-1))*(a5-(a4-1))+(b5-(b4-0))*(b5-(b4-0));
			a45 = (a5-(a4+1))*(a5-(a4+1))+(b5-(b4-0))*(b5-(b4-0));			
			
			a46 = (a5-(a4-1))*(a5-(a4-1))+(b5-(b4+1))*(b5-(b4+1));
			a47 = (a5-(a4+0))*(a5-(a4+0))+(b5-(b4+1))*(b5-(b4+1));
			a48 = (a5-(a4+1))*(a5-(a4+1))+(b5-(b4+1))*(b5-(b4+1));

			mini_a = a41;
			if(a42 < mini_a){
				mini_a = a42;
			}
			if(a43 < mini_a){
				mini_a = a43;
			}
			if(a44 < mini_a){
				mini_a = a44;
			}
			if(a45 < mini_a){
				mini_a = a45;
			}
			if(a46 < mini_a){
				mini_a = a46;
			}
			if(a47 < mini_a){
				mini_a = a47;
			}
			if(a48 < mini_a){
				mini_a = a48;
			}
			
			if(mini_a == a41){
				kasouboard[a4-1][b4-1]= kasouboard[a4][b4] + kasouboard[a4-1][b4-1];
				kasouboard[a4][b4] = 0;
				a6 = a4-1;
				b6 = b4-1;
			}
			if(mini_a == a42){
				kasouboard[a4][b4-1]= kasouboard[a4][b4] + kasouboard[a4][b4-1];
				kasouboard[a4][b4] = 0;
				a6 = a4;
				b6 = b4-1;
			}
			if(mini_a == a43){
				kasouboard[a4+1][b4-1]= kasouboard[a4][b4] + kasouboard[a4+1][b4-1];
				kasouboard[a4][b4] = 0;
				a6 = a4+1;
				b6 = b4-1;
			}
			if(mini_a == a44){
				kasouboard[a4-1][b4]= kasouboard[a4][b4] + kasouboard[a4-1][b4];
				kasouboard[a4][b4] = 0;
				a6 = a4-1;
				b6 = b4;
			}
			if(mini_a == a45){
				kasouboard[a4+1][b4]= kasouboard[a4][b4] + kasouboard[a4+1][b4];
				kasouboard[a4][b4] = 0;
				a6 = a4+1;
				b6 = b4;
			}
			if(mini_a == a46){
				kasouboard[a4-1][b4+1]= kasouboard[a4][b4] + kasouboard[a4-1][b4+1];
				kasouboard[a4][b4] = 0;
				a6 = a4-1;
				b6 = b4+1;
			}
			if(mini_a == a47){
				kasouboard[a4][b4+1]= kasouboard[a4][b4] + kasouboard[a4][b4+1];
				kasouboard[a4][b4] = 0;
				a6 = a4;
				b6 = b4+1;
			}
			if(mini_a == a48){
				kasouboard[a4+1][b4+1]= kasouboard[a4][b4] + kasouboard[a4+1][b4+1];
				kasouboard[a4][b4] = 0;
				a6 = a4+1;
				b6 = b4+1;
			}
		}
		

		
		if(flag1 == 3){
			minimum = 200;
			k = 1;
			for (int d = 1; d <= 8; d++) {
				for (int c = 1; c <= 7; c++) {
				 	status = kasouboard[c][d];
				 	if(status == 2){
	 					a1 = c;
	 					b1 = d;
	 					status1 = status;
				 	}
				}
			}
			for (int d = 1; d <= 8; d++) {
				for (int c = 1; c <= 7; c++) {
				 	status = kasouboard[c][d];
				 	if(status == -1){
								a2 = c;
								b2 = d;
								kyori[k] = ((a2-a1)*(a2-a1))+((b2-b1)*(b2-b1));
								if(kyori[k]<0){
									kyori[k]= -1*kyori[k];
								}
								
								//������̓f�o�b�O�p�̕\���v���O�����A�R�����g�ŏ��O���Ă����Ȃ�
								System.out.println(status1+"��(x,y)=("+a1+","+b1+")��"+status+"��(x,y)=("+a2+","+b2+")�̋�����"+kyori[k]);
								count3++;
								if( kyori[k] < minimum ){
					                 minimum = kyori[k];
					                /**��ƈ����
				                  * a4 = a1;
				                  * b4 = b1;
				                  * a5 = a2;
				                  * b5 = b2;
				                  * �ł͂Ȃ�
				                  * �Ȃ��Ȃ�(a4,b4)�𓮂����v���O�������ȍ~�Ŏg���񂷂̂�
				                  * (a4,b4)��-1���A(a5,b5)��-2�̍��W�����Ă���
				                  * 
				                  */
				                 a5 = a1;
				                 b5 = b1;
				                 a4 = a2;
				                 b4 = b2;
					                 
					             }
								if( kyori[k] == minimum ){
				                 Random rand = new Random();
				                int select = rand.nextInt(2);
				                if(select == 0){
				                	//�����O�Ȃ�A��̍ŒZ������I��	
				                }
				                if(select == 1){
				                	//�����P�Ȃ�A���Ƃ̍ŒZ������I��	
				                	 /**��ƈ����
					                  * a4 = a1;
					                  * b4 = b1;
					                  * a5 = a2;
					                  * b5 = b2;
					                  * �ł͂Ȃ�
					                  * �Ȃ��Ȃ�(a4,b4)�𓮂����v���O�������ȍ~�Ŏg���񂷂̂�
					                  * (a4,b4)��-1���A(a5,b5)��-2�̍��W�����Ă���
					                  * 
					                  */
					                 a5 = a1;
					                 b5 = b1;
					                 a4 = a2;
					                 b4 = b2;
				                } 
				             }
								k++;
				 	
				}
			}
		}

		/**+2��-1�̍ŏ������͕��������A�i�[�������A�ł�-1�����ۂɂǂ��ɓ���������œK���ȁH
		 */
			if(flag1 == 3){
				System.out.println("2��-1�̍ŏ�������(x,y)=("+a5+","+b5+")��(x,y)=("+a4+","+b4+")�ŋ�����"+minimum);
				}
				
			if(flag1 == 3){
					
					
					a41 = (a5-(a4-1))*(a5-(a4-1))+(b5-(b4-1))*(b5-(b4-1));
					a42 = (a5-(a4+0))*(a5-(a4+0))+(b5-(b4-1))*(b5-(b4-1));
					a43 = (a5-(a4+1))*(a5-(a4+1))+(b5-(b4-1))*(b5-(b4-1));
					
					a44 = (a5-(a4-1))*(a5-(a4-1))+(b5-(b4-0))*(b5-(b4-0));
					a45 = (a5-(a4+1))*(a5-(a4+1))+(b5-(b4-0))*(b5-(b4-0));			
					
					a46 = (a5-(a4-1))*(a5-(a4-1))+(b5-(b4+1))*(b5-(b4+1));
					a47 = (a5-(a4+0))*(a5-(a4+0))+(b5-(b4+1))*(b5-(b4+1));
					a48 = (a5-(a4+1))*(a5-(a4+1))+(b5-(b4+1))*(b5-(b4+1));

					mini_a = a41;
					if(a42 < mini_a){
						mini_a = a42;
					}
					if(a43 < mini_a){
						mini_a = a43;
					}
					if(a44 < mini_a){
						mini_a = a44;
					}
					if(a45 < mini_a){
						mini_a = a45;
					}
					if(a46 < mini_a){
						mini_a = a46;
					}
					if(a47 < mini_a){
						mini_a = a47;
					}
					if(a48 < mini_a){
						mini_a = a48;
					}
					
					if(mini_a == a41){
						kasouboard[a4-1][b4-1]= kasouboard[a4][b4] + kasouboard[a4-1][b4-1];
						kasouboard[a4][b4] = 0;
						a6 = a4-1;
						b6 = b4-1;
					}
					if(mini_a == a42){
						kasouboard[a4][b4-1]= kasouboard[a4][b4] + kasouboard[a4][b4-1];
						kasouboard[a4][b4] = 0;
						a6 = a4;
						b6 = b4-1;
					}
					if(mini_a == a43){
						kasouboard[a4+1][b4-1]= kasouboard[a4][b4] + kasouboard[a4+1][b4-1];
						kasouboard[a4][b4] = 0;
						a6 = a4+1;
						b6 = b4-1;
					}
					if(mini_a == a44){
						kasouboard[a4-1][b4]= kasouboard[a4][b4] + kasouboard[a4-1][b4];
						kasouboard[a4][b4] = 0;
						a6 = a4-1;
						b6 = b4;
					}
					if(mini_a == a45){
						kasouboard[a4+1][b4]= kasouboard[a4][b4] + kasouboard[a4+1][b4];
						kasouboard[a4][b4] = 0;
						a6 = a4+1;
						b6 = b4;
					}
					if(mini_a == a46){
						kasouboard[a4-1][b4+1]= kasouboard[a4][b4] + kasouboard[a4-1][b4+1];
						kasouboard[a4][b4] = 0;
						a6 = a4-1;
						b6 = b4+1;
					}
					if(mini_a == a47){
						kasouboard[a4][b4+1]= kasouboard[a4][b4] + kasouboard[a4][b4+1];
						kasouboard[a4][b4] = 0;
						a6 = a4;
						b6 = b4+1;
					}
					if(mini_a == a48){
						kasouboard[a4+1][b4+1]= kasouboard[a4][b4] + kasouboard[a4+1][b4+1];
						kasouboard[a4][b4] = 0;
						a6 = a4+1;
						b6 = b4+1;
					}
				}	
			
		}
		
		
		
		/*
		kasouboard[0][0] = -1*cx*board.getBoardStatus(b4, a4);
		kasouboard[8][9] = -1*cx*board.getBoardStatus(b6, a6);
		if(kasouboard[8][9] == -1*cx){
			board.Tutuku(-cx ,b4-1 ,a4-1,b6-1,a6-1);
		}else{
			
		}*/
		
		//if(flag1 ==0 || flag1 == 1){
		//board.move(cx ,a4-1 ,b4-1,a6-1,b6-1);
		
		
		
		/************************************************************************************/
		
		/*�������炨�݂������[�`��Ԃ̂Ƃ��̃v���O��������������*/
		
		
		int hirata1;
		int daiki1;
		int hirata2;
		int daiki2;
		
		if(flag1 == 4){
			minimum = 200;
			k = 1;

			for (int d = 1; d <= 8; d++) {
				for (int c = 1; c <= 7; c++) {
				 	status = kasouboard[c][d];
				 	if(status == 2){
	 					a1 = c;
	 					b1 = d;
	 					status1 = status;
				 	}
				}
			}
			for (int d = 1; d <= 8; d++) {
				for (int c = 1; c <= 7; c++) {
				 	status = kasouboard[c][d];
				 	if(status == 1){
								a2 = c;
								b2 = d;
								kyori[k] = ((a2-a1)*(a2-a1))+((b2-b1)*(b2-b1));
								if(kyori[k]<0){
									kyori[k]= -1*kyori[k];
								}
								
								//������̓f�o�b�O�p�̕\���v���O�����A�R�����g�ŏ��O���Ă����Ȃ�
								System.out.println(status1+"��(x,y)=("+a1+","+b1+")��"+status+"��(x,y)=("+a2+","+b2+")�̋�����"+kyori[k]);
								count3++;
								if( kyori[k] < minimum ){
					                 minimum = kyori[k];
					                /**��ƈ����
				                  * a4 = a1;
				                  * b4 = b1;
				                  * a5 = a2;
				                  * b5 = b2;
				                  * �ł͂Ȃ�
				                  * �Ȃ��Ȃ�(a4,b4)�𓮂����v���O�������ȍ~�Ŏg���񂷂̂�
				                  * (a4,b4)��-1���A(a5,b5)��-2�̍��W�����Ă���
				                  * 
				                  */
				                 a5 = a1;
				                 b5 = b1;
				                 a4 = a2;
				                 b4 = b2;
					                 
					             }
								if( kyori[k] == minimum ){
				                 Random rand = new Random();
				                int select = rand.nextInt(2);
				                if(select == 0){
				                	//�����O�Ȃ�A��̍ŒZ������I��	
				                }
				                if(select == 1){
				                	//�����P�Ȃ�A���Ƃ̍ŒZ������I��	
				                	 /**��ƈ����
					                  * a4 = a1;
					                  * b4 = b1;
					                  * a5 = a2;
					                  * b5 = b2;
					                  * �ł͂Ȃ�
					                  * �Ȃ��Ȃ�(a4,b4)�𓮂����v���O�������ȍ~�Ŏg���񂷂̂�
					                  * (a4,b4)��-1���A(a5,b5)��-2�̍��W�����Ă���
					                  * 
					                  */
					                 a5 = a1;
					                 b5 = b1;
					                 a4 = a2;
					                 b4 = b2;
				                } 
				             }
								k++;
				 	
				}
			}
		}
			
		//2��1�̋����������ł߂����Ă���
			hirata1 =minimum;
			
			
			
			
			
			
			
			
			minimum = 200;
			k = 1;

			for (int d = 1; d <= 8; d++) {
				for (int c = 1; c <= 7; c++) {
				 	status = kasouboard[c][d];
				 	if(status == -2){
	 					a1 = c;
	 					b1 = d;
	 					status1 = status;
				 	}
				}
			}
			for (int d = 1; d <= 8; d++) {
				for (int c = 1; c <= 7; c++) {
				 	status = kasouboard[c][d];
				 	if(status == -1){
								a2 = c;
								b2 = d;
								kyori[k] = ((a2-a1)*(a2-a1))+((b2-b1)*(b2-b1));
								if(kyori[k]<0){
									kyori[k]= -1*kyori[k];
								}
								
								//������̓f�o�b�O�p�̕\���v���O�����A�R�����g�ŏ��O���Ă����Ȃ�
								System.out.println(status1+"��(x,y)=("+a1+","+b1+")��"+status+"��(x,y)=("+a2+","+b2+")�̋�����"+kyori[k]);
								count3++;
								if( kyori[k] < minimum ){
					                 minimum = kyori[k];
					                /**��ƈ����
				                  * a4 = a1;
				                  * b4 = b1;
				                  * a5 = a2;
				                  * b5 = b2;
				                  * �ł͂Ȃ�
				                  * �Ȃ��Ȃ�(a4,b4)�𓮂����v���O�������ȍ~�Ŏg���񂷂̂�
				                  * (a4,b4)��-1���A(a5,b5)��-2�̍��W�����Ă���
				                  * 
				                  */
				                 a5 = a1;
				                 b5 = b1;
				                 a4 = a2;
				                 b4 = b2;
					                 
					             }
								if( kyori[k] == minimum ){
				                 Random rand = new Random();
				                int select = rand.nextInt(2);
				                if(select == 0){
				                	//�����O�Ȃ�A��̍ŒZ������I��	
				                }
				                if(select == 1){
				                	//�����P�Ȃ�A���Ƃ̍ŒZ������I��	
				                	 /**��ƈ����
					                  * a4 = a1;
					                  * b4 = b1;
					                  * a5 = a2;
					                  * b5 = b2;
					                  * �ł͂Ȃ�
					                  * �Ȃ��Ȃ�(a4,b4)�𓮂����v���O�������ȍ~�Ŏg���񂷂̂�
					                  * (a4,b4)��-1���A(a5,b5)��-2�̍��W�����Ă���
					                  * 
					                  */
					                 a5 = a1;
					                 b5 = b1;
					                 a4 = a2;
					                 b4 = b2;
				                } 
				             }
								k++;
				 	
				}
			}
		}
			
			
			
			
			//-2��-1�̋����������ł߂����Ă���
				hirata2 =minimum;
			
				
			//hirata1��hirata2�̂ǂ��炪����������daiki1�ɑ��
			//daiki1��hirata1�̂Ƃ���2��1���߂��̂Ŏז�����v���O����
			//daiki1��hirata2�̂Ƃ���-2��-1���߂��̂ł���΂��ď����Ɍ������v���O����
			daiki1=hirata1;
			flag2 = 0;
			if(hirata2 <= hirata1){
				daiki1 = hirata2;
				flag2 = 1;
			}
			
			
			
			//daiki1��hirata1�̂Ƃ���2��1���߂��̂Ŏז�����v���O����
			//�܂�A2��-1���߂Â���
			
			if(flag2==0){
				minimum = 200;
				k = 1;
				for (int d = 1; d <= 8; d++) {
					for (int c = 1; c <= 7; c++) {
					 	status = kasouboard[c][d];
					 	if(status == 2){
		 					a1 = c;
		 					b1 = d;
		 					status1 = status;
					 	}
					}
				}
				for (int d = 1; d <= 8; d++) {
					for (int c = 1; c <= 7; c++) {
					 	status = kasouboard[c][d];
					 	if(status == -1){
									a2 = c;
									b2 = d;
									kyori[k] = ((a2-a1)*(a2-a1))+((b2-b1)*(b2-b1));
									if(kyori[k]<0){
										kyori[k]= -1*kyori[k];
									}
									
									//������̓f�o�b�O�p�̕\���v���O�����A�R�����g�ŏ��O���Ă����Ȃ�
									System.out.println(status1+"��(x,y)=("+a1+","+b1+")��"+status+"��(x,y)=("+a2+","+b2+")�̋�����"+kyori[k]);
									count3++;
									if( kyori[k] < minimum ){
						                 minimum = kyori[k];
						                /**��ƈ����
					                  * a4 = a1;
					                  * b4 = b1;
					                  * a5 = a2;
					                  * b5 = b2;
					                  * �ł͂Ȃ�
					                  * �Ȃ��Ȃ�(a4,b4)�𓮂����v���O�������ȍ~�Ŏg���񂷂̂�
					                  * (a4,b4)��-1���A(a5,b5)��-2�̍��W�����Ă���
					                  * 
					                  */
					                 a5 = a1;
					                 b5 = b1;
					                 a4 = a2;
					                 b4 = b2;
						                 
						             }
									if( kyori[k] == minimum ){
					                 Random rand = new Random();
					                int select = rand.nextInt(2);
					                if(select == 0){
					                	//�����O�Ȃ�A��̍ŒZ������I��	
					                }
					                if(select == 1){
					                	//�����P�Ȃ�A���Ƃ̍ŒZ������I��	
					                	 /**��ƈ����
						                  * a4 = a1;
						                  * b4 = b1;
						                  * a5 = a2;
						                  * b5 = b2;
						                  * �ł͂Ȃ�
						                  * �Ȃ��Ȃ�(a4,b4)�𓮂����v���O�������ȍ~�Ŏg���񂷂̂�
						                  * (a4,b4)��-1���A(a5,b5)��-2�̍��W�����Ă���
						                  * 
						                  */
						                 a5 = a1;
						                 b5 = b1;
						                 a4 = a2;
						                 b4 = b2;
					                } 
					             }
									k++;
					 	
					}
				}
			}

			/**+2��-1�̍ŏ������͕��������A�i�[�������A�ł�-1�����ۂɂǂ��ɓ���������œK���ȁH
			 */
				

				
				a41 = (a5-(a4-1))*(a5-(a4-1))+(b5-(b4-1))*(b5-(b4-1));
				a42 = (a5-(a4+0))*(a5-(a4+0))+(b5-(b4-1))*(b5-(b4-1));
				a43 = (a5-(a4+1))*(a5-(a4+1))+(b5-(b4-1))*(b5-(b4-1));
				
				a44 = (a5-(a4-1))*(a5-(a4-1))+(b5-(b4-0))*(b5-(b4-0));
				a45 = (a5-(a4+1))*(a5-(a4+1))+(b5-(b4-0))*(b5-(b4-0));			
				
				a46 = (a5-(a4-1))*(a5-(a4-1))+(b5-(b4+1))*(b5-(b4+1));
				a47 = (a5-(a4+0))*(a5-(a4+0))+(b5-(b4+1))*(b5-(b4+1));
				a48 = (a5-(a4+1))*(a5-(a4+1))+(b5-(b4+1))*(b5-(b4+1));

				mini_a = a41;
				if(a42 < mini_a){
					mini_a = a42;
				}
				if(a43 < mini_a){
					mini_a = a43;
				}
				if(a44 < mini_a){
					mini_a = a44;
				}
				if(a45 < mini_a){
					mini_a = a45;
				}
				if(a46 < mini_a){
					mini_a = a46;
				}
				if(a47 < mini_a){
					mini_a = a47;
				}
				if(a48 < mini_a){
					mini_a = a48;
				}
				
				if(mini_a == a41){
					kasouboard[a4-1][b4-1]= kasouboard[a4][b4] + kasouboard[a4-1][b4-1];
					kasouboard[a4][b4] = 0;
					a6 = a4-1;
					b6 = b4-1;
				}
				if(mini_a == a42){
					kasouboard[a4][b4-1]= kasouboard[a4][b4] + kasouboard[a4][b4-1];
					kasouboard[a4][b4] = 0;
					a6 = a4;
					b6 = b4-1;
				}
				if(mini_a == a43){
					kasouboard[a4+1][b4-1]= kasouboard[a4][b4] + kasouboard[a4+1][b4-1];
					kasouboard[a4][b4] = 0;
					a6 = a4+1;
					b6 = b4-1;
				}
				if(mini_a == a44){
					kasouboard[a4-1][b4]= kasouboard[a4][b4] + kasouboard[a4-1][b4];
					kasouboard[a4][b4] = 0;
					a6 = a4-1;
					b6 = b4;
				}
				if(mini_a == a45){
					kasouboard[a4+1][b4]= kasouboard[a4][b4] + kasouboard[a4+1][b4];
					kasouboard[a4][b4] = 0;
					a6 = a4+1;
					b6 = b4;
				}
				if(mini_a == a46){
					kasouboard[a4-1][b4+1]= kasouboard[a4][b4] + kasouboard[a4-1][b4+1];
					kasouboard[a4][b4] = 0;
					a6 = a4-1;
					b6 = b4+1;
				}
				if(mini_a == a47){
					kasouboard[a4][b4+1]= kasouboard[a4][b4] + kasouboard[a4][b4+1];
					kasouboard[a4][b4] = 0;
					a6 = a4;
					b6 = b4+1;
				}
				if(mini_a == a48){
					kasouboard[a4+1][b4+1]= kasouboard[a4][b4] + kasouboard[a4+1][b4+1];
					kasouboard[a4][b4] = 0;
					a6 = a4+1;
					b6 = b4+1;
				}
				
			}//daiki1=hirata1
			
			
		//daiki1��hirata2�̂Ƃ���-2��-1���߂��̂Ń��[�`�Ɍ������Ă���΂�v���O����
		//�܂�A2��-1���߂���
			
		if(flag2==1){	
			
			int kyori2[] = new int[20];
			k = 1;
			minimum = 200;
			
			for (int d = 1; d <= 8; d++) {
				for (int c = 1; c <= 7; c++) {
				 	status = kasouboard[c][d];
				 	if(status == -2){
	 					a1 = c;
	 					b1 = d;
	 					status1 = status;
	 					flag1 = 1;
				 	}
				}
			}
			
			
			for (int d = 1; d <= 8; d++) {
				for (int c = 1; c <= 7; c++) {
					status = kasouboard[c][d];
					if(status == -1){
						a2 = c;
						b2 = d;
						kyori2[k] = ((a2-a1)*(a2-a1))+((b2-b1)*(b2-b1));
						System.out.println(status1+"��("+a1+","+b1+")��"+status+"��("+a2+","+b2+")�̋�����"+kyori2[k]);
							//count3++;
							if( kyori2[k] < minimum ){
				                 minimum = kyori2[k];
				                 
				                 /**��ƈ����
				                  * a4 = a1;
				                  * b4 = b1;
				                  * a5 = a2;
				                  * b5 = b2;
				                  * �ł͂Ȃ�
				                  * �Ȃ��Ȃ�(a4,b4)�𓮂����v���O�������ȍ~�Ŏg���񂷂̂�
				                  * (a4,b4)��-1���A(a5,b5)��-2�̍��W�����Ă���
				                  * 
				                  */
				                 a5 = a1;
				                 b5 = b1;
				                 a4 = a2;
				                 b4 = b2;
				             }
							if( kyori[k] == minimum ){
				                 Random rand = new Random();
				                int select = rand.nextInt(2);
				                if(select == 0){
				                	//�����O�Ȃ�A��̍ŒZ������I��	
				                }
				                if(select == 1){
				                	//�����P�Ȃ�A���Ƃ̍ŒZ������I��	
				                	 /**��ƈ����
					                  * a4 = a1;
					                  * b4 = b1;
					                  * a5 = a2;
					                  * b5 = b2;
					                  * �ł͂Ȃ�
					                  * �Ȃ��Ȃ�(a4,b4)�𓮂����v���O�������ȍ~�Ŏg���񂷂̂�
					                  * (a4,b4)��-1���A(a5,b5)��-2�̍��W�����Ă���
					                  * 
					                  */
					                 a5 = a1;
					                 b5 = b1;
					                 a4 = a2;
					                 b4 = b2;
				                } 
				             }
						k++;
					}
				}
			}
			
			a41 = (a5-(a4-1))*(a5-(a4-1))+(b5-(b4-1))*(b5-(b4-1));
			a42 = (a5-(a4+0))*(a5-(a4+0))+(b5-(b4-1))*(b5-(b4-1));
			a43 = (a5-(a4+1))*(a5-(a4+1))+(b5-(b4-1))*(b5-(b4-1));
			
			a44 = (a5-(a4-1))*(a5-(a4-1))+(b5-(b4-0))*(b5-(b4-0));
			a45 = (a5-(a4+1))*(a5-(a4+1))+(b5-(b4-0))*(b5-(b4-0));			
			
			a46 = (a5-(a4-1))*(a5-(a4-1))+(b5-(b4+1))*(b5-(b4+1));
			a47 = (a5-(a4+0))*(a5-(a4+0))+(b5-(b4+1))*(b5-(b4+1));
			a48 = (a5-(a4+1))*(a5-(a4+1))+(b5-(b4+1))*(b5-(b4+1));

			mini_a = a41;
			if(a42 < mini_a){
				mini_a = a42;
			}
			if(a43 < mini_a){
				mini_a = a43;
			}
			if(a44 < mini_a){
				mini_a = a44;
			}
			if(a45 < mini_a){
				mini_a = a45;
			}
			if(a46 < mini_a){
				mini_a = a46;
			}
			if(a47 < mini_a){
				mini_a = a47;
			}
			if(a48 < mini_a){
				mini_a = a48;
			}
			
			if(mini_a == a41){
				kasouboard[a4-1][b4-1]= kasouboard[a4][b4] + kasouboard[a4-1][b4-1];
				kasouboard[a4][b4] = 0;
				a6 = a4-1;
				b6 = b4-1;
			}
			if(mini_a == a42){
				kasouboard[a4][b4-1]= kasouboard[a4][b4] + kasouboard[a4][b4-1];
				kasouboard[a4][b4] = 0;
				a6 = a4;
				b6 = b4-1;
			}
			if(mini_a == a43){
				kasouboard[a4+1][b4-1]= kasouboard[a4][b4] + kasouboard[a4+1][b4-1];
				kasouboard[a4][b4] = 0;
				a6 = a4+1;
				b6 = b4-1;
			}
			if(mini_a == a44){
				kasouboard[a4-1][b4]= kasouboard[a4][b4] + kasouboard[a4-1][b4];
				kasouboard[a4][b4] = 0;
				a6 = a4-1;
				b6 = b4;
			}
			if(mini_a == a45){
				kasouboard[a4+1][b4]= kasouboard[a4][b4] + kasouboard[a4+1][b4];
				kasouboard[a4][b4] = 0;
				a6 = a4+1;
				b6 = b4;
			}
			if(mini_a == a46){
				kasouboard[a4-1][b4+1]= kasouboard[a4][b4] + kasouboard[a4-1][b4+1];
				kasouboard[a4][b4] = 0;
				a6 = a4-1;
				b6 = b4+1;
			}
			if(mini_a == a47){
				kasouboard[a4][b4+1]= kasouboard[a4][b4] + kasouboard[a4][b4+1];
				kasouboard[a4][b4] = 0;
				a6 = a4;
				b6 = b4+1;
			}
			if(mini_a == a48){
				kasouboard[a4+1][b4+1]= kasouboard[a4][b4] + kasouboard[a4+1][b4+1];
				kasouboard[a4][b4] = 0;
				a6 = a4+1;
				b6 = b4+1;
			}
			
			
			
			
			
			
			
			
			
			
			
			
		}//daiki1==hirata2
			
			
			
			
			
			
			
			
			
			
			
		}//flag1==4
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		System.out.println("�����(x,y)=("+a4+","+b4+")��(x,y)=("+a6+","+b6+")�Ɉړ����܂���");

		beforeS = a4-1;
		beforeT = b4-1;
		s = a6-1;
		t = b6-1;
		//board.Tutuku(cx ,b4-1 ,a4-1,b6-1,a6-1);
		//}
		//}
		
	}

	public int getS(){
		return s;
	}
	public int getT(){
		return t;
	}
	public int getBeforeS(){
		return beforeS;
	}
	public int getBeforeT(){
		return beforeT;
	}


}
