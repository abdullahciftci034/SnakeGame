import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class snakeGame extends JPanel implements KeyListener{
    //yilanların parçaların konumlarını tuttuk
    int Xler[],Xler1[];
    int Yler[],Yler1[];
    
    int pencereBoyut=405,yilanBoyut=15,Yyon=0,Xyon=20;
    int yenilemeMiktari=3,yilanSayisi=1,appleX,appleY,cikart;
    boolean continues=true,continues1=false;
    Random rnd=new Random();
    
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame jf=new JFrame();
        snakeGame sg=new snakeGame();
        jf.addKeyListener(sg);
        jf.add(sg);
        jf.setVisible(true);
        jf.setSize(420,450);
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
    }
    
    public snakeGame(){
     this.Xler=new int[50];
     this.Yler=new int[50];
     this.Xler1=new int[50];
     this.Yler1=new int[50];
     this.Xler[0]=5;
     this.Yler[0]=5;
     this.cikart=this.pencereBoyut;
     this.appleX=rnd.nextInt(20)*(this.yilanBoyut+5)+5;
     this.appleY=rnd.nextInt(20)*(this.yilanBoyut+5)+5;
     this.loop();
    }
    
    public void loop(){
        again ag=new again(this);
        Thread t1=new Thread(ag);
        t1.start();
    }
    
    public void update1(){
        //hareket Yaptık
        this.Xler[0]+=this.Xyon;
        this.Yler[0]+=this.Yyon;
        
        for(int i=1;i<this.yilanSayisi;i++){
            if(this.Xler[0]==this.Xler[i] && this.Yler[0]==this.Yler[i]){
                this.reset1();
            }
        }
        
        //elma yeme yaptık
        if(this.Xler[0]==this.appleX && this.Yler[0]==this.appleY){
            this.continues=true;
            this.yilanSayisi++;
            this.Xler[yilanSayisi-1]=this.Xler1[yilanSayisi-2];
            this.Yler[yilanSayisi-1]=this.Yler1[yilanSayisi-2];
            while(this.continues){
                this.continues1=false;
                this.appleX=rnd.nextInt(20)*(this.yilanBoyut+5)+5;
                this.appleY=rnd.nextInt(20)*(this.yilanBoyut+5)+5;
                for(int i=0;i<this.yilanSayisi;i++){
                    if(this.Xler[i]==this.appleX && this.Yler[i]==this.appleY){
                        this.continues1=true;
                    }
                }
                if(!(this.continues1)){
                    this.continues=false;
                }
            }
        }
        
        
        //girintileri ve çıkıtntıları  yaptık
        if(this.Xler[0]>=this.cikart){
            this.Xler[0]=5;
        }
        if(this.Yler[0]>=this.cikart){
            this.Yler[0]=5;
        }
        if(this.Xler[0]<=0){
           this.Xler[0]=this.cikart;
        }
        if(this.Yler[0]<=0){
            this.Yler[0]=this.cikart;
        }
        //bir önceki konumları aldık
        for(int i=0;i<this.yilanSayisi-1;i++){
            this.Xler[i+1]=this.Xler1[i];
            this.Yler[i+1]=this.Yler1[i];
            System.out.println(this.Yler[i+1]);
        }
        ///eski arrayi update ettik
        for(int i=0;i<yilanSayisi;i++){
            this.Xler1[i]=this.Xler[i];
            this.Yler1[i]=this.Yler[i];
        }
    }
    public void reset1(){
        this.Xler[0]=5;
        this.Yler[0]=5;
        this.yilanSayisi=1;
        this.appleX=rnd.nextInt(20)*(this.yilanBoyut+5)+5;
        this.appleY=rnd.nextInt(20)*(this.yilanBoyut+5)+5;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0,0,this.pencereBoyut,this.pencereBoyut);
        g.setColor(Color.yellow);
        for(int i=0;i<yilanSayisi;i++){
            g.fillRect(this.Xler[i],this.Yler[i],this.yilanBoyut,this.yilanBoyut); 
        }
        g.setColor(Color.PINK);
        g.fillRect(this.appleX,this.appleY,this.yilanBoyut, this.yilanBoyut);
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.print(e.getKeyCode()+"\n");
        if(e.getKeyCode()==37 && this.Xyon != 20){
            this.Xyon=-20;
            this.Yyon=0;
        }
         if(e.getKeyCode()==39 && this.Xyon != -20){
            this.Xyon=20;
            this.Yyon=0;
        }
       if(e.getKeyCode()==38 && this.Yyon != 20){
            this.Xyon=0;
            this.Yyon=-20;
        }
        if(e.getKeyCode()==40 && this.Yyon != -20){
            this.Xyon= 0;
            this.Yyon=20;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
         
    }
    public class again implements Runnable{
        snakeGame sg;
        @Override
        public void run(){
            while(true){
                try {
                    Thread.sleep(1000/sg.yenilemeMiktari);
                } catch (InterruptedException ex) {
                    Logger.getLogger(snakeGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                sg.update1();
                sg.repaint();
            }
        }
        public again(snakeGame sg){
            this.sg=sg;
        }
    }
}
