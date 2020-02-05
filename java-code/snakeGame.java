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
    int Xler[],Xler1[];
    int Yler[],Yler1[];
    int boyut=15;
    int yilanSayisi=4;
    int Xyon=20;
    int Yyon=0;
    int pencereBoyut=405;
    int cikart;
    int appleX;
    int appleY;
    int saniyede›lerlemeZamani=1;
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
     this.Xler=new int[20];
     this.Yler=new int[20];
     this.Xler1=new int[20];
     this.Yler1=new int[20];
     this.Xler[0]=5;
     this.Yler[0]=5;
     this.Xler[1]=5;
     this.Yler[1]=5;
     this.Xler[2]=5;
     this.Yler[2]=5;
     this.Xler1[1]=5;
     this.Yler1[1]=5;
     this.Xler1[2]=5;
     this.Yler1[2]=5;
     this.cikart=this.pencereBoyut-5;
     this.appleX=rnd.nextInt(20)*(this.boyut+5)+5;
     this.appleY=rnd.nextInt(20)*(this.boyut+5)+5;
     this.loop();
    }
    public void loop(){
        again ag=new again(this);
        Thread t1=new Thread(ag);
        t1.start();
    }
    public void update1(){
        
        for(int i=0;i<this.yilanSayisi-1;i++){
            this.Xler[i+1]=this.Xler1[i];
            this.Yler[i+1]=this.Yler1[i];
            System.out.println(this.Yler[i+1]);
        }
        this.Xler[0]+=this.Xyon;
        this.Yler[0]+=this.Yyon;
        
        ///eski arrayi update ettik
        for(int i=0;i<yilanSayisi;i++){
            this.Xler1[i]=this.Xler[i];
            this.Yler1[i]=this.Yler[i];
        }
        
        
        if(this.Xler[0]>=this.cikart){
            this.Xler[0]=5;
        }
        if(this.Yler[0]>=this.cikart){
            this.Yler[0]=5;
        }
        if(this.Yler[0]<=0){
           this.Yler[0]=cikart;
        }
        if(this.Yler[0]<=0){
            this.Yler[0]=cikart;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0,0,this.pencereBoyut,this.pencereBoyut);
        g.setColor(Color.yellow);
        for(int i=0;i<yilanSayisi;i++){
            g.fillRect(this.Xler[i],this.Yler[i],this.boyut,this.boyut); 
        }
        g.setColor(Color.PINK);
        g.fillRect(this.appleX,this.appleY,this.boyut, this.boyut);
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
                    Thread.sleep(1000/sg.saniyede›lerlemeZamani);
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
