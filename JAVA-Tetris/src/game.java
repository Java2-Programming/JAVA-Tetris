import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;


public class game extends JFrame {
   Container c = getContentPane();
   TetrisPanel TP = new TetrisPanel();
   JDialog JD = new JDialog(); //점수
   TetrisThread th;
   
   static int blocksize = 20;
   
   int End = 0;
   int random = 0 , random2 = (int)(Math.random()*7);
   
   int score = 0;
   
   int speed_Value = 1; //속도 조정 변수
   
   int wid=100;
   int hgt= 0;
   int rotation = 0;
   
   boolean isPause = false;
   boolean limit = false;
   
   int curX[]= new int[4], curY[] = new int [4]; // 블록들의 좌표 저장
   
   int blocks[][][][]  = 
      {
         {
            //■
            //■■■
            {
               {0,0,0,0},
               {1,0,0,0},
               {1,1,1,0},
               {0,0,0,0}
            },
            {
               {0,1,1,0},
               {0,1,0,0},
               {0,1,0,0},
               {0,0,0,0}
            },
            {
               {1,1,1,0},
               {0,0,1,0},
               {0,0,0,0},
               {0,0,0,0}
            },
            {
               {0,0,1,0},
               {0,0,1,0},
               {0,1,1,0},
               {0,0,0,0}
            }
         },
         {

               //  ■
               //■■■
            {
               {0,0,0,0},
               {0,0,1,0},
               {1,1,1,0},
               {0,0,0,0}
            },
            {
               {0,1,0,0},
               {0,1,0,0},
               {0,1,1,0},
               {0,0,0,0}
            },
            {
               {0,0,0,0},
               {1,1,1,0},
               {1,0,0,0},
               {0,0,0,0}
            },
            {
               {0,1,1,0},
               {0,0,1,0},
               {0,0,1,0},
               {0,0,0,0}
            }
         },
         {
               //  ■■
               //  ■■
            {
               {0,0,0,0},
               {1,1,0,0},
               {1,1,0,0},
               {0,0,0,0}
            },
            {
               {0,0,0,0},
               {1,1,0,0},
               {1,1,0,0},
               {0,0,0,0}
            },
            {
               {0,0,0,0},
               {1,1,0,0},
               {1,1,0,0},
               {0,0,0,0}
            },
            {
               {0,0,0,0},
               {1,1,0,0},
               {1,1,0,0},
               {0,0,0,0}
            }
         },
         {
               // ■■■■
            {
               {0,0,0,0},
               {0,0,0,0},
               {1,1,1,1},
               {0,0,0,0}
            },
            {
               {0,1,0,0},
               {0,1,0,0},
               {0,1,0,0},
               {0,1,0,0}
            },
            {
               {0,0,0,0},
               {0,0,0,0},
               {1,1,1,1},
               {0,0,0,0}
            },
            {
               {0,1,0,0},
               {0,1,0,0},
               {0,1,0,0},
               {0,1,0,0}
            }
         },
         {
                //■
               //■■■
            {
               {0,0,0,0},
               {0,1,0,0},
               {1,1,1,0},
               {0,0,0,0}
            },
            {
               {0,1,0,0},
               {0,1,1,0},
               {0,1,0,0},
               {0,0,0,0}
            },
            {
               {0,0,0,0},
               {1,1,1,0},
               {0,1,0,0},
               {0,0,0,0}
            },
            {
               {0,1,0,0},
               {1,1,0,0},
               {0,1,0,0},
               {0,0,0,0}
            }   
         },
         {
                //  ■■
                //   ■■
            {
               {0,0,0,0},
               {1,1,0,0},
               {0,1,1,0},
               {0,0,0,0}
            },
            {
               {0,0,1,0},
               {0,1,1,0},
               {0,1,0,0},
               {0,0,0,0}
            },
            {
               {0,0,0,0},
               {1,1,0,0},
               {0,1,1,0},
               {0,0,0,0}
            },
            {
               {0,0,1,0},
               {0,1,1,0},
               {0,1,0,0},
               {0,0,0,0}
            }
         },
         {
                //  ■■
               //  ■■
            {
               {0,0,0,0},
               {0,1,1,0},
               {1,1,0,0},
               {0,0,0,0}
            },
            {
               {0,1,0,0},
               {0,1,1,0},
               {0,0,1,0},
               {0,0,0,0}
            },
            {
               {0,0,0,0},
               {0,1,1,0},
               {1,1,0,0},
               {0,0,0,0}
            },
            {
               {0,1,0,0},
               {0,1,1,0},
               {0,0,1,0},
               {0,0,0,0}
            }   
         }
   };
   
