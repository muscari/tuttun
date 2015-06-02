package com.android.tuttun;
import java.util.Timer;

import java.util.TimerTask;

import java.util.Random;

public class AI {

	int beforeS;
	int beforeT;
	int s;
	int t;
	
	/*AIを実装するに当たり・・・
	 * 
	 * GameControlにて
	 * 序盤に
	 * 	AI ai = new AI();
	 * 	int aiflag =0;
	 * 
	 * 中盤のlogicStatus==10の}の直後に
	 *
	 * aiflag ++;
		if(aiflag==2){
			ai.calc(board);
			aiflag =0;
		}
	 * を記入している
	 * 
	 * また、ずっとplayer== -1のターンにするために
	 * //player = -1 * player;
	 * のようにプレイヤー交換式を全てコメント化している
	 * 
	 * おしてlogicstatus = 0 の後には微調整のため
	 * 			aiflag --;
	 * と記述している
	 * */
	
	
	
	public void calc(Board board){
		
		//board.getBoardStatus(1, 1);
		
		//(y+1,x+1,中身)
		//board.setBoardStatus(1, 1, 1);

		//(中身、移動前y+1、移動前x+1、移動先y+1、移動先x+1)
		//board.move(-1,2,1,3,1);
		
		
		int kasouboard[][] = new int[7+2][8+2]; 
		
		int a = 1;
		int b = 1;
		
		/**Andoridでは1Pが-1,CPUが+1を操る
		 * cxはmoveとかで、移動させるのはどっちかを決定する際に
		 * 利用する
		 */
		int cx = 1;






		//ここからボードの情報を仮想ボードに代入
		for (int j = 1; j <= 8; j++) {
			for (int i = 1; i <= 7; i++) {
				
				//(i,j)ではなく(j,i)であることに注意
				//なぜなら外園は(y座標、x座標)としているから
				//また、代入の際は外園は左上を(0,0)にしているが、俺は(1,1)としているため、それも気をつけよう
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
		System.out.println("仮想ボードの表示↓");
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
		
		
		
		
		/**条件分岐プログラム
		 * flag1==0		仮想ボードに-1しかないので、それを近づけていく。しかも移動にあたってつっつくは発動しなさそう
		 * flag1==1		仮想ボードに-2があるので、それに-1を近づけていく。しかも移動にあたってつっつくは発動しなさそう
		 * flag1==2		
		 * flag1==3		仮想ボードに2があるので、クリアを邪魔すべくそれに-1を近づけていく
		 */
		int flag1 = 0;
		int flag2 = 0;
		
		
		//優先度が高いのが下に来ている
		//今回はflag1==3よりflag1==1を優先している
		
		
		
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
		
		
		
		
		
		
		
		


		//左上から検索して、１つめのコマを原点として２つめ以降の距離を計算
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
 								
 								//↓これはデバッグ用の表示プログラム、コメントで除外しても問題ない
 								System.out.println((status1)+"の(x,y)=("+a1+","+b1+")と"+status+"の(x,y)=("+a2+","+b2+")の距離は"+kyori[k]);
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
					                	//もし０なら、先の最短距離を選択	
					                }
					                if(select == 1){
					                	//もし１なら、あとの最短距離を選択	
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
		
		//　X個目（X＝１，２・・）のコマの後から検索して、Y個目（Y＝２，３・・）のコマを原点として２つめ以降の距離を計算
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
 								
 								//↓これはデバッグ用の表示プログラム、コメントで除外しても問題ない
 								System.out.println((status1)+"の(x,y)=("+a1+","+b1+")と"+status+"の(x,y)=("+a2+","+b2+")の距離は"+kyori[k]);
 								
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
					                	//もし０なら、先の最短距離を選択	
					                }
					                if(select == 1){
					                	//もし１なら、あとの最短距離を選択	
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
		
		System.out.println("最小距離は(x,y)=("+a4+","+b4+")と(x,y)=("+a5+","+b5+")で距離は"+minimum);

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		//つっつんというゲームの性格上、できるだけコマが真ん中に寄って行くよう駒を動かす、中心から遠い方のコマがどちらか算出
		
		//中心座標を(4,5)とする
		int center_x = 4;
		int center_y = 5; 
		
		//中心から(a4,b4)と(a5,b5)までの距離を算出
		int center_r4 = (a4-center_x)*(a4-center_x)+(b4-center_y)*(b4-center_y);
		int center_r5 = (a5-center_x)*(a5-center_x)+(b5-center_y)*(b5-center_y);
		
		//それぞれのコマへの距離の遠い方を算出
		int center_r = center_r4;
		if (center_r5 > center_r4){
			center_r = center_r5;
		}
		
		//この式により、動かすコマは必ず(a4,b4)となる
		if(center_r==center_r5){
			a6 = a5;
			b6 = b5;
			a5 = a4;
			b5 = b4;
			a5 = a6;
			b5 = b6;
		}
		
		//(a4,b4)を試しに移動可能範囲で移動させてみて（８方向１マス）、それぞれと、(a5,b5)との距離を算出
		//８方向は(左上、上、右上、左、右、左下、下、右下)の順番
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

		//(a4,b4)のコマをどこかに動かすことで、最小値が出た。　では、具体的にどこに動かしたら最小値がでた？ 一致したときにコマを動かす
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
						System.out.println(status1+"の("+a1+","+b1+")と"+status+"の("+a2+","+b2+")の距離は"+kyori2[k]);
							//count3++;
							if( kyori2[k] < minimum ){
				                 minimum = kyori2[k];
				                 
				                 /**上と違って
				                  * a4 = a1;
				                  * b4 = b1;
				                  * a5 = a2;
				                  * b5 = b2;
				                  * ではない
				                  * なぜなら(a4,b4)を動かすプログラムを以降で使い回すので
				                  * (a4,b4)に-1を、(a5,b5)に-2の座標を入れておく
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
				                	//もし０なら、先の最短距離を選択	
				                }
				                if(select == 1){
				                	//もし１なら、あとの最短距離を選択	
				                	 /**上と違って
					                  * a4 = a1;
					                  * b4 = b1;
					                  * a5 = a2;
					                  * b5 = b2;
					                  * ではない
					                  * なぜなら(a4,b4)を動かすプログラムを以降で使い回すので
					                  * (a4,b4)に-1を、(a5,b5)に-2の座標を入れておく
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
		System.out.println("-2と-1の最小距離は(x,y)=("+a5+","+b5+")と(x,y)=("+a4+","+b4+")で距離は"+minimum);
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
								
								//↓これはデバッグ用の表示プログラム、コメントで除外しても問題ない
								System.out.println(status1+"の(x,y)=("+a1+","+b1+")と"+status+"の(x,y)=("+a2+","+b2+")の距離は"+kyori[k]);
								count3++;
								if( kyori[k] < minimum ){
					                 minimum = kyori[k];
					                /**上と違って
				                  * a4 = a1;
				                  * b4 = b1;
				                  * a5 = a2;
				                  * b5 = b2;
				                  * ではない
				                  * なぜなら(a4,b4)を動かすプログラムを以降で使い回すので
				                  * (a4,b4)に-1を、(a5,b5)に-2の座標を入れておく
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
				                	//もし０なら、先の最短距離を選択	
				                }
				                if(select == 1){
				                	//もし１なら、あとの最短距離を選択	
				                	 /**上と違って
					                  * a4 = a1;
					                  * b4 = b1;
					                  * a5 = a2;
					                  * b5 = b2;
					                  * ではない
					                  * なぜなら(a4,b4)を動かすプログラムを以降で使い回すので
					                  * (a4,b4)に-1を、(a5,b5)に-2の座標を入れておく
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

		/**+2と-1の最小距離は分かった、格納もした、では-1を実際にどこに動かしたら最適化な？
		 */
			if(flag1 == 3){
				System.out.println("2と-1の最小距離は(x,y)=("+a5+","+b5+")と(x,y)=("+a4+","+b4+")で距離は"+minimum);
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
		
		/*ここからお互いがリーチ状態のときのプログラムを書きたす*/
		
		
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
								
								//↓これはデバッグ用の表示プログラム、コメントで除外しても問題ない
								System.out.println(status1+"の(x,y)=("+a1+","+b1+")と"+status+"の(x,y)=("+a2+","+b2+")の距離は"+kyori[k]);
								count3++;
								if( kyori[k] < minimum ){
					                 minimum = kyori[k];
					                /**上と違って
				                  * a4 = a1;
				                  * b4 = b1;
				                  * a5 = a2;
				                  * b5 = b2;
				                  * ではない
				                  * なぜなら(a4,b4)を動かすプログラムを以降で使い回すので
				                  * (a4,b4)に-1を、(a5,b5)に-2の座標を入れておく
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
				                	//もし０なら、先の最短距離を選択	
				                }
				                if(select == 1){
				                	//もし１なら、あとの最短距離を選択	
				                	 /**上と違って
					                  * a4 = a1;
					                  * b4 = b1;
					                  * a5 = a2;
					                  * b5 = b2;
					                  * ではない
					                  * なぜなら(a4,b4)を動かすプログラムを以降で使い回すので
					                  * (a4,b4)に-1を、(a5,b5)に-2の座標を入れておく
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
			
		//2と1の距離をここでめもっておく
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
								
								//↓これはデバッグ用の表示プログラム、コメントで除外しても問題ない
								System.out.println(status1+"の(x,y)=("+a1+","+b1+")と"+status+"の(x,y)=("+a2+","+b2+")の距離は"+kyori[k]);
								count3++;
								if( kyori[k] < minimum ){
					                 minimum = kyori[k];
					                /**上と違って
				                  * a4 = a1;
				                  * b4 = b1;
				                  * a5 = a2;
				                  * b5 = b2;
				                  * ではない
				                  * なぜなら(a4,b4)を動かすプログラムを以降で使い回すので
				                  * (a4,b4)に-1を、(a5,b5)に-2の座標を入れておく
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
				                	//もし０なら、先の最短距離を選択	
				                }
				                if(select == 1){
				                	//もし１なら、あとの最短距離を選択	
				                	 /**上と違って
					                  * a4 = a1;
					                  * b4 = b1;
					                  * a5 = a2;
					                  * b5 = b2;
					                  * ではない
					                  * なぜなら(a4,b4)を動かすプログラムを以降で使い回すので
					                  * (a4,b4)に-1を、(a5,b5)に-2の座標を入れておく
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
			
			
			
			
			//-2と-1の距離をここでめもっておく
				hirata2 =minimum;
			
				
			//hirata1とhirata2のどちらが小さいかをdaiki1に代入
			//daiki1がhirata1のときは2と1が近いので邪魔するプログラム
			//daiki1がhirata2のときは-2と-1が近いのでがんばって勝ちに向かうプログラム
			daiki1=hirata1;
			flag2 = 0;
			if(hirata2 <= hirata1){
				daiki1 = hirata2;
				flag2 = 1;
			}
			
			
			
			//daiki1がhirata1のときは2と1が近いので邪魔するプログラム
			//つまり、2と-1を近づける
			
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
									
									//↓これはデバッグ用の表示プログラム、コメントで除外しても問題ない
									System.out.println(status1+"の(x,y)=("+a1+","+b1+")と"+status+"の(x,y)=("+a2+","+b2+")の距離は"+kyori[k]);
									count3++;
									if( kyori[k] < minimum ){
						                 minimum = kyori[k];
						                /**上と違って
					                  * a4 = a1;
					                  * b4 = b1;
					                  * a5 = a2;
					                  * b5 = b2;
					                  * ではない
					                  * なぜなら(a4,b4)を動かすプログラムを以降で使い回すので
					                  * (a4,b4)に-1を、(a5,b5)に-2の座標を入れておく
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
					                	//もし０なら、先の最短距離を選択	
					                }
					                if(select == 1){
					                	//もし１なら、あとの最短距離を選択	
					                	 /**上と違って
						                  * a4 = a1;
						                  * b4 = b1;
						                  * a5 = a2;
						                  * b5 = b2;
						                  * ではない
						                  * なぜなら(a4,b4)を動かすプログラムを以降で使い回すので
						                  * (a4,b4)に-1を、(a5,b5)に-2の座標を入れておく
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

			/**+2と-1の最小距離は分かった、格納もした、では-1を実際にどこに動かしたら最適化な？
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
			
			
		//daiki1がhirata2のときは-2と-1が近いのでリーチに向かってがんばるプログラム
		//つまり、2と-1を近ける
			
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
						System.out.println(status1+"の("+a1+","+b1+")と"+status+"の("+a2+","+b2+")の距離は"+kyori2[k]);
							//count3++;
							if( kyori2[k] < minimum ){
				                 minimum = kyori2[k];
				                 
				                 /**上と違って
				                  * a4 = a1;
				                  * b4 = b1;
				                  * a5 = a2;
				                  * b5 = b2;
				                  * ではない
				                  * なぜなら(a4,b4)を動かすプログラムを以降で使い回すので
				                  * (a4,b4)に-1を、(a5,b5)に-2の座標を入れておく
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
				                	//もし０なら、先の最短距離を選択	
				                }
				                if(select == 1){
				                	//もし１なら、あとの最短距離を選択	
				                	 /**上と違って
					                  * a4 = a1;
					                  * b4 = b1;
					                  * a5 = a2;
					                  * b5 = b2;
					                  * ではない
					                  * なぜなら(a4,b4)を動かすプログラムを以降で使い回すので
					                  * (a4,b4)に-1を、(a5,b5)に-2の座標を入れておく
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		System.out.println("よって(x,y)=("+a4+","+b4+")を(x,y)=("+a6+","+b6+")に移動しました");

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
