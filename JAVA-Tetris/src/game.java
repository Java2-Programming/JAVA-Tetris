import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;


public class game extends JFrame {
   Container c = getContentPane();
   TetrisPanel TP = new TetrisPanel();
   JDialog JD = new JDialog(); //����
   TetrisThread th;
   
   static int blocksize = 20;
   
   int End = 0;
   int random = 0 , random2 = (int)(Math.random()*7);
   
   int score = 0;
   
   int speed_Value = 1; //�ӵ� ���� ����
   
   int wid=100;
   int hgt= 0;
   int rotation = 0;
   
   boolean isPause = false;
   boolean limit = false;
   
   int curX[]= new int[4], curY[] = new int [4]; // ��ϵ��� ��ǥ ����
   
   int blocks[][][][]  = 
      {
         {
            //��
            //����
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

               //  ��
               //����
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
               //  ���
               //  ���
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
               // �����
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
                //��
               //����
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
                //  ���
                //   ���
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
                //  ���
               //  ���
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
   
   public final int XVALUE = 12; //�� ����
   public final int YVALUE = 19; //�� ����
   public final int STARTX = 1; //0�� ���� ����Ʈ
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

   //��ư ����, �� ����
   JButton btn = new JButton("�絵��");
   JButton btn1 = new JButton("�� ��");
   JButton btn2 = new JButton("�ӵ� ��");
   JButton btn3 = new JButton("�ӵ� �ٿ�");
   JButton btn4 = new JButton("�� ��");
   JLabel lb = new JLabel(); //��Ʈ���� Ÿ��Ʋ
   JLabel lbl = new JLabel(); //����
   JLabel lbl2 = new JLabel(); //���� ����
   JLabel lbl3 = new JLabel(); //�ӵ�
   JLabel lbl4 = new JLabel(); //�ӵ�����
   
   //����
   ImageIcon i1 = new ImageIcon("images/Restart.png");
   JButton btnn = new JButton(i1);
   JLabel sc = new JLabel("Score");
   ImageIcon low = new ImageIcon("images/low.png");
   ImageIcon middle = new ImageIcon("images/middle.png");
   ImageIcon high = new ImageIcon("images/high.png");
   JLabel low1 = new JLabel(low);
   JLabel middle1 = new JLabel(middle);
   JLabel high1 = new JLabel(high);
   //����
   
   game(){
      setTitle("��Ʈ����");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(null);
      TP.setSize(1280,800); //�̰� ����ȭ�� �ػ�
      
      add(TP);
      
      th = new TetrisThread();
      
      // JDialog 
      JD.setTitle("����");
      JD.setSize(250,190);
      JD.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 30));
      
      
      lb.setText("�� Ʈ �� ��");
      lb.setFont(new Font("�������",Font.PLAIN,20));
      
      lbl.setFont(new Font("arial",Font.PLAIN,15));
      lbl2.setText("��  ��");
      lbl2.setFont(new Font("�������",Font.PLAIN,15));
      
      lbl3.setText("��  ��");
      lbl3.setFont(new Font("�������",Font.PLAIN,15));
      
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
      
      //��ư ������
      btnn.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
        	 //����
        	JD.remove(high1);
            JD.remove(middle1);
            JD.remove(low1); 
            //����
        	 
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
        	  isPause = true; //����
          }
       });
      
      btn2.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
        	  if(speed_Value<3) //�� �ӵ� 3���� ����
        		  speed_Value++;
          }
       });
      
      btn3.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
        	  if(speed_Value>1) //�ӵ��� 0�� �Ǹ� �ȵǰ� �ּ� 1�� ����
        		  speed_Value--;
          }
       });
      
      btn4.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
        	  
        	  isPause = false; //�簳
          }
       });
      
      TP.setBackground(Color.WHITE);
      setSize(1280,800); //�̰� ȭ�� �ػ�
      setVisible(true);


      // ȭ�� �߾� ����
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
         
         
         lb.setLocation(550,100); //��Ʈ���� Ÿ��Ʋ
         TP.add(lb);
         
         lbl2.setLocation(953,320); //���� ��ġ
         TP.add(lbl2);
         
         lbl3.setLocation(953,120); //�ӵ� ��ġ
         TP.add(lbl3);
         
         lbl.setLocation(960,345);  //���� ���� ��ġ
         TP.add(lbl);
         lbl.setText(Integer.toString(score*100));
         
         lbl4.setLocation(970,170);  //�ӵ� ���� ��ġ
         TP.add(lbl4);
         lbl4.setText(Integer.toString(speed_Value));
         
         btn1.setLocation(40,50);  //��ư ���� ��ġ
         TP.add(btn1);
         
         btn2.setLocation(893,140);  //��ư �ӵ� �� ��ġ
         TP.add(btn2);
         
         btn3.setLocation(983,140);  //��ư �ӵ� �ٿ� ��ġ
         TP.add(btn3);
         
         btn4.setLocation(140,50);  //��ư �簳 ��ġ
         TP.add(btn4);
         
         g.setColor(Color.ORANGE); // ���� �������� ��,�̸�����  �� ����
         
         // ���� ���� ���� ���
         blockLookAhead(g);
         
         // ���� õ�忡 ������ ���� ����
         gameOverCheck();
                
         // �� ���� ��� ������� ä���� ��� ��ϵ� ����(ä���������� ��� ��� ����������)
         removeBlock(cnt, cnt2, g);
         
         // ����� ���� �����ϸ� ���->������ ��ȯ(�������� ��� �ʱ�ȭ)
         blockToWall();
         
         // ������ ����
         makeBlock(g);
         
         // �׵θ� ����
         makeBorder(g);
       
       
         if(End == 1){
            random2 = (int)(Math.random()*7);
            End = 0;
         }
      }
      
   // ���� ���� ���� ���
      public void blockLookAhead(Graphics g){
         for(int a = 0; a<4 ;a++){
              for(int b = 0; b<4;b++){
                 if(blocks[random2][0][a][b] == 1){
                    g.fill3DRect(b*blocksize+250, a*blocksize, blocksize, blocksize, true);
                 }
              }
          }
      }
      
      //���ӿ����� ��Ÿ���°�
      public void gameOverCheck(){
         for(int x=STARTX;x<XVALUE;x++)
             if(gameboard[2][x]==1){
                limit = true;
                //����
                JD.setSize(300, 400);
               
                JD.add(sc);
                //lbl.setLocation(50,50);
                JD.add(lbl);
                JD.add(btnn);
               
                //btn.setLocation(50,30);
                JD.setVisible(true);
                
                //����
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
                blockDown(cnt,g); // �� ���� ��� ������� ä������ ���� ���� ����� ���������� ��
             }
             cnt2 = 0 ;
          }
      }
      
      public void makeBlock(Graphics g){
         g.setColor(Color.GRAY); 
          for(int y=0; y<YVALUE;y++){
             for(int x=STARTX; x<XVALUE; x++){
                if(gameboard[y][x]== 1){
                   g.fill3DRect( x*blocksize+470, y*blocksize+160, blocksize, blocksize, true);  //�����ġ
                }
             }
          }
      }
      
      public void blockDown(int cnt, Graphics g){
         for(int j = 0; j<4 ;j++){
              for(int k = 0; k<4;k++){
                 if(blocks[random][rotation][j][k] == 1){
                    curX[cnt] = ((k*blocksize)+wid)/blocksize; curY[cnt] = ((j*blocksize)+hgt)/blocksize;//curX,Y[0][1][2][3]�� ��ǥ 4�� ����
                    g.fill3DRect(curX[cnt]*blocksize+470, curY[cnt]*blocksize+160, blocksize, blocksize, true);   //�����ġ
                    
                    cnt ++;
                 }
              }
           }
      }
      
      // �������� ����� ���� �Ǵ��� �˻�
      // ���� �Ǹ� wid=120, hgt=0 ���� ��� �ʱ�ȭ, rotation�� �ʱ�ȭ 
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
      
      // ���� ���� �浹�ϸ� �������̵���
      public int collision_LEFT(){
         for(int i=0; i<4; i++){
            if(gameboard[curY[i]][curX[i]-1] == 1)  // �浹�� 1 ��ȯ
               return 1;
         }
         return 0; // �浹���� ������ 0 ��ȯ
      }
      
      // ������ ���� �浹�ϸ� �� �����̵���
      public int collision_RIGHT(){

         for(int i=0; i<4; i++){
            if(gameboard[curY[i]][curX[i]+1] == 1)   // �浹�� 1��ȯ
               return 1;
         }
         return 0; // �浹���� ������ 0��ȯ
      }
      
      // curX,Y�� ���� ȸ�� ������ ������ǥ�� ��� ����صΰ�, ���� �������̳� ���� X��ǥ1,2ĭ �ȿ� ���� ������ �׸�ŭ ������ Ȥ�� �������� �о ��ġ
      public void rotationCheck(){
       // curX,Y�� ���� ȸ�� ������ ������ǥ�� ��� ����صΰ�, �ؿ� �������� �� ������ǥ�� ���� ���� ����� �Ǵ�
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
          
       // curX,Y�� ����� ��ǥ�� �̿�
          int chk = 0;
          int blank =0;
          int error = 0;
           // ���� ��
             
                
                      if(gameboard[curY[0]][curX[0]] == 1 || (random == 6 && gameboard[curY[2]][curX[2]] == 1) || (random == 1 && gameboard[curY[1]][curX[1]] ==1 )){
                         chk = 1; // ���� ���� ȸ���� ������ ��ġ�� ���� ��ģ�ٸ� chk=1�� ǥ����           
                         error++;
                         System.out.println("chk1");
                         if(random == 3){ // ���ڸ����� ��� ȸ���� ������ �ִ� ������ ������ ȸ������
                            for(int i=1;i<5;i++)
                               if(gameboard[curY[0]][curX[0]+i] == 0)
                                  blank++;
                            if(blank < 4)
                               chk = 4;
                            
                              System.out.println(blank);
                         }else{ // �� ���� ��� ȸ���� ������ ���� ������ ������ ȸ�� ����
                            for(int i=1; i<4;i++)
                               if(gameboard[curY[0]][curX[0]+i] == 0)
                                  blank++;
                            if(blank <3)
                               chk = 4;
                            System.out.println("blank2");
                            System.out.println(blank);
                         }
                         
                      }
            
          //������ ��
             
                 
                      else if(gameboard[curY[2]][curX[2]] == 1){
                        error++;
                       chk = 2; // ���� ���� ȸ���� ������ ��ġ�� ���� ��ģ�ٸ� chk=2�� ǥ����  
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
                       chk = 3; // ���� ���� ȸ���� ������ ��ġ�� ���� ��ģ�ٸ� chk=3�� ǥ����    
                       System.out.println("chk3");
                       for(int i=0; i<5;i++)
                          if(gameboard[curY[3]][curX[3]-i] == 0)
                             blank ++;
                       if(blank < 4)
                          chk = 4;
                       System.out.println(blank);
                       
                    }
                 
             
          
          
          if(chk == 1){ // chk = 1(���� ȸ���� ������ ��ġ�� ���� �ߺ��Ǹ�)�� wid(����)�� 30�̵�
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
      
      public void makeBorder(Graphics g){  //��յ� ��ġ
         g.setColor(Color.GRAY);
         
         g.draw3DRect(478, 170, 5, 375,true); // ���
         g.draw3DRect(715, 170, 5, 375, true); // ���
         g.draw3DRect(465, 545, 270, 5,true); // �ٴ�
         g.draw3DRect(465, 165, 270, 5, true); // õ��
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
         int sel = collision_LEFT();// sel�� 1�̸� �浹, 0�̸� �浹X
         if(sel == 0 && limit == false){
            wid -= blocksize;
               TP.repaint();
         }
      }
      void moveRight(){
         int sel = collision_RIGHT();// sel�� 1�̸� �浹, 0�̸� �浹X
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
            	while(true) //pause ���
        		{
            		btn4.addActionListener(new ActionListener(){
            	          public void actionPerformed(ActionEvent e){
            	        	  
            	        	  isPause = false; //�簳
            	          }
            	       });
            		if(isPause!=true)
                	{
            			break;
                	}
        		}
               if(limit == false) // limit�� false�� ��쿡�� �۵�. true�� �Ǹ� ��Ʈ���� �۵�����
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
		setTitle("��Ʈ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(1,1400,40));
		//ó�� â�� �ߴ� ��ġ
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
		btn2.addActionListener(new MyActionListenerr());// Action ������ �ޱ�
		
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