   public final int XVALUE = 12; //총 가로
   public final int YVALUE = 19; //총 세로
   public final int STARTX = 1; //0의 시작 포인트
   int[][] gameboard = {{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                     {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                     {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

   //버튼 생성, 라벨 생성
   JButton btn = new JButton("재도전");
   JButton btn1 = new JButton("정 지");
   JButton btn2 = new JButton("속도 업");
   JButton btn3 = new JButton("속도 다운");
   JButton btn4 = new JButton("재 개");
   JLabel lb = new JLabel(); //테트리스 타이틀
   JLabel lbl = new JLabel(); //점수
   JLabel lbl2 = new JLabel(); //점수 숫자
   JLabel lbl3 = new JLabel(); //속도
   JLabel lbl4 = new JLabel(); //속도숫자
   
   //성욱
   ImageIcon i1 = new ImageIcon("images/Restart.png");
   JButton btnn = new JButton(i1);
   JLabel sc = new JLabel("Score");
   ImageIcon low = new ImageIcon("images/low.png");
   ImageIcon middle = new ImageIcon("images/middle.png");
   ImageIcon high = new ImageIcon("images/high.png");
   JLabel low1 = new JLabel(low);
   JLabel middle1 = new JLabel(middle);
   JLabel high1 = new JLabel(high);
   //성욱
   
   game(){
      setTitle("테트리스");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(null);
      TP.setSize(1280,800); //이게 게임화면 해상도
      
      add(TP);
      
      th = new TetrisThread();
      
      // JDialog 
      JD.setTitle("점수");
      JD.setSize(250,190);
      JD.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 30));
      
      
      lb.setText("테 트 리 스");
      lb.setFont(new Font("나눔고딕",Font.PLAIN,20));
      
      lbl.setFont(new Font("arial",Font.PLAIN,15));
      lbl2.setText("점  수");
      lbl2.setFont(new Font("나눔고딕",Font.PLAIN,15));
      
      lbl3.setText("속  도");
      lbl3.setFont(new Font("나눔고딕",Font.PLAIN,15));
      
      lbl4.setFont(new Font("arial",Font.PLAIN,15));
      
      
      TP.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
               int keyCode = e.getKeyCode();
                        
               if(keyCode == KeyEvent.VK_UP)
                  TP.moveUp();
               if(keyCode == KeyEvent.VK_DOWN)
                  TP.moveDown();
               if(keyCode == KeyEvent.VK_LEFT)
                  TP.moveLeft();
               if(keyCode == KeyEvent.VK_RIGHT)
                  TP.moveRight();
            }
         });
      
      //버튼 리스너
      btnn.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
        	 //성욱
        	JD.remove(high1);
            JD.remove(middle1);
            JD.remove(low1); 
            //성욱
        	 
            limit = false;
            for(int y=0; y<YVALUE;y++)
               for(int x=STARTX; x<XVALUE; x++)
                  gameboard[y][x] =0 ;
            score =0;
            wid =100; hgt = 0;
         }
      });
      
      btn1.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
        	  isPause = true; //정지
          }
       });
      
      btn2.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
        	  if(speed_Value<3) //총 속도 3까지 가능
        		  speed_Value++;
          }
       });
      
      btn3.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
        	  if(speed_Value>1) //속도가 0이 되면 안되게 최소 1로 설정
        		  speed_Value--;
          }
       });
      
      btn4.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
        	  
        	  isPause = false; //재개
          }
       });
      
      TP.setBackground(Color.WHITE);
      setSize(1280,800); //이게 화면 해상도
      setVisible(true);


      // 화면 중앙 정렬
      Dimension frameSize = this.getSize();
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
      JD.setLocation((screenSize.width - frameSize.width)/2 + 220, (screenSize.height - frameSize.height)/2 +220);
      
      
      TP.requestFocus(true);
      th.start();
   }
   
   
   
   class TetrisPanel extends JPanel{
      public void paintComponent(Graphics g){
         
         int cnt = 0 , cnt2 = 0;
         TP.requestFocus(true);
         super.paintComponent(g);
         
         
         lb.setLocation(550,100); //테트리스 타이틀
         TP.add(lb);
         
         lbl2.setLocation(953,320); //점수 위치
         TP.add(lbl2);
         
         lbl3.setLocation(953,120); //속도 위치
         TP.add(lbl3);
         
         lbl.setLocation(960,345);  //숫자 점수 위치
         TP.add(lbl);
         lbl.setText(Integer.toString(score*100));
         
         lbl4.setLocation(970,170);  //속도 숫자 위치
         TP.add(lbl4);
         lbl4.setText(Integer.toString(speed_Value));
         
         btn1.setLocation(40,50);  //버튼 정지 위치
         TP.add(btn1);
         
         btn2.setLocation(893,140);  //버튼 속도 업 위치
         TP.add(btn2);
         
         btn3.setLocation(983,140);  //버튼 속도 다운 위치
         TP.add(btn3);
         
         btn4.setLocation(140,50);  //버튼 재개 위치
         TP.add(btn4);
         
         g.setColor(Color.ORANGE); // 새로 떨어지는 블럭,미리보기  블럭 색깔
         
         // 다음 나올 도형 출력
         blockLookAhead(g);
         
         // 벽이 천장에 닿으면 게임 오버
         gameOverCheck();
                
         // 한 행이 모두 블록으로 채워진 경우 블록들 제거(채워지지않은 경우 블록 떨어지도록)
         removeBlock(cnt, cnt2, g);
         
         // 블록이 벽에 착지하면 블록->벽으로 변환(떨어지는 블록 초기화)
         blockToWall();
         
         // 벽들을 생성
         makeBlock(g);
         
         // 테두리 생성
         makeBorder(g);
       
       
         if(End == 1){
            random2 = (int)(Math.random()*7);
            End = 0;
         }
      }
      
   // 다음 나올 도형 출력
      public void blockLookAhead(Graphics g){
         for(int a = 0; a<4 ;a++){
              for(int b = 0; b<4;b++){
                 if(blocks[random2][0][a][b] == 1){
                    g.fill3DRect(b*blocksize+250, a*blocksize, blocksize, blocksize, true);
                 }
              }
          }
      }
      
      //게임오버시 나타나는것
      public void gameOverCheck(){
         for(int x=STARTX;x<XVALUE;x++)
             if(gameboard[2][x]==1){
                limit = true;
                //성욱
                JD.setSize(300, 400);
               
                JD.add(sc);
                //lbl.setLocation(50,50);
                JD.add(lbl);
                JD.add(btnn);
               
                //btn.setLocation(50,30);
                JD.setVisible(true);
                
                //성욱
                if(score == 0)
                	JD.add(low1);
                else if(score >0 && score < 10)
                	JD.add(middle1);
                else
                	JD.add(high1);
             }
      }
      
      public void removeBlock(int cnt, int cnt2, Graphics g){
         for(int y =0;y<YVALUE;y++){
             for(int x =STARTX; x<XVALUE ; x++){
                if(gameboard[y][x] == 1){
                   cnt2++;
                }
             }
             if(cnt2 == 11){
                for(int i=y;i>1;i--)
                   for(int j=1;j<13;j++){
                      gameboard[i][j] = 0;
                      gameboard[i][j] = gameboard[i-1][j];
                   }
               score++;
             }else{
                blockDown(cnt,g); // 한 행이 모두 블록으로 채워지지 않을 때만 블록이 내려가도록 함
             }
             cnt2 = 0 ;
          }
      }
      
      public void makeBlock(Graphics g){
         g.setColor(Color.GRAY); 
          for(int y=0; y<YVALUE;y++){
             for(int x=STARTX; x<XVALUE; x++){
                if(gameboard[y][x]== 1){
                   g.fill3DRect( x*blocksize+470, y*blocksize+160, blocksize, blocksize, true);  //블록위치
                }
             }
          }
      }
      
      public void blockDown(int cnt, Graphics g){
         for(int j = 0; j<4 ;j++){
              for(int k = 0; k<4;k++){
                 if(blocks[random][rotation][j][k] == 1){
                    curX[cnt] = ((k*blocksize)+wid)/blocksize; curY[cnt] = ((j*blocksize)+hgt)/blocksize;//curX,Y[0][1][2][3]에 좌표 4개 저장
                    g.fill3DRect(curX[cnt]*blocksize+470, curY[cnt]*blocksize+160, blocksize, blocksize, true);   //블록위치
                    
                    cnt ++;
                 }
              }
           }
      }
      
      // 떨어지던 블록이 벽이 되는지 검사
      // 벽이 되면 wid=120, hgt=0 으로 블록 초기화, rotation도 초기화 
      public void blockToWall(){
         try{
         for(int z = 0; z<4 ; z++)
              if(gameboard[curY[z]+1][curX[z]] == 1)
                    for (int j= 0; j<4;j++){
                      
                          gameboard[curY[j]][curX[j]] = 1;
                          wid =100; 
                          hgt =0;
                          End = 1;
                          rotation = 0;
                          random = random2;
                    }
         }catch(ArrayIndexOutOfBoundsException e){ System.out.println("Error");
         for(int i=0; i<4 ; i++)
               System.out.print("("+curY[i]+","+curX[i]+")");
            System.out.println();}
        
         
      }
      
      // 왼쪽 벽에 충돌하면 못움직이도록
      public int collision_LEFT(){
         for(int i=0; i<4; i++){
            if(gameboard[curY[i]][curX[i]-1] == 1)  // 충돌시 1 반환
               return 1;
         }
         return 0; // 충돌하지 않으면 0 반환
      }
      
      // 오른쪽 벽에 충돌하면 못 움직이도록
      public int collision_RIGHT(){

         for(int i=0; i<4; i++){
            if(gameboard[curY[i]][curX[i]+1] == 1)   // 충돌시 1반환
               return 1;
         }
         return 0; // 충돌하지 않으면 0반환
      }
      
      // curX,Y에 다음 회전 도형의 절대좌표를 모두 기록해두고, 만약 오른쪽이나 왼쪽 X좌표1,2칸 안에 벽이 있으면 그만큼 오른쪽 혹은 왼쪽으로 밀어서 배치
      public void rotationCheck(){
       // curX,Y에 다음 회전 도형의 절대좌표를 모두 기록해두고, 밑에 구문에서 그 절대좌표의 값이 벽에 닿는지 판단
         int cnt2=0;
          for(int j = 0; j<4 ;j++){
              for(int k = 0; k<4;k++){
                 int rotation2 = (rotation%4)+1 ;
                 if(rotation2 == 4)
                    rotation2 = 0;
                 if(blocks[random][rotation2][j][k] == 1){
                    curX[cnt2] = ((k*blocksize)+wid)/blocksize; curY[cnt2] = ((j*blocksize)+hgt)/blocksize;
                    cnt2++;
                 }    
              }
          }
          
       // curX,Y에 저장된 좌표를 이용
          int chk = 0;
          int blank =0;
          int error = 0;
           // 왼쪽 벽
             
                
                      if(gameboard[curY[0]][curX[0]] == 1 || (random == 6 && gameboard[curY[2]][curX[2]] == 1) || (random == 1 && gameboard[curY[1]][curX[1]] ==1 )){
                         chk = 1; // 만약 다음 회전한 도형의 위치가 벽과 겹친다면 chk=1로 표시함           
                         error++;
                         System.out.println("chk1");
                         if(random == 3){ // 일자막대의 경우 회전할 여유가 있는 공백이 없으면 회전막음
                            for(int i=1;i<5;i++)
                               if(gameboard[curY[0]][curX[0]+i] == 0)
                                  blank++;
                            if(blank < 4)
                               chk = 4;
                            
                              System.out.println(blank);
                         }else{ // 그 외의 경우 회전할 여유가 없는 공백이 없으면 회전 막음
                            for(int i=1; i<4;i++)
                               if(gameboard[curY[0]][curX[0]+i] == 0)
                                  blank++;
                            if(blank <3)
                               chk = 4;
                            System.out.println("blank2");
                            System.out.println(blank);
                         }
                         
                      }
            
          //오른쪽 벽
             
                 
                      else if(gameboard[curY[2]][curX[2]] == 1){
                        error++;
                       chk = 2; // 만약 다음 회전한 도형의 위치가 벽과 겹친다면 chk=2로 표시함  
                       System.out.println("chk2");
                       
                       for(int i=1; i<5;i++)
                          if(gameboard[curY[2]][curX[2]-i] == 0)
                             blank ++;
                       if(blank < 4)
                          chk = 4;
                       System.out.println("blank2");
                       System.out.println(blank);
                             
                       
                    }
                      else if(gameboard[curY[3]][curX[3]] == 1){
                       error++;
                       chk = 3; // 만약 다음 회전한 도형의 위치가 벽과 겹친다면 chk=3로 표시함    
                       System.out.println("chk3");
                       for(int i=0; i<5;i++)
                          if(gameboard[curY[3]][curX[3]-i] == 0)
                             blank ++;
                       if(blank < 4)
                          chk = 4;
                       System.out.println(blank);
                       
                    }
                 
             
          
          
          if(chk == 1){ // chk = 1(다음 회전한 도형의 위치가 벽과 중복되면)면 wid(가로)로 30이동
             wid += blocksize;
             rotation++;
             rotation = rotation%4;
            }else if (chk ==2){
               wid -= blocksize*2;
               rotation++;
               rotation = rotation%4;
            }else if (chk ==3){
               wid -= blocksize;
               rotation++;
               rotation = rotation%4;
            }else if(chk == 4){
               System.out.println("ban");
            }else{
               rotation++;
                rotation = rotation%4;
            }
      
          
      }
      
      public void makeBorder(Graphics g){  //기둥들 위치
         g.setColor(Color.GRAY);
         
         g.draw3DRect(478, 170, 5, 375,true); // 기둥
         g.draw3DRect(715, 170, 5, 375, true); // 기둥
         g.draw3DRect(465, 545, 270, 5,true); // 바닥
         g.draw3DRect(465, 165, 270, 5, true); // 천장
      }
      
      void down(){
         hgt +=blocksize;
         TP.repaint();
      }
      void moveUp(){
         rotationCheck();
          if(limit == false)
             repaint();
      }
      void moveDown(){
         if(limit == false){
            hgt += blocksize;
            TP.repaint();
         }
      }
      void moveLeft(){
         int sel = collision_LEFT();// sel이 1이면 충돌, 0이면 충돌X
         if(sel == 0 && limit == false){
            wid -= blocksize;
               TP.repaint();
         }
      }
      void moveRight(){
         int sel = collision_RIGHT();// sel이 1이면 충돌, 0이면 충돌X
         if(sel == 0 && limit == false){ 
            wid += blocksize;
            TP.repaint();
         }
      }
   }
   
   class TetrisThread extends Thread{
      TetrisPanel TP = new TetrisPanel();
      public void run(){
         while(true){
            try{
            	sleep(500/speed_Value);
            	while(true) //pause 기능
        		{
            		btn4.addActionListener(new ActionListener(){
            	          public void actionPerformed(ActionEvent e){
            	        	  
            	        	  isPause = false; //재개
            	          }
            	       });
            		if(isPause!=true)
                	{
            			break;
                	}
        		}
               if(limit == false) // limit이 false일 경우에만 작동. true가 되면 테트리스 작동중지
                  TP.down();
            }catch(InterruptedException e){
               return;
            }
         }
      }
   }
   
   public static void main(String[] args){
      new IndepClassListener();
   }
}

class IndepClassListener extends JFrame {
	public IndepClassListener() {
		setTitle("테트리스");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(1,1400,40));
		//처음 창이 뜨는 위치
		Dimension frameSize = this.getSize();
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - frameSize.width)/4, (screenSize.height - frameSize.height)/10);
		
		c.setBackground(Color.WHITE);
		ImageIcon i1 = new ImageIcon("images/TETRIS.png");
		ImageIcon i2 = new ImageIcon("images/Game Start.png");
		ImageIcon i3 = new ImageIcon("images/Exit.png");
		
		JLabel btn0 = new JLabel(i1);
		JButton btn1 = new JButton(" ",i2);
		JButton btn2 = new JButton(i3);
		btn1.addActionListener(new MyActionListenerr());
		btn2.addActionListener(new MyActionListenerr());// Action 리스너 달기
		
		c.add(btn0);
		c.add(btn1);
		c.add(btn2);
		
		setSize(770, 700);
		setVisible(true); 
		
		}
}

class MyActionListenerr implements ActionListener { 
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		if(b.getText().equals(" "))
			new game();
		else
			System.exit(0);
	}
}
